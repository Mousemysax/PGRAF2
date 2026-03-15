package controller.shader;

import model.objectdata.model3D.Vertex;
import transforms.Col;

public class ConstColorShader implements Shader {
    Col col = new Col(0xd000ff);
    @Override
    public Col getColor(Vertex v) {
        return col;
    }

    public ConstColorShader() {
    }
    public ConstColorShader(Col col) {
        this.col = col;
    }

}
