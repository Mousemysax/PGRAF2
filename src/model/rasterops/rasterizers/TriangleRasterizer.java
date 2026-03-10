package model.rasterops.rasterizers;

import controller.shader.Shader;
import model.objectdata.model3D.Triangle;
import model.objectdata.model3D.Vertex;
import model.rasterdata.ZBuffer;
import transforms.Col;
import transforms.Point3D;

public abstract class TriangleRasterizer {

    protected final ZBuffer zBuffer;

    protected TriangleRasterizer(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;
    }


    public abstract void rasterize(Triangle triangle);

    public abstract void rasterize(Vertex v1, Vertex v2, Vertex v3, Shader shader);
}
