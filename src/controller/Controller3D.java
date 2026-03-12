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
import model.solid.Quad;
import model.solid.Solid;
import transforms.*;
import view.Panel;

import javax.imageio.ImageIO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Controller3D implements Controller {

    private final Panel panel;
    private final RasterBI raster;

    private TriangleRasterizer  triangleRasterizer;

    private Vertex v1,v2,v3,v4,v5,v6;


    private Camera view;
    private Mat4 proj;
    private final LineRasterizer lineRasterizer;
    private final RendererSolid renderer;

    private BufferedImage houseTexture;

    private final ZBuffer zbuffer;
    Solid solid;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.raster = (RasterBI) panel.getRaster();
        this.zbuffer = new ZBuffer(panel.getRaster());
        this.lineRasterizer = new LineRasterizerGraphics(raster);
        this.triangleRasterizer = new TriangleRasterizerBasic(zbuffer);
        this.renderer = new RendererSolid(lineRasterizer,triangleRasterizer);
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



        solid = new Quad("peanits");
        solid.setShader(new LerpColorShader());


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
                                u= 1;
                            }
                            return new Col(houseTexture.getRGB((int) (u*(houseTexture.getWidth()-1)), (int) (v*(houseTexture.getHeight()-1))));
                        }
                    };
                    solid.setShader(textShader);
                }

                renderScene();
            }
        });

    }



    public void renderScene() {
        panel.clear();
        zbuffer.clear();
        renderer.render(solid);
        panel.repaint();
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
