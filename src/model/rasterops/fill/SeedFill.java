package model.rasterops.fill;

import model.rasterdata.Raster;
import transforms.Col;

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
            try {
                if(raster.getValue(x,y) == new Col(oldColor.getRGB())) {
                    raster.setValue(x,y,newColor.getRGB());
                    System.out.println(x+" "+y);
                    floodFill4(x+1, y, oldColor, newColor);
                    floodFill4(x-1, y, oldColor, newColor);
                    floodFill4(x, y+1, oldColor, newColor);
                    floodFill4(x, y-1, oldColor, newColor);
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void  floodFill4Border(int x, int y, Color bordercolor, Color newColor) {
        if(x>= 0 && y>= 0 && x < raster.getWidth() && y < raster.getHeight()) {
            if(raster.getValue(x, y) != new Col(bordercolor.getRGB())) {
                raster.setValue(x,y,newColor.getRGB());
                floodFill4Border(x+1, y, bordercolor, newColor);
                floodFill4Border(x-1, y, bordercolor, newColor);
                floodFill4Border(x, y+1, bordercolor, newColor);
                floodFill4Border(x, y-1, bordercolor, newColor);
            }
        }

    }


}
