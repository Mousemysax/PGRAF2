package model.rasterops.renderer;

import model.objectdata.Line;
import model.objectdata.model3D.Mesh;
import model.objectdata.model3D.Triangle;
import model.rasterops.rasterizers.LineRasterizer;
import transforms.Point3D;
import transforms.Vec3D;
import view.Panel;

import java.awt.*;


public class Renderer3D {

    private LineRasterizer liner;
    private Panel panel;

    public Renderer3D(LineRasterizer liner, view.Panel panel) {
        this.liner = liner;
        this.panel = panel;
    }

    public void render(Mesh mesh) {

        for (int i = 0; i < mesh.getIB().size(); i += 2) {
            int index1 = mesh.getIB().get(i);
            int index2 = mesh.getIB().get(i + 1);

            Point3D a = mesh.getVB().get(index1);
            Point3D b = mesh.getVB().get(index2);

            a = a.mul(mesh.getMat4());
            b = b.mul(mesh.getMat4());

            if (mesh instanceof Triangle) {
                Line line = new Line(
                        (int) Math.round(a.getX()),
                        (int) Math.round(a.getY()),
                        (int) Math.round(b.getX()),
                        (int) Math.round(b.getY())
                );
                liner.setColor(Color.WHITE);
                liner.rasterize(line);

            } else {
                if(isInView(a)&&isInView(b)) {
                    return;
                }
                a = unGay(a);
                b = unGay(b);
                Vec3D aToWindow = transformToWindow(new Vec3D(a));
                Vec3D bToWindow = transformToWindow(new Vec3D(b));

                Line line = new Line(
                        (int) Math.round(aToWindow.getX()),
                        (int) Math.round(aToWindow.getY()),
                        (int) Math.round(bToWindow.getX()),
                        (int) Math.round(bToWindow.getY())
                );
                liner.setColor(Color.RED);
                liner.rasterize(line);
            }


        }

    }

    private Vec3D transformToWindow(Vec3D p) {
        p = p.mul(new Vec3D(1,-1,1));
        p = p.add(new Vec3D(1,1,0));
        p = p.mul(new Vec3D((panel.getWidth()-1)/2,(panel.getHeight()-1)/2,0));
        return p;
    }

    private boolean isInView(Point3D p) {
        return (p.getX()>(-p.getW())&&p.getX()<p.getW()&&p.getY()>-p.getW()&&p.getY()<p.getW()&&p.getZ()>0&&p.getZ()<p.getW() );
    }

    private Point3D unGay(Point3D p) {
        return new Point3D(p.getX()/p.getW(), p.getY()/p.getW(), p.getZ()/p.getW(),1);
    }



}

