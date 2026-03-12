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
import util.Lerp;

public class RendererSolid {
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;

    public RendererSolid(LineRasterizer lineRasterizer, TriangleRasterizer triangleRasterizer) {
        this.lineRasterizer = lineRasterizer;
        this.triangleRasterizer = triangleRasterizer;
    }

    public void render(Solid solid) {
        Lerp<Vertex> lerp = new Lerp<>();
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

                        Vertex temp;
                        if (a.getZ() < b.getZ()) {
                            temp = a;
                            a = b;
                            b = temp;
                        }
                        if(a.getZ() < c.getZ()) {
                            temp = a;
                            a = c;
                            c = temp;
                        }
                        if(b.getZ() < c.getZ()) {
                            temp = c;
                            c = b;
                            b = temp;
                        }

                        if (a.getZ() < zMin){

                            System.out.println("peanits A");
                            continue;
                        }
                        if (b.getZ() < zMin){
                            double tAB = (a.getZ() - zMin)/(a.getZ()-b.getZ());
                            Vertex vAB = lerp.lerp(a,b,tAB);
                            double tAC = (a.getZ() - zMin)/(a.getZ()-c.getZ());
                            Vertex vAC = lerp.lerp(a,b,tAC);
                            renderTriangle(a, vAB,vAC, solid.getShader());
                            System.out.println("peanits B");
                            continue;
                        }

                        if (c.getZ() < zMin){

                            System.out.println("peanits C");
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

