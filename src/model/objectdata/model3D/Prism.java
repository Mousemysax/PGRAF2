package model.objectdata.model3D;

import transforms.Point3D;

public class Prism extends Mesh{
    public Prism(Polygon3D base, double height) {
        for (Point3D vertex : base.vertexBuffer){
            vertexBuffer.add(vertex.withZ(vertex.getZ()-height/2));
        }
        for (Point3D vertex : base.vertexBuffer){
            vertexBuffer.add(vertex.withZ(vertex.getZ()+height/2));
        }
        int baseVertices = base.vertexBuffer.size();
        for (int i = 0; i < baseVertices; i++) {
            indexBuffer.add(i);indexBuffer.add((i+1)%baseVertices);
            indexBuffer.add(i+baseVertices);indexBuffer.add((i+1)%baseVertices+baseVertices);
            indexBuffer.add(i);indexBuffer.add(i+baseVertices);
        }
    }


}
