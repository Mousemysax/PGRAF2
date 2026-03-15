package model.objectdata.model3D;

import model.solid.Solid;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4PerspRH;
import view.Panel;

import java.util.List;

public class Scene {


    private final List<Solid> solids;
    private Camera view;
    private Mat4 proj;

    private int screenWidth;
    private int screenHeight;

    public Scene(List<Solid> solids, Camera view, Mat4 proj, Panel panel) {
        this.solids = solids;
        this.view = view;
        this.proj = proj;
        this.screenWidth = panel.getWidth();
        this.screenHeight = panel.getHeight();
    }

    public Camera getView() {
        return view;
    }

    public Mat4 getProj() {
        return proj;
    }

    public void setProj(Mat4 proj) {this.proj = proj;}

    public void setView(Camera view) {this.view = view;}

    public List<Solid> getSolids() {
        return solids;
    }

    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    ;}
