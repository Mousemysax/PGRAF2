package model.rasterops.fill;

import model.objectdata.Line;
import model.objectdata.Point2D;
import model.objectdata.Polygon;
import model.rasterops.rasterizers.LineRasterizer;
import model.rasterops.rasterizers.PolygonRasterizer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScanLine implements Filler{
    Polygon polygon;
    LineRasterizer lr;
    PolygonRasterizer pr;

    public ScanLine(Polygon polygon, LineRasterizer lr, PolygonRasterizer pr) {
        this.polygon = polygon;
        this.lr = lr;
        this.pr = pr;
    }

    @Override
    public void fill() {
        scanLineFill();
    }

    private void scanLineFill(){
        List<Line> edges = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < polygon.size(); i++) {
            Line lajna = new Line(polygon.getItem(i),polygon.getItem((i + 1) % polygon.size()));
            if (!(lajna.start.y == lajna.end.y)){
                edges.add(lajna.orientated());
            }
        }
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        for (int i = 0; i < edges.size(); i++) {
            Line lajna = edges.get(i);
            if (lajna.start.y < yMin){
                yMin = lajna.start.y;
            }
            if (lajna.start.y > yMax){
                yMax = lajna.start.y;
            }
        }

        for (int i = yMin; i < yMax; i++) {
            List<Point2D> points = new ArrayList<>();
            for(Line lajna : edges){
                int x = lajna.getIntersectionY(i);
                if(x != -1){
                    points.add(new Point2D(x,lajna.getIntersectionY(i), Color.GRAY));
                }
            }

            for (int j = 0; j < points.size(); j +=2) {
                lines.add(new Line(points.get(j),points.get(j+1)));
            }
        }

        for (Line lajna : lines) {
            lr.drawLine(lajna);
        }

        pr.rasterize(polygon);
    }
}
