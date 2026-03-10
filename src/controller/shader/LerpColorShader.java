package controller.shader;

import model.objectdata.model3D.Vertex;
import transforms.Col;

import java.awt.*;

public class LerpColorShader implements Shader {
    @Override
    public Col getColor(Vertex v) {
        return v.getColor();
    }
}
