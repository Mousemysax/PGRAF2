package controller;

import model.objectdata.model3D.Arrow;
import model.objectdata.model3D.Triangle;
import model.rasterdata.Raster;
import model.rasterops.rasterizers.LineRasterizer;
import model.rasterops.rasterizers.LineRasterizerTrivial;
import model.rasterops.renderer.Renderer3D;
import transforms.Mat4;
import transforms.Mat4Transl;
import view.Panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller3D implements Controller {


    private final Panel panel;
    private final Raster raster;

    private double translationSize = 0.1;
    private double rotationSize = 90;

    private Mat4 translate = new Mat4();

    Triangle triangle;
    Arrow arrow;

    Renderer3D renderer;
    LineRasterizer lr;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        this.lr = new LineRasterizerTrivial(raster);
        this.renderer = new Renderer3D(lr,panel);

        initObjects(raster);
        initListeners(panel);
        renderScene();
    }

    @Override
    public void initObjects(Raster raster) {
        this.arrow = new Arrow();
        this.triangle = new Triangle();




    }

    @Override
    public void initListeners(Panel panel) {
        panel.addKeyListener(new  KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_T) {
                    translate = new Mat4Transl(0,translationSize,0);
                    arrow.setMat4(arrow.getMat4().mul(translate));
                    renderScene();
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {


                }
            }
        });
    }

    /**
     * Clears canvas and all the data structures
     */

    private void renderScene(){
        raster.clear();
        renderer.render(arrow);
        renderer.render(triangle);
    }
    private void hardClear() {

    }

}
