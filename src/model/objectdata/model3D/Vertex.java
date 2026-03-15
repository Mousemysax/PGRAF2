package model.objectdata.model3D;

import transforms.*;


public class Vertex implements Vectorizable<Vertex> {


    private Point3D position;
    private final Col color;

    public Vec2D getUv() {
        return uv;
    }

    private final Vec2D uv;


    public Vertex(Point3D position, Col color, Vec2D uv) {
        this.position = position;
        this.color = color;
        this.uv = uv;
    }
    public Vertex(Point3D position) {
        this.position = position;
        this.uv = new Vec2D(0, 0);
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z, Vec2D uv) {
        this.uv = uv;
        this.position = new Point3D(x, y, z);
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z) {
        this.uv = new  Vec2D(0, 0);
        this.position = new Point3D(x, y, z);
        this.color = new Col(255,255,255);
    }
    public Vertex(double x, double y, double z, Col color, Vec2D uv) {
        this.uv = uv;
        this.position = new Point3D(x, y, z);
        this.color = color;



    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
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
        return new Vertex(this.position.mul(d), this.color.mul(d),this.uv.mul(d));
    }


    public Vertex mul(Mat4 mat) {
        return new Vertex(this.position.mul(mat), this.color,this.uv);
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(this.position.add(v.getPosition()), this.color.add(v.getColor()),this.uv.add(v.getUv()));
    }

    public Vertex lerp(Vertex v1, Vertex v2,double t) {
        return new Vertex(position);
    }

    public Vertex dehomogenized(){
         position = new Point3D(position.dehomog().orElse(new Vec3D(0,0,0)));
        return this;
    }
}
