package model.rasterops.rasterizers;

import model.rasterdata.Raster;
import model.rasterdata.RasterBI;

import java.awt.*;

/**
 * Jenom ukazkovej algoritmus, nutno dokoncit alespon trivialni
 */
public class LineRasterizerGraphics extends LineRasterizer {
    public LineRasterizerGraphics(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        Graphics g = ((RasterBI) raster).getImg().createGraphics();
        g.setColor(this.color);
        g.drawLine(x1, y1, x2, y2);

    }
}
