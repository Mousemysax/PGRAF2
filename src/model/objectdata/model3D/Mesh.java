package model.objectdata.model3D;

import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Mesh {

    protected ArrayList<Point3D> vertexBuffer = new ArrayList<>();
    protected ArrayList<Integer> indexBuffer = new ArrayList<>();
    private Mat4 model = new Mat4Identity();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive = false;

    public void setColor(Color color) {
        this.color = color;
    }

    protected Color color = Color.white;
    public boolean isAnimated = false;

    protected void addIndices(Integer... indices) {
        indexBuffer.addAll(Arrays.asList(indices));
    }

    public ArrayList<Point3D> getVB() {
        return vertexBuffer;
    }

    public ArrayList<Integer> getIB() {
        return indexBuffer;
    }

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }
    public Color getColor() {
        if (isActive){
            return Color.yellow;
        }
        else{
            return color;
        }
    }




}

