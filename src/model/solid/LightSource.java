package model.solid;

import controller.shader.ConstColorShader;
import model.objectdata.model3D.Vertex;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

public class LightSource {
    private Solid solid;
    private Col lightColor;

    public LightSource(Solid solid, Col lightColor) {
        this.solid = solid;
        this.lightColor = lightColor;
        solid.setShader(new ConstColorShader(lightColor));
    }

    public Solid getSolid() {
        return solid;
    }

    public void setSolid(Solid solid) {
        this.solid = solid;
    }

    public Col getLightColor() {
        return lightColor;
    }

    public void setLightColor(Col lightColor) {
        this.lightColor = lightColor;
    }

    public Point3D getPostition(){
        return new Point3D(0,0,0).mul(solid.getModel());
    }
}
