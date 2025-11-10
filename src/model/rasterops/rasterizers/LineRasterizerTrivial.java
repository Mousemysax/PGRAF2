package model.rasterops.rasterizers;

import controller.Controller2D;
import model.objectdata.Line;
import model.objectdata.Point2D;
import model.rasterdata.Raster;

import static java.lang.Math.abs;

public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }


    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int temp;
        int dx = x1-x2;
        int dy = y1-y2;
        double k = (double) (y2 - y1) / (x2 - x1);
        if(Double.isNaN(k)||Double.isInfinite(k)){
            k = Integer.MAX_VALUE;
        }
        double q = y1 - k * x1;
        if(abs(dx)>abs(dy)){
            if(x1>x2){
                temp = x1;
                x1 = x2;
                x2 = temp;
            }
            for (int x = x1; x <= x2; x++) {
                int y = (int) (k * x + q);
                raster.setPixel(x, y, color.getRGB());
            }
        }
        else {
            if(y1>y2){
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            for (int y = y1; y <= y2; y++) {
                int x = (int) ((y - q)/k);
                raster.setPixel(x, y, color.getRGB());
            }
        }




    }



    //Trivialni algoritmus s linearni interpolaci barev
    public void drawLineColorLerp(Line line) {
        try{
            Point2D start = line.start;
            Point2D end = line.end;
            Point2D temPoint;
            int x1 = start.x;
            int x2 = end.x;
            int y1 = start.y;
            int y2 = end.y;
            if(x2<0){
                x2=0;
            }
            if(y2<0){
                y2=0;
            }
            int temp;
            int dx = x1-x2;
            int dy = y1-y2;
            double k = (double) (y2 - y1) / (x2 - x1);
            if(Double.isNaN(k)||Double.isInfinite(k)){
                k = Integer.MAX_VALUE;
            }
            double q = y1 - k * x1;
            if(abs(dx)>abs(dy)){
                if(x1>x2){
                    temp = x1;
                    x1 = x2;
                    x2 = temp;
                    temPoint = start;
                    start = end;
                    end = temPoint;
                }
                for (int x = x1; x <= x2; x++) {
                    int y = (int) (k * x + q);
                    raster.setPixel(x, y,Controller2D.colorLerp(start.getColor(),end.getColor(),((double) (x-x1) /Math.abs(x2-x1))).getRGB());
                }
            }
            else {
                if(y1>y2){
                    temp = y1;
                    y1 = y2;
                    y2 = temp;
                    temPoint = start;
                    start = end;
                    end = temPoint;
                }
                for (int y = y1; y <= y2; y++) {
                    int x = (int) ((y - q)/k);
                    raster.setPixel(x, y,Controller2D.colorLerp(start.getColor(),end.getColor(),((double) (y-y1) /Math.abs(y2-y1))).getRGB());
                }
            }
        }
        catch (Exception e){

        }
    }

}
