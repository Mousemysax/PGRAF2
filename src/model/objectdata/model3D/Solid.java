package model.objectdata.model3D;

import transforms.Col;
import transforms.Mat4;

import java.util.List;

public class Solid {

    protected final List<Vertex> vBuffer;
    protected final List<Integer> iBuffer;
    protected final List<SolidPart> pBuffer;
    protected final Mat4 modelMat;

    public Solid(List<Vertex> vBuffer, List<Integer> iBuffer, List<SolidPart> pBuffer, Mat4 modelMat) {
        this.vBuffer = vBuffer;
        this.iBuffer = iBuffer;
        this.pBuffer = pBuffer;
        this.modelMat = modelMat;
    }
}
