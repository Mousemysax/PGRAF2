package model.objectdata.model3D;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

public class ParaCurve extends  Mesh{
    public ParaCurve(Mat4 curveType, int lod) {
        Point3D p1 = new Point3D(1.,-1.,1);
        Point3D p2 = new Point3D(1.,1.,1);
        Point3D p3 = new Point3D(-1.,0.,-1);
        Point3D p4 = new Point3D(-1.,1.,-1);
        Cubic curve = new Cubic(curveType,p1,p2,p3,p4);
        for (int i = 0; i <= lod; i++) {
            vertexBuffer.add(curve.compute((double) i /lod));
        }
        for (int i = 0; i < lod; i++) {
            indexBuffer.add(i);
            indexBuffer.add(i+1);
        }
    }
}
