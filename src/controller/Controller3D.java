package controller;

import controller.shader.ConstColorShader;
import controller.shader.LerpColorShader;
import controller.shader.Shader;
import controller.shader.TextPhongShader;
import model.objectdata.model3D.*;
import model.rasterdata.Raster;
import model.rasterdata.RasterBI;
import model.rasterdata.ZBuffer;
import model.rasterops.rasterizers.LineRasterizer;
import model.rasterops.rasterizers.LineRasterizerBasic;
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
    private BufferedImage wislonTexture;
    private BufferedImage formanTexture;

    private Solid axisZ = new Arrow();
    private Solid axisY = new Arrow();
    private Solid axisX = new Arrow();

    private final ZBuffer zbuffer;
    private boolean translMode = false;

    private final Scene scene;
    Solid solid;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.raster = (RasterBI) panel.getRaster();
        this.zbuffer = new ZBuffer(panel.getRaster());
        this.lineRasterizer = new LineRasterizerBasic(zbuffer);
        this.triangleRasterizer = new TriangleRasterizerBasic(zbuffer);
        this.scene = new Scene(
                new ArrayList<Solid>(),
                new Camera(new Vec3D(0, 0,5 ), 0 , 0, 5, true),
                new Mat4PerspRH(Math.PI/2, (double) panel.getHeight() /panel.getWidth(),0.1,10000),
                new LightSource(new Sphere(20),new Col(0xffbbbba)),
                panel);
        this.renderer = new RendererSolid(lineRasterizer,triangleRasterizer,scene);


        try {
            houseTexture = ImageIO.read(new File("res/textures/house.jpg"));
        }
        catch (Exception e) {
            System.out.println("Error loading house");
        }
        try {
            wislonTexture = ImageIO.read(new File("res/textures/wilson.jpg"));
        }
        catch (Exception e) {
            System.out.println("Error loading house");
        }
        try {
            formanTexture = ImageIO.read(new File("res/textures/foreman.jpg"));
        }
        catch (Exception e) {
            System.out.println("Error loading house");
        }
        initObjects(raster);
        initListeners(panel);

        renderScene();

    }

    @Override
    public void initObjects(Raster raster) {
//        v1 = new Vertex(100,300,0.5,new Col(0,255,0));
//        v2 = new Vertex(400.0,300,0.5);
//        v3 = new Vertex(400,200,0.5);
//        v4 = new Vertex(400,400,0.3,new Col(255,0,0));
//        v5 = new Vertex(600,300,0.3);

        axisX.setShader(new ConstColorShader(new Col(0xff0000)));
        axisY.setShader(new ConstColorShader(new Col(0x00ff00)));
        axisZ.setShader(new ConstColorShader(new Col(0x0000ff)));
        axisZ.setModel(axisZ.getModel().mul(new Mat4RotY(-Math.PI/2)));
        axisY.setModel(axisY.getModel().mul(new Mat4RotZ(Math.PI/2)));
        axisX.setModel(axisX.getModel().mul(new Mat4Scale(10)));
        axisY.setModel(axisY.getModel().mul(new Mat4Scale(10)));
        axisZ.setModel(axisZ.getModel().mul(new Mat4Scale(10)));

        solid = new TruncPyramid();
        solid.setShader(new TextPhongShader(scene,formanTexture));
        solid.setModel(solid.getModel().mul(new Mat4Scale(2)));
        solid.setModel(solid.getModel().mul(new Mat4Transl(10,-10,0)));
        scene.addSolid(solid);

        solid = new Cube();
        solid.setShader(new TextPhongShader(scene,wislonTexture));
        solid.setModel(solid.getModel().mul(new Mat4Transl(10,10,0)));
        scene.addSolid(solid);


        solid = new Sphere(20);
        solid.setShader(new TextPhongShader(scene,houseTexture));
        solid.setModel(solid.getModel().mul(new Mat4Scale(2)));
        solid.setModel(solid.getModel().mul(new Mat4Transl(10,0,0)));
        scene.addSolid(solid);


    }

    @Override
    public void initListeners(Panel panel) {
        panel.addKeyListener(new  KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    scene.selected = (scene.selected+scene.getSolids().size())%(scene.getSolids().size()+1);
                    System.out.println(scene.selected);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    scene.selected = (scene.selected+scene.getSolids().size()+2)%(scene.getSolids().size()+1);
                    System.out.println(scene.selected);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Shader textShader = new TextPhongShader(scene,houseTexture);
                    getSelectedSolid().setShader(textShader);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    getSelectedSolid().setShader(new LerpColorShader());
                }
                if (e.getKeyCode() == KeyEvent.VK_X){
                    getSelectedSolid().setModel(new Mat4RotX(Math.PI/8).mul(getSelectedSolid().getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_Y){
                    getSelectedSolid().setModel(new Mat4RotY(Math.PI/8).mul(getSelectedSolid().getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_Z){
                    getSelectedSolid().setModel(new Mat4RotZ(Math.PI/8).mul(getSelectedSolid().getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_O){
                    getSelectedSolid().setModel(new Mat4Scale(0.9).mul(getSelectedSolid().getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_I){
                    getSelectedSolid().setModel(new Mat4Scale(1.1).mul(getSelectedSolid().getModel()));
                }
                if (e.getKeyCode() == KeyEvent.VK_CONTROL){
                    translMode = !translMode;
                }
                if (translMode){
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        getSelectedSolid().setModel(new Mat4Transl(1,0,0).mul(getSelectedSolid().getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        getSelectedSolid().setModel(new Mat4Transl(0,-1,0).mul(getSelectedSolid().getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        getSelectedSolid().setModel(new Mat4Transl(-1,0,0).mul(getSelectedSolid().getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D) {
                        getSelectedSolid().setModel(new Mat4Transl(0,1,0).mul(getSelectedSolid().getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                        getSelectedSolid().setModel(new Mat4Transl(0,0,-1).mul(getSelectedSolid().getModel()));
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        getSelectedSolid().setModel(new Mat4Transl(0,0,1).mul(getSelectedSolid().getModel()));
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
                if (e.getKeyCode() == KeyEvent.VK_P){
                    scene.persp = !scene.persp;
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


    public Solid getSelectedSolid(){
        if (scene.selected>= scene.getSolids().size()){
            return scene.getLightSource().getSolid();
        }
        return scene.getSolids().get(scene.selected);
    }

    public void renderScene() {
        panel.clear();
        zbuffer.clear();
        for (Solid solid1 : scene.getSolids()){
            renderer.render(solid1);
        }
        renderer.render(scene.getLightSource().getSolid());
        renderer.render(axisX);
        renderer.render(axisY);
        renderer.render(axisZ);

        panel.repaint();
    }







    private void hardClear() {
        panel.clear();

    }

}
