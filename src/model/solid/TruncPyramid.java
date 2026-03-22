package model.solid;

import model.objectdata.model3D.Part;
import model.objectdata.model3D.TopologyType;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Vec2D;
import transforms.Vec3D;

public class TruncPyramid extends Solid {

    public TruncPyramid() {
        vertexBuffer.add(new Vertex(-0.5,-0.5, 0,new Col(0xff00ff), new Vec3D(0,0,1), new Vec2D(0,0)));
        vertexBuffer.add(new Vertex(-0.5, 0.5, 0,new Col(0xff00ff), new Vec3D(0,0,1), new Vec2D(0,1)));
        vertexBuffer.add(new Vertex( 0.5, 0.5, 0,new Col(0xff00ff), new Vec3D(0,0,1), new Vec2D(1,1)));
        vertexBuffer.add(new Vertex( 0.5,-0.5, 0,new Col(0xff00ff), new Vec3D(0,0,1), new Vec2D(1,0)));

        addIndices(0,1,2);
        addIndices(0,2,3);
        vertexBuffer.add(new Vertex( 1,-1,-1,new Col(0xff00ff), new Vec3D(0,0,-1), new Vec2D(0,0)));
        vertexBuffer.add(new Vertex( 1, 1,-1,new Col(0xff00ff), new Vec3D(0,0,-1), new Vec2D(0,1)));
        vertexBuffer.add(new Vertex(-1, 1,-1,new Col(0xff00ff), new Vec3D(0,0,-1), new Vec2D(1,1)));
        vertexBuffer.add(new Vertex(-1,-1,-1,new Col(0xff00ff), new Vec3D(0,0,-1), new Vec2D(1,0)));

        addIndices(4,5,6);
        addIndices(4,6,7);
        vertexBuffer.add(new Vertex(-1,-1,-1,new Col(0xff00ff), new Vec3D(-1,0,1).normalized().get(), new Vec2D(0,0)));
        vertexBuffer.add(new Vertex(-1, 1,-1,new Col(0xffffff), new Vec3D(-1,0,1).normalized().get(), new Vec2D(0,1)));
        vertexBuffer.add(new Vertex(-0.5, 0.5, 0,new Col(0xff00ff), new Vec3D(-1,0,1).normalized().get(), new Vec2D(1,1)));
        vertexBuffer.add(new Vertex(-0.5,-0.5, 0,new Col(0xffffff), new Vec3D(-1,0,1).normalized().get(), new Vec2D(1,0)));

        addIndices(8,9,10);
        addIndices(8,10,11);
        vertexBuffer.add(new Vertex(0.5,-0.5, 0,new Col(0xffffff), new Vec3D(1,0,1).normalized().get(), new Vec2D(0,0)));
        vertexBuffer.add(new Vertex(0.5, 0.5, 0,new Col(0xffffff), new Vec3D(1,0,1).normalized().get(), new Vec2D(0,1)));
        vertexBuffer.add(new Vertex(1, 1,-1,new Col(0xffffff), new Vec3D(1,0,1).normalized().get(), new Vec2D(1,1)));
        vertexBuffer.add(new Vertex(1,-1,-1,new Col(0xffffff), new Vec3D(1,0,1).normalized().get(), new Vec2D(1,0)));

        addIndices(12,13,14);
        addIndices(12,14,15);

        vertexBuffer.add(new Vertex(-0.5,0.5, 0,new Col(0xffffff), new Vec3D(0,1,1).normalized().get(), new Vec2D(0,0)));
        vertexBuffer.add(new Vertex(-1,1,-1,new Col(0xffffff), new Vec3D(0,1,1).normalized().get(), new Vec2D(0,1)));
        vertexBuffer.add(new Vertex( 1,1,-1,new Col(0xffffff), new Vec3D(0,1,1).normalized().get(), new Vec2D(1,1)));
        vertexBuffer.add(new Vertex( 0.5,0.5, 0,new Col(0xffffff), new Vec3D(0,1,1).normalized().get(), new Vec2D(1,0)));

        addIndices(16,17,18);
        addIndices(16,18,19);

        vertexBuffer.add(new Vertex(-1,-1,-1,new Col(0xff00ff), new Vec3D(0,-1,1).normalized().get(), new Vec2D(0,0)));
        vertexBuffer.add(new Vertex(-0.5,-0.5, 0,new Col(0xff00ff), new Vec3D(0,-1,1).normalized().get(), new Vec2D(0,1)));
        vertexBuffer.add(new Vertex( 0.5,-0.5, 0,new Col(0xffffff), new Vec3D(0,-1,1).normalized().get(), new Vec2D(1,1)));
        vertexBuffer.add(new Vertex( 1,-1,-1,new Col(0xffffff), new Vec3D(0,-1,1).normalized().get(), new Vec2D(1,0)));

        addIndices(20,21,22);
        addIndices(20,22,23);

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, 12));
    }
}
