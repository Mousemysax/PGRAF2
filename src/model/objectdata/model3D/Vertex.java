package model.objectdata.model3D;

import transforms.*;


public class Vertex implements Vectorizable<Vertex> {


    private Point3D position;
    private final Col color;
    private final Vec3D normal;
    private Point3D worldPosition;


    public Vec2D getUv() {
        return uv;
    }

    private final Vec2D uv;


    public Vertex(Point3D position, Col color, Vec3D normal, Vec2D uv) {
        this.position = position;
        this.color = color;
        this.normal = normal;
        this.uv = uv;
        this.worldPosition = position;
    }


    public Vertex(double x, double y, double z) {
        this.normal = new Vec3D(0,0,0);
        this.uv = new  Vec2D(0, 0);
        this.position = new Point3D(x, y, z);
        this.color = new Col(255,255,255);
        this.worldPosition = position;

    }
    public Vertex(double x, double y, double z, Col color, Vec3D normal, Vec2D uv ){
        this.normal = normal;
        this.uv = uv;
        this.position = new Point3D(x, y, z);
        this.color = color;
        this.worldPosition = position;


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

    public Vec3D getNormal() {
        return normal;
    }

    @Override
    public Vertex mul(double d) {
        return new Vertex(this.position.mul(d), this.color.mul(d),this.normal.mul(d) ,this.uv.mul(d));
    }


    public Vertex mul(Mat4 mat) {
        return new Vertex(this.position.mul(mat), this.color,this.normal , this.uv);
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(this.position.add(v.getPosition()), this.color.add(v.getColor()), this.normal.add(v.getNormal()), this.uv.add(v.getUv()));
    }


    public Vertex dehomogenized(){
         position = new Point3D(position.dehomog().orElse(new Vec3D(0,0,0)));
        return this;
    }

    public Point3D getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(Point3D worldPosition) {
        this.worldPosition = worldPosition;
    }
}
