package model.objectdata.model3D;

import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;



public class Mesh {

    protected ArrayList<Point3D> vertexBuffer = new ArrayList<>();
    protected ArrayList<Integer> indexBuffer = new ArrayList<>();
    protected Mat4 mat4 = new Mat4Identity();
    // color
    // isSelected

    protected void addIndices(Integer... indices) {
        indexBuffer.addAll(Arrays.asList(indices));
    }

    public ArrayList<Point3D> getVB() {
        return vertexBuffer;
    }

    public ArrayList<Integer> getIB() {
        return indexBuffer;
    }

    public Mat4 getMat4() {
        return mat4;
    }

    public void setMat4(Mat4 mat4) {
        this.mat4 = mat4;
    }
}

