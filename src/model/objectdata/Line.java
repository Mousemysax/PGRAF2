package model.objectdata;

import java.awt.*;

public class Line {
    public final Point2D start;
    public final Point2D end;

    public Line(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }

    public Line(Point2D start, Point2D end, int color) {
        this.start = start;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point2D(x1, y1);
        this.end = new Point2D(x2, y2);
    }

    public Line(int x1, int y1, int x2, int y2, int color) {
        this.start = new Point2D(x1, y1);
        this.end = new Point2D(x2, y2);
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    public Line orientated(){
        if(start.y > end.y){
            return new Line(end,start);
        }
        return new Line(start,end);
    }
    public Line shortened(){
        return new Line(start.x,start.y,end.x,end.y-1);
    }
    public int getIntersectionY(int y){
        int y1 = this.start.y;
        int y2 = this.end.y;
        if(y <y1||y>y2){
            return -1;
        }
        int x1 = this.start.x;
        int x2 = this.end.x;
        double k = (double) (y2 - y1) / (x2 - x1);
        if(Double.isNaN(k)||Double.isInfinite(k)){
            k = Integer.MAX_VALUE;
        }
        double q = y1 - k * x1;
        return (int) ((y - q)/k);
    }

}
