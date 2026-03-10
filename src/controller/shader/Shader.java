package controller.shader;

import model.objectdata.model3D.Vertex;
import transforms.Col;

import java.awt.*;

public interface Shader {

    Col getColor(Vertex v);
}
