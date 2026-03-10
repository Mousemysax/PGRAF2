package model.rasterops.rasterizers;

import model.rasterdata.RasterBI;

import java.awt.*;

public class LineRasterizerGraphics extends LineRasterizer {
    public LineRasterizerGraphics(RasterBI raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) {
        Graphics g = raster.getImg().getGraphics();
        g.setColor(new Color(color.getRGB()));
        g.drawLine(x1, y1, x2, y2);
    }

}
