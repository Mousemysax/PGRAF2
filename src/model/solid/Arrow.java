package model.solid;


import model.objectdata.model3D.Part;
import model.objectdata.model3D.TopologyType;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Vec2D;

public class Arrow extends Solid {

    public Arrow() {
        vertexBuffer.add(new Vertex(200, 300, 0.5)); // v0
        vertexBuffer.add(new Vertex(400, 300, 0.5)); // v1
        vertexBuffer.add(new Vertex(400, 330, 0.5, new Col(0xff0000),new Vec2D(1,1))); // v2
        vertexBuffer.add(new Vertex(470, 300, 0.1, new Col(0x00ff00),new Vec2D(0,0.5))); // v3
        vertexBuffer.add(new Vertex(400, 270, 0.5, new Col(0x0000ff),new Vec2D(1,0))); // v4

        addIndices(0, 1); // lines
        addIndices(4, 3, 2); // triangles

        partBuffer.add(new Part(TopologyType.LINES, 0, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLES, 2, 1));
    }


}
