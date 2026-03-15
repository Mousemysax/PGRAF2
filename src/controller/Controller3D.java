package controller;

import controller.shader.ConstColorShader;
import controller.shader.LerpColorShader;
import controller.shader.Shader;
import model.objectdata.model3D.*;
import model.rasterdata.Raster;
import model.rasterdata.RasterBI;
import model.rasterdata.ZBuffer;
import model.rasterops.rasterizers.LineRasterizer;
import model.rasterops.rasterizers.LineRasterizerGraphics;
import model.rasterops.rasterizers.TriangleRasterizer;
import model.rasterops.rasterizers.TriangleRasterizerBasic;
import model.rasterops.renderer.RendererSolid;
import model.solid.*;
import transforms.*;
import view.Panel;

import javax.imageio.ImageIO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Controller3D implements Controller {

    private final Panel panel;
    private final RasterBI raster;

    private TriangleRasterizer  triangleRasterizer;

    private Vertex v1,v2,v3,v4,v5,v6;


    private double speed = 0.5;
    private final LineRasterizer lineRasterizer;
    private final RendererSolid renderer;

    private BufferedImage houseTexture;

    private final ZBuffer zbuffer;
    private boolean translMode = false;

    private final Scene scene;
    Solid solid;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.raster = (RasterBI) panel.getRaster();
        this.zbuffer = new ZBuffer(panel.getRaster());
        this.lineRasterizer = new LineRasterizerGraphics(raster);
        this.triangleRasterizer = new TriangleRasterizerBasic(zbuffer);
        this.scene = new Scene(
                new ArrayList<Solid>(),
                new Camera(new Vec3D(0, 0,0 ), 0 , 0, 5, true),
                new Mat4PerspRH(Math.PI/2, (double) panel.getHeight() /panel.getWidth(),0.1,10000),
                new LightSource(new Sphere(20),new Col(0xff0000)),
                panel);
        this.renderer = new RendererSolid(lineRasterizer,triangleRasterizer,scene);
        initObjects(raster);
        initListeners(panel);

        try {
            houseTexture = ImageIO.read(new File("res/textures/house.jpg"));
        }
        catch (Exception e) {
            System.out.println("Error loading house");
        }

        renderScene();

    }

    @Override
    public void initObjects(Raster raster) {
//        v1 = new Vertex(100,300,0.5,new Col(0,255,0));
//        v2 = new Vertex(400.0,300,0.5);
//        v3 = new Vertex(400,200,0.5);
//        v4 = new Vertex(400,400,0.3,new Col(255,0,0));
//        v5 = new Vertex(600,300,0.3);



        solid = new Quad();
        solid.setShader(new LerpColorShader());
//        solid.setModel(solid.getModel().mul(new Mat4Scale(1000000000)));
        solid.setModel(solid.getModel().mul(new Mat4Scale(1)));
        solid.setModel(solid.getModel().mul(new Mat4Transl(5,0,0)));
        scene.addSolid(solid);

        solid = new Sphere(20);
        solid.setShader(new LerpColorShader());
        solid.setModel(solid.getModel().mul(new Mat4Scale(2)));
        solid.setModel(solid.getModel().mul(new Mat4Transl(10,5,0)));
        scene.addSolid(solid);


    }

    @Override
    public void initListeners(Panel panel) {
        panel.addKeyListener(new  KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    scene.selected = (scene.selected+scene.getSolids().size()-1)%(scene.getSolids().size());
                    System.out.println(scene.selected);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    scene.selected = (scene.selected+scene.getSolids().size()+1)%(scene.getSolids().size());
                    System.out.println(scene.selected);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Shader textShader = new Shader() {
                        @Override
                        public Col getColor(Vertex vert) {
                            Col col;
                            double u = vert.getUv().getX();
                            double v =  vert.getUv().getY();
                            if (u >1){
                                u= 1;
                            }
                            if (v >1){
                                v= 1;
                            }
                            if (u <0){
                                u= 0;
                            }
                            if (v <0){
                                v= 0;
                            }
                            col = new Col(houseTexture.getRGB((int) (u*(houseTexture.getWidth()-1)), (int) (v*(houseTexture.getHeight()-1))));
                            col = col.mul(scene.getAmbientLight());
                            Vec3D dirToLight = scene.getLightSource().getPostition().dehomog().orElse(new Vec3D(1,0,0)).add(vert.getPosition().dehomog().orElse(new Vec3D(0,0,0)));
                            col = col.mul(scene.getLightSource().getLightColor().mul(vert.getNormal().dot(dirToLight)));
                            System.out.println("colcca"+vert.getNormal().dot(dirToLight));
                            return col;
                        }
                    };
                    scene.getSolids().get(scene.selected).setShader(textShader);
                }
                if (e.getKeyCode() == KeyEvent.VK_X){
                    scene.getSolids().get(scene.selected).setModel(new Mat4RotX(Math.PI/8).mul(scene.getSolids().get(scene.selected).getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_Y){
                    scene.getSolids().get(scene.selected).setModel(new Mat4RotY(Math.PI/8).mul(scene.getSolids().get(scene.selected).getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_Z){
                    scene.getSolids().get(scene.selected).setModel(new Mat4RotZ(Math.PI/8).mul(scene.getSolids().get(scene.selected).getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_CONTROL){
                    translMode = !translMode;
                }
                if (translMode){
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        scene.getSolids().get(scene.selected).setModel(new Mat4Transl(1,0,0).mul(scene.getSolids().get(scene.selected).getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        scene.getSolids().get(scene.selected).setModel(new Mat4Transl(0,-1,0).mul(scene.getSolids().get(scene.selected).getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        scene.getSolids().get(scene.selected).setModel(new Mat4Transl(-1,0,0).mul(scene.getSolids().get(scene.selected).getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D) {
                        scene.getSolids().get(scene.selected).setModel(new Mat4Transl(0,1,0).mul(scene.getSolids().get(scene.selected).getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                        scene.getSolids().get(scene.selected).setModel(new Mat4Transl(0,0,-1).mul(scene.getSolids().get(scene.selected).getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        scene.getSolids().get(scene.selected).setModel(new Mat4Transl(0,0,1).mul(scene.getSolids().get(scene.selected).getModel()));
                    }
                }
                else {
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        scene.setView(scene.getView().forward(speed));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        scene.setView(scene.getView().left(speed));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        scene.setView(scene.getView().backward(speed));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D) {
                        scene.setView(scene.getView().right(speed));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                        scene.setView(scene.getView().down(speed));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        scene.setView(scene.getView().up(speed));
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    scene.getSolids().get(scene.selected).setShader(new LerpColorShader());
                }

                renderScene();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                scene.setView(scene.getView().withAzimuth(-(((double) e.getX() /panel.getWidth())*2-1)*Math.PI));
                scene.setView(scene.getView().withZenith(-(((double) e.getY() /panel.getHeight())*2-1)*Math.PI/2));
                renderScene();
            }
        });

    }



    public void renderScene() {
        panel.clear();
        zbuffer.clear();
        for (Solid solid1 : scene.getSolids()){
            renderer.render(solid1);
        }
        //renderer.render(scene.getLightSource().getSolid());
        System.out.println(scene.getView().getViewVector());

        panel.repaint();
    }







    private void hardClear() {
        panel.clear();

    }

}
