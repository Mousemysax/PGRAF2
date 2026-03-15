package model.rasterops.rasterizers;


import controller.shader.Shader;
import model.objectdata.model3D.Line;
import model.objectdata.model3D.Vertex;
import model.rasterdata.RasterBI;
import model.rasterdata.ZBuffer;
import transforms.Col;

public abstract class LineRasterizer {
    protected ZBuffer zBuffer;

    public LineRasterizer(ZBuffer zbuffer) {
        this.zBuffer = zbuffer;
    }

    public void rasterize(Vertex a, Vertex b, Shader shader){

    }

}
