package model.objectdata.model3D;

import transforms.Point3D;

public class Polygon3D extends Mesh{
    public Polygon3D(int vertices, double edgeLength) {
        double origAngle = 2*Math.PI/vertices;
        double angle =origAngle;
        double x = 0;
        double y = 0;
        for (int i = 0; i < vertices; i++) {
            x += Math.sin(angle)*edgeLength;
            y += Math.cos(angle)*edgeLength;
            angle += origAngle;
            vertexBuffer.add(new Point3D(x,y,0));
        }
        for (int i = 0; i < vertices; i++) {
            indexBuffer.add(i);
            indexBuffer.add((i+1)%vertices);
        }
    }

    public Polygon3D(int vertices) {
        double edgeLength =  Math.PI /vertices;
        double origAngle = 2*Math.PI/vertices;
        double angle =origAngle;
        double x = -0.5;
        double y = edgeLength/2;
        for (int i = 0; i < vertices; i++) {
            x += Math.sin(angle)*edgeLength;
            y += Math.cos(angle)*edgeLength;
            angle += origAngle;
            vertexBuffer.add(new Point3D(x,y,0));
        }
        for (int i = 0; i < vertices; i++) {
            indexBuffer.add(i);
            indexBuffer.add((i+1)%vertices);
        }
    }
}
