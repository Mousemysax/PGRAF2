package model.rasterops.renderer;

import controller.Controller3D;
import model.objectdata.Line;
import model.objectdata.Point2D;
import model.objectdata.model3D.Mesh;
import model.objectdata.model3D.Triangle;
import model.rasterops.rasterizers.LineRasterizer;
import transforms.*;
import view.Panel;

import java.awt.*;


public class Renderer3D {

    private LineRasterizer liner;
    private Panel panel;
    private Mat4 proj, view;
    private Controller3D controller;

    public Renderer3D(LineRasterizer liner, view.Panel panel, Controller3D controller) {
        this.liner = liner;
        this.panel = panel;
        this.controller =controller;
    }

    public void render(Mesh mesh) {

        view = controller.getView().getViewMatrix();
        proj = controller.getProj();
        Mat4 mvp = mesh.getModel().mul(view).mul(proj); // DODRZET MVP PORADI!

        for (int i = 0; i < mesh.getIB().size(); i += 2) {
            int index1 = mesh.getIB().get(i);
            int index2 = mesh.getIB().get(i + 1);

            Point3D a = mesh.getVB().get(index1);
            Point3D b = mesh.getVB().get(index2);

            a = a.mul(mvp);
            b = b.mul(mvp);

            if (isInView(a)&&isInView(b)) {
                a = ungay(a);
                b = ungay(b);
                Vec3D aToWindow = transformToWindow(new Vec3D(a));
                Vec3D bToWindow = transformToWindow(new Vec3D(b));


                Point2D a2D = new Point2D((int) Math.round(aToWindow.getX()), (int) Math.round(aToWindow.getY()),mesh.getColor());
                Point2D b2D = new Point2D((int) Math.round(bToWindow.getX()), (int) Math.round(bToWindow.getY()),mesh.getColor());
                Line line = new Line(a2D,b2D);
                liner.rasterize(line);
            }


        }

    }


    private boolean isInView(Point3D p) {
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();
        double w = p.getW();

        return (-w<=x&&x<=w&&-w<=y&&y<=w&&0<=z&&z<=w);
    }

    private Point3D ungay(Point3D p){
        return new Point3D(p.getX()/p.getW(),p.getY()/p.getW(),p.getZ()/p.getW());
    }

    private Vec3D transformToWindow(Vec3D p) {
        p =p.mul(new Vec3D(1,-1,1));
        p = p.add(new Vec3D(1,1,0));
        p = p.mul(new Vec3D((panel.getWidth()-1)/2,(panel.getHeight()-1)/2,1));
        return p;
    }


    // metoda pro testovani orezani
    private void test(Point3D a, Point3D b) {
        boolean tooLeft = (a.getX() < -a.getW());
        boolean tooRight = (a.getX() > a.getW());
        boolean tooUp = (a.getY() > a.getW());
        boolean tooDown = (a.getY() < -a.getW());
        boolean tooBehind = (a.getZ() < 0);
        boolean tooFar = (a.getZ() > a.getW());

        System.out.println("--- FOR POINT A ---");
        System.out.println("Too left? " + tooLeft + ". A.x is: " + a.getX() + ", should be >= -A.w: " + (-a.getW()));
        System.out.println("Too right? " + tooRight + ". A.x is: " + a.getX() + ", should be <= A.w: " + a.getW());
        System.out.println("Too down? " + tooDown + ". A.y is: " + a.getY() + ", should be >= A.w: " + -a.getW());
        System.out.println("Too up? " + tooUp + ". A.y is: " + a.getY() + ", should be <= A.w: " + a.getW());
        System.out.println("Too behind? " + tooBehind + ". A.z is: " + a.getZ() + ", should be >= 0");
        System.out.println("Too far? " + tooFar + ". A.z is: " + a.getZ() + ", should be <= A.w: " + a.getW());

        tooLeft = (b.getX() < -b.getW());
        tooRight = (b.getX() > b.getW());
        tooUp = (b.getY() > b.getW());
        tooDown = (b.getY() < -b.getW());
        tooBehind = (b.getZ() > b.getW());
        tooFar = (b.getZ() < 0);

        System.out.println("--- FOR POINT B ---");
        System.out.println("Too left? " + tooLeft + ". B.x is: " + b.getX() + ", should be >= -B.w: " + (-b.getW()));
        System.out.println("Too right? " + tooRight + ". B.x is: " + b.getX() + ", should be <= B.w: " + b.getW());
        System.out.println("Too down? " + tooDown + ". B.y is: " + b.getY() + ", should be >= B.w: " + (-b.getW()));
        System.out.println("Too up? " + tooUp + ". B.y is: " + b.getY() + ", should be <= B.w: " + b.getW());
        System.out.println("Too behind? " + tooBehind + ". B.z is: " + b.getZ() + ", should be >= 0");
        System.out.println("Too far? " + tooFar + ". B.z is: " + b.getZ() + ", should be <= B.w: " + b.getW());

        System.out.println("=====================");
    }


}