package model.solid;

import model.objectdata.model3D.Part;
import model.objectdata.model3D.TopologyType;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Vec2D;
import transforms.Vec3D;

public class Quad extends Solid{

    public Quad() {
        vertexBuffer.add(new Vertex(-1, -1, 0.5, new Col(0xffffff),new Vec3D(0,0,1),new Vec2D(0, 0))); // v0
        vertexBuffer.add(new Vertex(-1, 1, 0.5, new Col(0x0000ff),new Vec3D(0,0,1),new Vec2D(1, 0))); // v1
        vertexBuffer.add(new Vertex(1, 1, 0.5, new Col(0xff0000),new Vec3D(0,0,1),new Vec2D(1, 1))); // v2
        vertexBuffer.add(new Vertex(1, -1, 0.5, new Col(0x00ff00),new Vec3D(0,0,1),new Vec2D(0, 1))); // v3


        addIndices(0, 1, 2); // triangles
        addIndices(0, 2, 3);

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, 2));
    }
}
