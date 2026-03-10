package model.objectdata.model3D;

import transforms.Col;
import transforms.Point3D;


public class Vertex implements Vectorizable<Vertex> {
    private Point3D position;
    private Col color;


    public Vertex(Point3D position, Col color) {
        this.position = position;
        this.color = color;
    }
    public Vertex(Point3D position) {
        this.position = position;
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z) {
        this.position = new Point3D(x, y, z);
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z,Col color) {
        this.position = new Point3D(x, y, z);
        this.color = color;



    }

    public Point3D getPosition() {
        return position;
    }

    public Col getColor() {
        return color;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getZ() {
        return position.getZ();
    }

    @Override
    public Vertex mul(double d) {
        return new Vertex(this.position.mul(d), this.color.mul(d));
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(this.position.add(v.getPosition()), this.color.add(v.getColor()));
    }

    public Vertex lerp(Vertex v1, Vertex v2,double t) {
        return new Vertex(position);
    }
}
