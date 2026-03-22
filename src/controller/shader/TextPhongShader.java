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
        Col col = new Col(0xffffff);
        Col light = new Col();
        double u = vert.getUv().getX();
        double v = vert.getUv().getY();
        if (u > 1) {
            u = 1;
        }
        else if (u < 0) {
            u = 0;
        }
        if (v > 1) {
            v = 1;
        } else if (v < 0) {
            v = 0;
        }




        Point3D lightPosition = scene.getLightSource().getPostition();
        Vec3D dirToLight = (lightPosition.dehomog().orElse(new Vec3D(0,0,0)).sub(vert.getWorldPosition().dehomog().orElse(new Vec3D(0,0,0))));
        light = light.add(scene.getAmbientLight().mul(0.2));
        light = light.add(scene.getLightSource().getLightColor().mul(Math.max(vert.getNormal().normalized().orElse(vert.getNormal()).dot(dirToLight.normalized().orElse(new Vec3D(0,0,0))), 0)).mul(0.8));
        col = new Col(texture.getRGB((int) (u * (texture.getWidth() - 1)), (int) (v * (texture.getHeight() - 1))));
        col = col.mul(light);

        Col normCol = new Col(vert.getNormal().getX(),vert.getNormal().getY(),vert.getNormal().getZ());
        return col;
    }
}
