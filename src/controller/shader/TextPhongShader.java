package controller.shader;

import model.objectdata.model3D.Scene;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.image.BufferedImage;

public class TextPhongShader implements Shader {

    Scene scene;
    BufferedImage texture;


    public TextPhongShader(Scene scene, BufferedImage texture) {
        this.scene = scene;
        this.texture = texture;
    }

    @Override
    public Col getColor(Vertex vert) {
        Col col;
        double u = vert.getUv().getX();
        double v = vert.getUv().getY();
        if (u > 1) {
            u = 1;
        }
        if (v > 1) {
            v = 1;
        }
        if (u < 0) {
            u = 0;
        }
        if (v < 0) {
            v = 0;
        }


        col = new Col(texture.getRGB((int) (u * (texture.getWidth() - 1)), (int) (v * (texture.getHeight() - 1))));
        col = col.mul(scene.getAmbientLight());
        Point3D lightPosition = scene.getLightSource().getPostition();
        Vec3D dirToLight = (lightPosition.dehomog().orElse(new Vec3D(0,0,0)).sub(vert.getWorldPosition().dehomog().orElse(new Vec3D(0,0,0))));
        col = col.add(scene.getLightSource().getLightColor().mul(Math.max(vert.getNormal().dot(dirToLight), 0)));
        System.out.println("dirToLight = " + dirToLight.toString());
        return col;
    }
}
