package model.objectdata.model3D;

public class SolidPart {

    private final Topology t;
    private final int primitiveCount;
    private final int startIndex;

    public SolidPart(Topology t, int primitiveCount, int startIndex) {
        this.t = t;
        this.primitiveCount = primitiveCount;
        this.startIndex = startIndex;
    }
}
