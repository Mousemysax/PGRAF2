package model.rasterops.fill;

import model.rasterdata.Raster;

import java.awt.*;

public class SeedFill implements Filler {
    private final Raster raster;

    public SeedFill(Raster raster) {
        this.raster = raster;
    }

    public void fill() {

    }

    public void  floodFill4(int x, int y, Color oldColor, Color newColor) {
        if(x>= 0 && y>= 0 && x < raster.getWidth() && y < raster.getHeight()) {
            if(raster.getPixel(x, y) == oldColor.getRGB()) {
                raster.setPixel(x,y,newColor.getRGB());
                floodFill4(x+1, y, oldColor, newColor);
                floodFill4(x-1, y, oldColor, newColor);
                floodFill4(x, y+1, oldColor, newColor);
                floodFill4(x, y-1, oldColor, newColor);
            }
        }

    }

    private void  floodFill8(int x, int y, Color oldColor, Color newColor) {
        if(x>= 0 && y>= 0 && x < raster.getWidth() && y < raster.getHeight()) {
            if(raster.getPixel(x, y) == oldColor.getRGB()) {
                raster.setPixel(x,y,newColor.getRGB());
                floodFill8(x-1, y, oldColor, newColor);
                floodFill8(x+1, y, oldColor, newColor);
                floodFill8(x, y+1, oldColor, newColor);
                floodFill8(x, y-1, oldColor, newColor);
                floodFill8(x+1, y+1, oldColor, newColor);
                floodFill8(x-1, y+1, oldColor, newColor);
                floodFill8(x+1, y-1, oldColor, newColor);
                floodFill8(x-1, y-1, oldColor, newColor);
            }
        }

    }
}
