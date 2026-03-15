package model.rasterops.rasterizers;

import controller.shader.Shader;
import model.objectdata.model3D.Vertex;
import model.rasterdata.RasterBI;
import model.rasterdata.ZBuffer;
import util.Lerp;

public class LineRasterizerBasic extends LineRasterizer {
    public LineRasterizerBasic(ZBuffer zBuffer) {
        super(zBuffer);
    }

    @Override
    public void rasterize(Vertex a, Vertex b, Shader shader){
        Vertex temp;
        if (a.getY() > b.getY()) {
            temp = a;
            a = b;
            b = temp;
        }
        Lerp<Vertex> lerp = new Lerp<>();

        int ay = (int)Math.round(a.getY());
        int by = (int)Math.round(b.getY());

        for (int y = ay; y < by; y++) {
            double tAB = (double) (y - ay) /(by-ay);
            Vertex pixel = lerp.lerp(a, b, tAB);

            zBuffer.setPixelZ((int) pixel.getX(),y, pixel.getZ(), shader.getColor(pixel));
            System.out.println(pixel.getX());
        }
    }



}
