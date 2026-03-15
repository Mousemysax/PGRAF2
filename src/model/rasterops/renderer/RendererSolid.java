package model.rasterops.renderer;


import controller.shader.Shader;
import model.objectdata.model3D.Part;
import model.objectdata.model3D.Scene;
import model.objectdata.model3D.Vertex;
import model.rasterops.rasterizers.LineRasterizer;
import model.rasterops.rasterizers.TriangleRasterizer;
import model.solid.Solid;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;
import util.Lerp;

public class RendererSolid {
    private LineRasterizer lineRasterizer;
    private TriangleRasterizer triangleRasterizer;
    private Scene scene;

    public RendererSolid(LineRasterizer lineRasterizer, TriangleRasterizer triangleRasterizer,Scene scene) {
        this.lineRasterizer = lineRasterizer;
        this.triangleRasterizer = triangleRasterizer;
        this.scene = scene;
    }

    public void render(Solid solid) {
        Mat4 mvp = solid.getModel().mul(scene.getView().getViewMatrix()).mul(scene.getProj());
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

                        Vertex a = solid.getVertexBuffer().get(indexA).mul(mvp);
                        Vertex b = solid.getVertexBuffer().get(indexB).mul(mvp);

                        double zMin = 0;

                        Vertex temp;
                        if (a.getZ() < b.getZ()) {
                            temp = a;
                            a = b;
                            b = temp;
                        }

                        if(!isInView(a)&&!isInView(b)){

                            System.out.println(a.getPosition());
                            continue;
                        }

                        if (a.getZ() < zMin){

                            continue;
                        }
                        else {
                            a = a.dehomogenized();
                            b = b.dehomogenized();
                            transformToWindow(a);
                            transformToWindow(b);
                            lineRasterizer.rasterize(a,b,solid.getShader());
                        }

                    }
                    break;
                case TRIANGLES:
                    index = part.getStartIndex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getIndexBuffer().get(index++);
                        int indexB = solid.getIndexBuffer().get(index++);
                        int indexC = solid.getIndexBuffer().get(index++);

                        Vertex a = solid.getVertexBuffer().get(indexA).mul(mvp);
                        Vertex b = solid.getVertexBuffer().get(indexB).mul(mvp);
                        Vertex c = solid.getVertexBuffer().get(indexC).mul(mvp);

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

                        if(!isInView(a)&&!isInView(b)&&!isInView(c)){
                            continue;
                        }

                        if (a.getZ() < zMin){

                            continue;
                        }
                        else if (b.getZ() < zMin){
                            double tAB = (a.getZ() - zMin)/(a.getZ()-b.getZ());
                            Vertex vAB = lerp.lerp(a,b,tAB);
                            double tAC = (a.getZ() - zMin)/(a.getZ()-c.getZ());
                            Vertex vAC = lerp.lerp(a,b,tAC);
                            renderTriangle(a, vAB,vAC, solid.getShader());
                            continue;
                        }
                        else if (c.getZ() < zMin){

                            double tBC = (b.getZ() - zMin)/(b.getZ()-c.getZ());
                            Vertex vBC = lerp.lerp(b,c,tBC);
                            renderTriangle(a, b, vBC,solid.getShader());
                            double tAC = (a.getZ() - zMin)/(a.getZ()-c.getZ());
                            Vertex vAC = lerp.lerp(a,b,tAC);
                            renderTriangle(a, vBC,vAC, solid.getShader());
                            continue;
                        }
                        else {
                            renderTriangle(a,b,c,solid.getShader());
                        }

                    }
                    break;
            }
        }
    }

    public void renderTriangle(Vertex a, Vertex b, Vertex c, Shader shader) {
        // TODO: dehomog
            a = a.dehomogenized();
            b = b.dehomogenized();
            c = c.dehomogenized();

        // TODO: transformace do okna


//        System.out.println("prewind A: "+a.getX()+","+a.getY()+","+0.5);
//        System.out.println(" B: "+b.getX()+","+b.getY()+","+0.5);
//        System.out.println(" C: "+c.getX()+","+c.getY()+","+0.5);
           transformToWindow(a);
           transformToWindow(b);
           transformToWindow(c);
//
//        System.out.println("prerast A: "+a.getX()+","+a.getY()+","+0.5);
//        System.out.println("prerast B: "+b.getX()+","+b.getY()+","+0.5);
//        System.out.println("prerast C: "+c.getX()+","+c.getY()+","+0.5);
        // Rasterizace
        triangleRasterizer.rasterize(a, b, c,shader);
    }

    private void transformToWindow(Vertex p) {
        p.setPosition(new Point3D(new Vec3D(p.getPosition()).mul(new Vec3D(1,-1,1))));
        p.setPosition(new Point3D(new Vec3D(p.getPosition()).add(new Vec3D(1,1,0))));
        p.setPosition(new Point3D(new Vec3D(p.getPosition()).mul(new Vec3D((double) (scene.getScreenWidth() - 1) /2, (double) (scene.getScreenHeight() - 1) /2,1))));
    }

    private boolean isInView(Vertex v){
        Point3D p = v.getPosition();
        if(p.getX()>p.getW()||p.getX()<-p.getW()||p.getY()>p.getW()||p.getY()<-p.getW()||p.getZ()<0.001||p.getZ()>p.getW()){
            return false;
        }
        return true;
    }
}

