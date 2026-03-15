package model.solid;

import model.objectdata.model3D.Part;
import model.objectdata.model3D.TopologyType;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Vec2D;

public class Cube extends Solid {

    public Cube() {
        vertexBuffer.add(new Vertex(-1, -1, -1, new Col(0xffffff),new Vec2D(0, 0))); // v0
        vertexBuffer.add(new Vertex(-1, 1, -1, new Col(0x0000ff),new Vec2D(0, 1))); // v1
        vertexBuffer.add(new Vertex(1, 1, -1, new Col(0xff0000),new Vec2D(1, 1))); // v2
        vertexBuffer.add(new Vertex(1, -1, -1, new Col(0x00ff00),new Vec2D(1, 0))); // v3
        vertexBuffer.add(new Vertex(-1, -1, 1, new Col(0xffffff),new Vec2D(0, 0))); // v0
        vertexBuffer.add(new Vertex(-1, 1, 1, new Col(0x0000ff),new Vec2D(0, 1))); // v1
        vertexBuffer.add(new Vertex(1, 1, 1, new Col(0xff0000),new Vec2D(1, 1))); // v2
        vertexBuffer.add(new Vertex(1, -1, 1, new Col(0x00ff00),new Vec2D(1, 0))); // v3

        addIndices(0, 1, 2); // triangles
        addIndices(0, 2, 3);
        addIndices(1,5,6);
        addIndices(1,6,2);
        addIndices(0,1,5);
        addIndices(0,5,4);
        addIndices(0,3,4);
        addIndices(3,4,7);
        addIndices(5,4,7);
        addIndices(5,7,6);
        addIndices(3,2,6);
        addIndices(3,6,7);

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, 12));
    }
}
