package model.objectdata.model3D;

import transforms.Point3D;

public class Triangle extends Mesh {

    public Triangle() {
        Point3D p0 = new Point3D(1, -1, -0.5);
        Point3D p1 = new Point3D(-1 , 1, -0.5);
        Point3D p2 = new Point3D(1, 0.5, 0.5);

        vertexBuffer.add(p0);
        vertexBuffer.add(p1);
        vertexBuffer.add(p2);


        indexBuffer.add(0); indexBuffer.add(1);
        indexBuffer.add(1); indexBuffer.add(2);
        indexBuffer.add(2); indexBuffer.add(0);

        // setColor();

    }

}

