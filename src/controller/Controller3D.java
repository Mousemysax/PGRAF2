package controller;

import model.objectdata.model3D.*;
import model.rasterdata.Raster;
import model.rasterdata.RasterBI;
import model.rasterdata.ZBuffer;
import model.rasterops.rasterizers.LineRasterizerTrivial;
import model.rasterops.rasterizers.TriangleRasterizer;
import model.rasterops.rasterizers.TriangleRasterizerBasic;
import model.rasterops.renderer.Renderer3D;
import transforms.*;
import view.Panel;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller3D implements Controller {

    private final Panel panel;
    private final RasterBI raster;

    private Mesh arrow;
    private Mesh triangle;
    private Cube cube;
    private Cube cube2;
    private Polygon3D polygon3D;
    private Prism prism;
    private ParaCurve curve;
    private Axis xaxis;
    private Axis yaxis;
    private Axis zaxis;

    private boolean perspective = true;

    private ArrayList<Mesh> objects = new ArrayList<>();
    private ArrayList<Mesh> axes = new ArrayList<>();
    private int activeIndex = 0;


    private Mat4 rot, transl;
    private final double speed = 0.5;
    private final double rotSpeed = 0.1;

    private LineRasterizerTrivial liner;
    private Renderer3D renderer3D;
    private TriangleRasterizer  triangleRasterizer;

    private Vertex v1,v2,v3,v4,v5,v6;


    private Camera view;
    private Mat4 proj;

    private final ZBuffer zbuffer;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.raster = (RasterBI) panel.getRaster();
        this.zbuffer = new ZBuffer(panel.getRaster());
        this.triangleRasterizer = new TriangleRasterizerBasic(zbuffer);
        initObjects(raster);
        initListeners(panel);

        renderScene();

    }

    @Override
    public void initObjects(Raster raster) {
        v1 = new Vertex(400.0,0,0.5,new Col(0,255,0));
        v2 = new Vertex(0.0,300,0.5);
        v3 = new Vertex(799.0,599,0.5);
        v4 = new Vertex(100,0,0.3,new Col(255,0,0));
        v5 = new Vertex(0.0,200,0.3);
        v6 = new Vertex(799,500,0.6);
//        view = new Camera(new Vec3D(-15, 0,0 ), 0 , 0, 5, true);
//        proj = new Mat4PerspRH(Math.toRadians(90), (double) panel.getHeight() / panel.getWidth(), 0.1, 10000);
//        liner = new LineRasterizerTrivial(raster);
//        renderer3D = new Renderer3D(liner, panel,this);
//        triangle = new Triangle();
//        arrow = new Arrow();
//        polygon3D = new Polygon3D(7);
//        prism = new Prism(polygon3D,1);
//        prism.setModel(prism.getModel().mul(new Mat4Transl(0,-2,0)));
//        prism.setModel(prism.getModel().mul(new Mat4Scale(5)));
//        cube = new Cube();
//        cube.isAnimated = true;
//        animateMesh(cube,new Mat4RotZ(Math.PI/60),60);
//        curve = new ParaCurve(Cubic.BEZIER,1000);
//        curve.setColor(Color.MAGENTA);
//        animateMesh(curve,new Mat4RotZ(Math.PI/60),60);
//        xaxis = new Axis(1000,new Vec3D(1,0,0), Color.red);
//        yaxis = new Axis(1000,new Vec3D(0,1,0), Color.green);
//        zaxis = new Axis(1000,new Vec3D(0,0,1), Color.blue);
//
//        axes.add(xaxis);
//        axes.add(yaxis);
//        axes.add(zaxis);
//        objects.add(new ParaCurve(Cubic.FERGUSON,1000));
//        objects.getLast().setModel(objects.getLast().getModel().mul(new Mat4Transl(0,10,0)));
//        objects.getLast().setActive(true);
//        objects.add(new ParaCurve(Cubic.COONS,1000));
//        objects.getLast().setModel(objects.getLast().getModel().mul(new Mat4Transl(0,5,0)));
//        objects.add(cube);
//        objects.add(curve);
//        objects.add(prism);





    }

    @Override
    public void initListeners(Panel panel) {

//        panel.addKeyListener(
//                new KeyAdapter()
//                {
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_W) {
//                    view = view.forward(speed);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_A) {
//                    view = view.left(speed);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_S) {
//                    view = view.backward(speed);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_D) {
//                    view = view.right(speed);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
//                    view = view.down(speed);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    view = view.up(speed);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_Z) {
//                    objects.get(activeIndex).setModel(new Mat4RotZ(Math.PI/16).mul(objects.get(activeIndex).getModel()));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_Y) {
//                    objects.get(activeIndex).setModel(new Mat4RotY(Math.PI/16).mul(objects.get(activeIndex).getModel()));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_X) {
//                    objects.get(activeIndex).setModel(new Mat4RotX(Math.PI/16).mul(objects.get(activeIndex).getModel()));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_K) {
//                    objects.get(activeIndex).setModel(new Mat4Scale(1.2).mul(objects.get(activeIndex).getModel()));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_L) {
//                    objects.get(activeIndex).setModel(new Mat4Scale(0.833333333).mul(objects.get(activeIndex).getModel()));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    objects.get(activeIndex).setModel(objects.get(activeIndex).getModel().mul(new Mat4Transl(1,0,0)));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    objects.get(activeIndex).setModel(objects.get(activeIndex).getModel().mul(new Mat4Transl(-1,0,0)));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    objects.get(activeIndex).setModel(objects.get(activeIndex).getModel().mul(new Mat4Transl(0,1,0)));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    objects.get(activeIndex).setModel(objects.get(activeIndex).getModel().mul(new Mat4Transl(0,-1,0)));
//                }
//                if (e.getKeyCode() == KeyEvent.VK_P) {
//                    if(perspective){
//                        proj = new Mat4OrthoRH((double) panel.getWidth() /20, (double) panel.getHeight() /20  , 0.1, 10000);
//                        view = view.withFirstPerson(false);
//                    }
//                    else {
//                        proj = new Mat4PerspRH(Math.toRadians(90), (double) panel.getHeight() / panel.getWidth(), 0.1, 10000);
//                        view = view.withFirstPerson(true);
//                    }
//                    perspective = !perspective;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_1) {
//                    objects.get(activeIndex).setActive(false);
//                    activeIndex = (activeIndex+1)%objects.size();
//                    objects.get(activeIndex).setActive(true);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_2) {
//                    objects.get(activeIndex).setActive(false);
//                    activeIndex = (activeIndex-1+objects.size())%objects.size();
//                    objects.get(activeIndex).setActive(true);
//                }
//                if (e.getKeyCode() == KeyEvent.VK_Q){
//
//                }
//                renderScene();
//            }
//        });
//        panel.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                view = view.withAzimuth(-(((double) e.getX() /panel.getWidth())*2-1)*Math.PI);
//                view = view.withZenith(-(((double) e.getY() /panel.getHeight())*2-1)*Math.PI/2);
//                renderScene();
//            }
//        });


    }



    public void renderScene() {
        panel.clear();
        //renderer3D.render(triangle);
        //renderer3D.render(arrow);

//        for (Mesh axis : axes){
//            renderer3D.render(axis);
//        }
//        for (Mesh mesh : objects){
//            renderer3D.render(mesh);
//        }
        triangleRasterizer.rasterize(v1, v2, v3);
        triangleRasterizer.rasterize(v4, v5, v6);
        panel.repaint();
    }

    private void animateMesh(Mesh mesh,Mat4 animTransform,int fps) {
        ScheduledExecutorService loopTimer = Executors.newScheduledThreadPool(1);
        loopTimer.scheduleAtFixedRate(new AnimateFrame(mesh,animTransform,this), 0, 1000/fps, TimeUnit.MILLISECONDS );
    }





    private void hardClear() {
        panel.clear();

    }
    public Camera getView() {
        return view;
    }

    public Mat4 getProj() {
        return proj;
    }

}
