package model.rasterops.rasterizers;

import model.objectdata.model3D.Triangle;
import model.objectdata.model3D.Vertex;
import util.Lerp;
import model.rasterdata.ZBuffer;

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

        Lerp<Vertex> lerp = new Lerp<>();

        int ay = (int)Math.round(a.getY());
        int by = (int)Math.round(b.getY());
        int cy = (int)Math.round(c.getY());

        for (int y = ay; y < by; y++) {
            double tAB = (double) (y - ay) /(by-ay);
            Vertex ab = lerp.lerp(a, b, tAB);
            double tAC = (double) (y - ay) /(cy-ay);
            Vertex ac = lerp.lerp(a, c, tAC);

            if (ab.getX() > ac.getX()){
                Vertex tempX = ab;
                ab = ac;
                ac = tempX;
            }

            for (int x = (int) ab.getX(); x < ac.getX(); x++) {
                double t = (x - ab.getX()) / (ac.getX() - ab.getX());
                Vertex pixel = lerp.lerp(ab, ac, t);
                zBuffer.setPixelZ(x,y, pixel.getZ(), a.getColor());
            }
        }
        for (int y = by; y < cy; y++) {
            double tBC = (double) (y - by) /(cy-by);
            Vertex bc = lerp.lerp(b, c, tBC);
            double tAC = (double) (y - ay) /(cy-ay);
            Vertex ac = lerp.lerp(a, c, tAC);

            if (bc.getX() > ac.getX()){
                Vertex tempX = bc;
                bc = ac;
                ac = tempX;
            }

            for (int x = (int) bc.getX(); x < ac.getX(); x++) {
                double t = (x - bc.getX()) / (ac.getX() - bc.getX());
                Vertex pixel = lerp.lerp(bc, ac, t);
                zBuffer.setPixelZ(x,y, pixel.getZ(), a.getColor());
            }
        }


    }

}
