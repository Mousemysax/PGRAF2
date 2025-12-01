package model.objectdata.model3D;

import transforms.Point3D;



public class Triangle extends Mesh {
    // v device coordinated (window coords.)

    public Triangle() {
        Point3D p1 = new Point3D(450., 200., 1);
        Point3D p2 = new Point3D(500., 280., 1);
        Point3D p3 = new Point3D(420., 330., 1);

        vertexBuffer.add(p1); // 0
        vertexBuffer.add(p2); // 1
        vertexBuffer.add(p3); // 2

        indexBuffer.add(0); indexBuffer.add(1); // 1. hrana
        indexBuffer.add(1); indexBuffer.add(2); // 2. hrana
        indexBuffer.add(2); indexBuffer.add(0); // 3. hrana

        // setColor();

    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        vertexBuffer.add(p1); // 0
        vertexBuffer.add(p2); // 1
        vertexBuffer.add(p3); // 2

        indexBuffer.add(0); indexBuffer.add(1); // 1. hrana
        indexBuffer.add(1); indexBuffer.add(2); // 2. hrana
        indexBuffer.add(2); indexBuffer.add(0); // 3. hrana

        // setColor();

    }



}
