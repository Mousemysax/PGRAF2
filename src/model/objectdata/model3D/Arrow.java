package model.objectdata.model3D;

import transforms.Point3D;

public class Arrow extends Mesh{
    public Arrow() {
        vertexBuffer.add(new Point3D(0, 0, 0));
        vertexBuffer.add(new Point3D(0.8, 0, 0));
        vertexBuffer.add(new Point3D(0.8, -0.5, 0));
        vertexBuffer.add(new Point3D(0.95, 0, 0));
        vertexBuffer.add(new Point3D(0.8, 0.5, 0));

        addIndices(
                0, 1,
                1, 2,
                2, 3,
                3, 4,
                1, 4
        );

    }

}
