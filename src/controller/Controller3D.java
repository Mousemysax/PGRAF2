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
import model.solid.Arrow;
import model.solid.Cube;
import model.solid.Quad;
import model.solid.Solid;
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

    private final Scene scene;
    Solid solid;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.raster = (RasterBI) panel.getRaster();
        this.zbuffer = new ZBuffer(panel.getRaster());
        this.lineRasterizer = new LineRasterizerGraphics(raster);
        this.triangleRasterizer = new TriangleRasterizerBasic(zbuffer);
        this.scene = new Scene(new ArrayList<Solid>(),new Camera(new Vec3D(0, 0,0 ), 0 , -Math.PI/2, 5, true),new Mat4PerspRH(Math.PI/2, (double) panel.getWidth() /panel.getHeight(),0,10000),panel);
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



        solid = new Cube();
        solid.setShader(new LerpColorShader());
//        solid.setModel(solid.getModel().mul(new Mat4Scale(1000000000)));
//        solid.setModel(solid.getModel().mul(new Mat4Scale(1000000000)));
        solid.setModel(solid.getModel().mul(new Mat4Rot(Math.PI/4,new Vec3D(0,1,0))));
        solid.setModel(solid.getModel().mul(new Mat4Transl(10,0,0)));
        scene.addSolid(solid);



    }

    @Override
    public void initListeners(Panel panel) {
        panel.addKeyListener(new  KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    solid.setShader(new LerpColorShader());
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    solid.setShader(new ConstColorShader());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Shader textShader = new Shader() {
                        @Override
                        public Col getColor(Vertex vert) {
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
                            System.out.println(u+";"+v);
                            return new Col(houseTexture.getRGB((int) (u*(houseTexture.getWidth()-1)), (int) (v*(houseTexture.getHeight()-1))));
                        }
                    };
                    solid.setShader(textShader);
                }
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
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    solid.setModel(solid.getModel().mul(new Mat4Rot(0.5,1,new Vec3D(1,1,1))));
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
        System.out.println(scene.getView().getViewVector());

        panel.repaint();
    }







    private void hardClear() {
        panel.clear();

    }

}
