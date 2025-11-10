package model.rasterops.rasterizers;

import model.rasterdata.Raster;

import static java.lang.Math.abs;

public class LineRasterizerBresenham extends LineRasterizer {

    public LineRasterizerBresenham(Raster raster) {
        super(raster);
    }

    // TODO
    // https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
    @Override
    public void drawLine(int x0, int y0, int x1, int y1) {
        if (abs(y1 - y0) < abs(x1 - x0)) {
            if (x0 > x1){
                plotLineLow(x1, y1, x0, y0);
            }
            else
            {
                plotLineLow(x0, y0, x1, y1);
            }
        }
        else{
            if (y0 > y1)
            {
                plotLineHigh(x1, y1, x0, y0);
            }
            else
            {
                plotLineHigh(x0, y0, x1, y1);
            }
        }
    }

    public void plotLineLow(int x0, int y0, int x1, int y1){
        int temp;

        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy < 0) {
            yi = -1;
            dy = -dy;
        }
        int D = 2 * dy - dx;
        int y = y0;

        for (int x = x0; x < x1; x++) {
            raster.setPixel(x, y, color.getRGB());
            if (D > 0) {
                y += yi;
                D -= 2 * dx;
            }
            D += 2 * dy;
        }

    }

    public void plotLineHigh(int x0, int y0, int x1, int y1){
        int temp;

        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }
        int D = (2 * dx) - dy;
        int x = x0;

        for (int y = y0; y < y1; y++) {
            raster.setPixel(x, y, color.getRGB());
            if (D > 0) {
                x+= xi;
                D -= 2 * dy;
            }
            D += 2 * dx;
        }

    }


}
