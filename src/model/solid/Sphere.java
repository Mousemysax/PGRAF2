package model.solid;

import model.objectdata.model3D.Part;
import model.objectdata.model3D.TopologyType;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Vec2D;
import transforms.Vec3D;

public class Sphere extends Solid{
    public Sphere(int smoothness) {
        float radius = 1.0f;

// ===== VERTEX BUFFER =====
        for (int i = 0; i <= smoothness; i++) {

            float u = (float) i / smoothness;
            double phi = Math.PI * u;

            for (int j = 0; j <= smoothness; j++) {

                float v = (float) j / smoothness;
                double theta = 2.0 * Math.PI * v;

                float x = (float)(radius * Math.sin(phi) * Math.cos(theta));
                float y = (float)(radius * Math.cos(phi));
                float z = (float)(radius * Math.sin(phi) * Math.sin(theta));

                vertexBuffer.add(
                        new Vertex(
                                x, y, z,
                                new Col(0xffffff),
                                new Vec3D(x,y,z).normalized().orElse(new Vec3D(0,0,1)),
                                new Vec2D(u, v)
                        )
                );
            }
        }

        int stride = smoothness + 1;

// ===== INDEX BUFFER =====
        for (int i = 0; i < smoothness; i++) {
            for (int j = 0; j < smoothness; j++) {

                int a = i * stride + j;
                int b = a + 1;
                int c = a + stride;
                int d = c + 1;

                indexBuffer.add(a);
                indexBuffer.add(c);
                indexBuffer.add(b);

                indexBuffer.add(b);
                indexBuffer.add(c);
                indexBuffer.add(d);
            }
        }

        partBuffer.add(new Part(TopologyType.TRIANGLES, 0, smoothness * smoothness * 2));
    }
}
