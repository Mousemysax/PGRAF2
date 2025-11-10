package model.rasterops.rasterizers;

import model.objectdata.Line;
import model.objectdata.Polygon;
import model.rasterdata.Raster;

import java.awt.*;

public class PolygonRasterizer {

        Raster raster;
        LineRasterizer lr;
        Color color = Color.blue;

        public PolygonRasterizer(Raster raster,LineRasterizer lr){
            this.lr = lr;
            this.raster = raster;
        }

        public void rasterize(Polygon polygon) {
            drawPolygon(polygon);
        }


        protected void drawPolygon(Polygon polygon) {
            for (int i = 0; i < polygon.size(); i++) {
                lr.drawLineColorLerp(new Line(polygon.getItem(i),polygon.getItem((i+1)%polygon.size())));
            }
        }


        public void setColor(Color color) {
            this.color = color;
        }

        public void setColor(int color) {
            this.color = new Color(color);
        }

}
