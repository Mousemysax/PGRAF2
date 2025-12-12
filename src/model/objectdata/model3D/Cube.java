package model.objectdata.model3D;

import transforms.Point3D;

public class Cube extends Mesh{
    public Cube(){
        Point3D p1 = new Point3D(1.,-1.,1);
        Point3D p2 = new Point3D(1.,-1.,-1);
        Point3D p3 = new Point3D(-1.,-1.,-1);
        Point3D p4 = new Point3D(-1.,-1.,1);
        Point3D p5 = new Point3D(1.,1.,1);
        Point3D p6 = new Point3D(1.,1.,-1);
        Point3D p7 = new Point3D(-1.,1.,-1);
        Point3D p8 = new Point3D(-1.,1.,1);

        vertexBuffer.add(p1);
        vertexBuffer.add(p2);
        vertexBuffer.add(p3);
        vertexBuffer.add(p4);
        vertexBuffer.add(p5);
        vertexBuffer.add(p6);
        vertexBuffer.add(p7);
        vertexBuffer.add(p8);

        indexBuffer.add(0); indexBuffer.add(1);
        indexBuffer.add(1); indexBuffer.add(2);
        indexBuffer.add(2); indexBuffer.add(3);
        indexBuffer.add(3); indexBuffer.add(0);
        indexBuffer.add(4); indexBuffer.add(5);
        indexBuffer.add(5); indexBuffer.add(6);
        indexBuffer.add(6); indexBuffer.add(7);
        indexBuffer.add(7); indexBuffer.add(4);
        indexBuffer.add(0); indexBuffer.add(4);
        indexBuffer.add(1); indexBuffer.add(5);
        indexBuffer.add(2); indexBuffer.add(6);
        indexBuffer.add(3); indexBuffer.add(7);
        indexBuffer.add(0); indexBuffer.add(2);
        indexBuffer.add(1); indexBuffer.add(6);
        indexBuffer.add(4); indexBuffer.add(6);
        indexBuffer.add(0); indexBuffer.add(7);
        indexBuffer.add(0); indexBuffer.add(5);
        indexBuffer.add(3); indexBuffer.add(6);
    }
}
