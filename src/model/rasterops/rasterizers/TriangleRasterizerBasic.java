package model.rasterops.rasterizers;

import model.objectdata.model3D.Triangle;
import model.objectdata.model3D.Vertex;
import model.rasterdata.ZBuffer;
import transforms.Col;
import transforms.Point3D;

public class TriangleRasterizerBasic extends TriangleRasterizer {
    public TriangleRasterizerBasic(ZBuffer zBuffer) {
        super(zBuffer);
    }

    @Override
    public void rasterize(Triangle triangle) {

    }

    @Override
    public void rasterize(Vertex a, Vertex b, Vertex c) {
        Vertex temp;
        if (a.getY() > b.getY()) {
            temp = a;
            a = b;
            b = temp;
        }
        if(a.getY() > c.getY()) {
            temp = a;
            a = c;
            c = temp;
        }
        if(b.getY() > c.getY()) {
            temp = c;
            c = b;
            b = temp;
        }

        int ax = (int)Math.round(a.getX());
        int ay = (int)Math.round(a.getY());
        double az = a.getZ();
        int bx = (int)Math.round(b.getX());
        int by = (int)Math.round(b.getY());
        double bz = b.getZ();
        int cx = (int)Math.round(c.getX());
        int cy = (int)Math.round(c.getY());
        double cz = c.getZ();

        for (int y = ay; y < by; y++) {
            double tAB = (double) (y - ay) /(by-ay);
            int xAB = (int)Math.round((1-tAB)*ax+tAB*bx);
            double zAB = (1-tAB)*az+tAB*bz;
            double tAC = (double) (y - ay) /(cy-ay);
            int xAC = (int)Math.round((1-tAC)*ax+tAC*cx);
            double zAC = (1-tAC)*az+tAC*cz;

            if (xAB > xAC){
                int tempX = xAB;
                xAB = xAC;
                xAC = tempX;
            }

            for (int x = xAB; x < xAC; x++) {
                double tX = (double) (x - xAB) /(xAC-xAB);
                double zX = (1-tX)*zAB+tX*zAC;
                zBuffer.setPixelZ(x,y,zX,a.getColor());
            }
        }
        for (int y = by; y < cy; y++) {
            double tBC = (double) (y - by) /(cy-by);
            int xBC = (int)Math.round((1-tBC)*bx+tBC*cx);
            double zBC = (1-tBC)*bz+tBC*cz;
            double tAC = (double) (y - ay) /(cy-ay);
            int xAC = (int)Math.round((1-tAC)*ax+tAC*cx);
            double zAC = (1-tAC)*az+tAC*cz;
            if (xBC > xAC){
                int tempX = xBC;
                xBC = xAC;
                xAC = tempX;
            }

            for (int x = xBC; x < xAC; x++) {
                double tX = (double) (x - xBC) /(xAC-xBC);
                double zX = (1-tX)*zBC+tX*zAC;
                zBuffer.setPixelZ(x,y,zX,a.getColor());
            }
        }


    }

}
