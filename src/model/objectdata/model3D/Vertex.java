package model.objectdata.model3D;

import transforms.Col;
import transforms.Point3D;


public class Vertex {
    private Point3D point;
    private Col color;


    public Vertex(Point3D point, Col color) {
        this.point = point;
        this.color = color;
    }
    public Vertex(Point3D point) {
        this.point = point;
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z) {
        this.point = new Point3D(x, y, z);
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z,Col color) {
        this.point = new Point3D(x, y, z);
        this.color = color;
    }

    public Point3D getPoint() {
        return point;
    }

    public Col getColor() {
        return color;
    }

    public double getX() {
        return point.getX();
    }
    public double getY() {
        return point.getY();
    }
    public double getZ() {
        return point.getZ();
    }

    public Vertex lerp(Vertex v1, Vertex v2,double t) {
        return new Vertex(point);
    }
}
