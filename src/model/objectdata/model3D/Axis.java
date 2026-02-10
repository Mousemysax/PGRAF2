package model.objectdata.model3D;

import transforms.*;

import java.awt.*;

public class Axis extends Mesh{
    public Axis(int lod, Vec3D direction, Color color) {
        this.color = color;
        Point3D lastPos = new Point3D(0,0,0);
        direction = direction.mul(0.2);
        Mat4 trans = new Mat4Transl(direction);
        for (int i = 0; i < lod; i++) {
            vertexBuffer.add(lastPos);
            lastPos = lastPos.mul(trans);
        }
        for (int i = 0; i < lod-1; i++) {
            indexBuffer.add(i);
            indexBuffer.add(i+1);
        }
        lastPos = new Point3D(0,0,0);
        trans = new Mat4Transl(direction.opposite());
        for (int i = 0; i < lod; i++) {
            vertexBuffer.add(lastPos);
            lastPos = lastPos.mul(trans);
        }
        for (int i = 0; i < lod-1; i++) {
            indexBuffer.add(i+lod);
            indexBuffer.add(i+lod+1);
        }
    }
}
