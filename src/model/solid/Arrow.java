package model.solid;


import model.objectdata.model3D.Part;
import model.objectdata.model3D.TopologyType;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Vec2D;
import transforms.Vec3D;

public class Arrow extends Solid {

    public Arrow() {
        vertexBuffer.add(new Vertex(0, 0, 0)); // v0
        vertexBuffer.add(new Vertex(1, 0, 0)); // v1
        vertexBuffer.add(new Vertex(1, -0.3, 0, new Col(0xff0000),new Vec3D(0,0,1),new Vec2D(1,1))); // v2
        vertexBuffer.add(new Vertex(1.3, 0, 0, new Col(0x00ff00),new Vec3D(0,0,1),new Vec2D(0,0.5))); // v3
        vertexBuffer.add(new Vertex(1, 0.3, 0, new Col(0x0000ff),new Vec3D(0,0,1),new Vec2D(1,0))); // v4

        addIndices(0, 1); // lines
        addIndices(4, 3, 2); // triangles

        partBuffer.add(new Part(TopologyType.LINES, 0, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLES, 2, 1));
    }


}
