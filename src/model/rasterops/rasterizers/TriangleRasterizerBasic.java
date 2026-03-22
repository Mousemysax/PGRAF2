package model.rasterops.rasterizers;

import controller.shader.Shader;
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
    public void rasterize(Vertex a, Vertex b, Vertex c, Shader shader) {
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

        for (int y = Integer.max(ay,0); y < Integer.min(by, zBuffer.getHeight()); y++) {
            double tAB = (double) (y - ay) /(by-ay);
            Vertex ab = lerp.lerp(a, b, tAB);
            double tAC = (double) (y - ay) /(cy-ay);
            Vertex ac = lerp.lerp(a, c, tAC);

            if (ab.getX() > ac.getX()){
                Vertex tempX = ab;
                ab = ac;
                ac = tempX;
            }

            for (int x = Integer.max((int) ab.getX(),0); x < Integer.min((int)ac.getX(), zBuffer.getWidth()); x++) {
                double t = (x - ab.getX()) / (ac.getX() - ab.getX());
                Vertex pixel = lerp.lerp(ab, ac, t);
                zBuffer.setPixelZ(x,y, pixel.getZ(), shader.getColor(pixel));
            }
        }
        for (int y = Integer.max(by,0); y < Integer.min(cy, zBuffer.getHeight()); y++) {
            double tBC = (double) (y - by) /(cy-by);
            Vertex bc = lerp.lerp(b, c, tBC);
            double tAC = (double) (y - ay) /(cy-ay);
            Vertex ac = lerp.lerp(a, c, tAC);

            if (bc.getX() > ac.getX()){
                Vertex tempX = bc;
                bc = ac;
                ac = tempX;
            }

            for (int x = Integer.max((int) bc.getX(),0); x < Integer.min((int)ac.getX(), zBuffer.getWidth()); x++) {
                double t = (x - bc.getX()) / (ac.getX() - bc.getX());
                Vertex pixel = lerp.lerp(bc, ac, t);
                zBuffer.setPixelZ(x,y, pixel.getZ(), shader.getColor(pixel));
            }
        }


    }

}
