package controller.shader;

import model.objectdata.model3D.Vertex;
import transforms.Col;

public class ConstColorShader implements Shader {
    @Override
    public Col getColor(Vertex v) {
        return new Col(0xd000ff);
    }
}
