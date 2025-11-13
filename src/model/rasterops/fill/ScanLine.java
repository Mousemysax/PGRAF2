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
    LineRasterizer lr;
    PolygonRasterizer pr;

    public ScanLine(LineRasterizer lr, PolygonRasterizer pr) {
        this.lr = lr;
        this.pr = pr;
    }

    @Override
    public void fill() {
    }

    public void scanLineFill(Polygon polygon) {
        List<Line> edges = new ArrayList<>();
        List<Line> scanLines = new ArrayList<>();

        for (int i = 0; i < polygon.size(); i++) {
            Line lajna = new Line(polygon.getItem(i),polygon.getItem((i + 1) % polygon.size()));
            if (!(lajna.start.y == lajna.end.y)){
                edges.add(lajna.orientated().shortened());
            }
        }
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        for (int i = 0; i < edges.size(); i++) {
            Line lajna = edges.get(i);
            if (lajna.start.y < yMin){
                yMin = lajna.start.y;
            }
            if (lajna.end.y > yMax){
                yMax = lajna.end.y;
            }
        }

        for (int i = yMin; i < yMax; i++) {
            List<Point2D> points = new ArrayList<>();
            for(Line lajna : edges){
                int x = lajna.getIntersectionY(i);
                if(x != -1){
                    points.add(new Point2D(x,i, polygon.getColor()));
                }
            }
            points.sort((a,b) -> a.x-b.x);
            for (int j = 0; j < points.size(); j +=2) {
                scanLines.add(new Line(points.get(j),points.get(j+1)));
            }
        }

        for (Line lajna : scanLines) {
            lr.drawLineColorLerp(lajna);
            System.out.println(lajna.start.y+","+lajna.start.x + " " + lajna.end.y+","+lajna.end.x);
        }

        pr.rasterize(polygon);
        System.out.println("penis");
    }
}
