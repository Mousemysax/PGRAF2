package model.rasterops.renderer;


import controller.shader.ConstColorShader;
import controller.shader.LerpColorShader;
import controller.shader.Shader;
import model.objectdata.model3D.Part;
import model.objectdata.model3D.Triangle;
import model.objectdata.model3D.Vertex;
import model.rasterops.rasterizers.LineRasterizer;
import model.rasterops.rasterizers.TriangleRasterizer;
import model.solid.Solid;

public class RendererSolid {
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;

    public RendererSolid(LineRasterizer lineRasterizer, TriangleRasterizer triangleRasterizer) {
        this.lineRasterizer = lineRasterizer;
        this.triangleRasterizer = triangleRasterizer;
    }

    public void render(Solid solid) {
        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case POINTS:
                    // TODO: points
                    break;
                case LINES:
                    int index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);

                        Vertex a = solid.getVertexBuffer().get(indexA);
                        Vertex b = solid.getVertexBuffer().get(indexB);

                        // TODO: vrcholy pronásobím MVP

                        // TODO: ořezání


                        // TODO: dehomog

                        // TODO: transformace do okna

                        // Rasterizace
                        lineRasterizer.rasterize(
                                (int) Math.round(a.getX()),
                                (int) Math.round(a.getY()),
                                (int) Math.round(b.getX()),
                                (int) Math.round(b.getY())
                        );
                    }
                    break;
                case TRIANGLES:
                    index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);
                        int indexC = solid.getIndexBuffer().get(index++);

                        Vertex a = solid.getVertexBuffer().get(indexA);
                        Vertex b = solid.getVertexBuffer().get(indexB);
                        Vertex c = solid.getVertexBuffer().get(indexC);

                        // TODO: vrcholy pronásobím MVP

                        // TODO: ořezání

                        double zMin = 0;

                        if (a.getZ() < zMin){
                            continue;
                        }
                        if (b.getZ() < zMin){
                            Double tAB = (a.getZ() - zMin);
                            //renderTriangle(a, vAB,vAC);
                            continue;
                        }

                        if (c.getZ() < zMin){
                            //renderTriangle(a, b, vBC);
                            //renderTriangle(a, vBC,vAC);
                            continue;
                        }
                        renderTriangle(a,b,c,solid.getShader());

                    }
                    break;
            }
        }
    }

    public void renderTriangle(Vertex a, Vertex b, Vertex c, Shader shader) {
        // TODO: dehomog

        // TODO: transformace do okna

        // Rasterizace
        triangleRasterizer.rasterize(a, b, c,shader);
    }
}

