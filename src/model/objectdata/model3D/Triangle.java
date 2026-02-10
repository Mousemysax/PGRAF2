package model.objectdata.model3D;

import transforms.Point3D;

public class Triangle extends Mesh {

    public Triangle() {
        Point3D p0 = new Point3D(0.6, 0.3333, 1);
        Point3D p1 = new Point3D(0.3, 0., 1);
        Point3D p2 = new Point3D(0.2, 0.6, 1);

        vertexBuffer.add(p0);
        vertexBuffer.add(p1);
        vertexBuffer.add(p2);


        indexBuffer.add(0); indexBuffer.add(1);
        indexBuffer.add(1); indexBuffer.add(2);
        indexBuffer.add(2); indexBuffer.add(0);

        // setColor();

    }

}

