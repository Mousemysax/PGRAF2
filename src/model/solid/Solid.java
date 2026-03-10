package model.solid;


import controller.shader.Shader;
import model.objectdata.model3D.Part;
import model.objectdata.model3D.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Solid {
    protected final List<Vertex> vertexBuffer = new ArrayList<>();
    protected final List<Integer> indexBuffer = new ArrayList<>();
    protected final List<Part> partBuffer = new ArrayList<>();

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    protected Shader shader;

    public List<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public List<Part> getPartBuffer() {
        return partBuffer;
    }

    public void addIndices(Integer... indices) {
        indexBuffer.addAll(Arrays.asList(indices));
    }
}
