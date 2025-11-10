package model.objectdata;

import java.awt.*;

public class Point2D {
    public int x, y;

    private Color color = Color.white;
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(double x, double y) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public Point2D(double x, double y, Color color) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor(){
        return color;
    }
    // TODO distanceTo another Point2D
    // https://en.wikipedia.org/wiki/Euclidean_distance

}
