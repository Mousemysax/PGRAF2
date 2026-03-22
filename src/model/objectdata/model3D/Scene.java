package model.objectdata.model3D;

import model.solid.LightSource;
import model.solid.Solid;
import transforms.*;
import view.Panel;

import java.util.List;

public class Scene {


    private final List<Solid> solids;
    private Camera view;
    private Mat4 proj;
    private Mat4 projOrth;
    public int selected=0;
    private LightSource lightSource;
    private Col ambientLight = new Col(0xbbbbff);
    public boolean persp = true;

    private int screenWidth;
    private int screenHeight;

    public Scene(List<Solid> solids, Camera view, Mat4 proj, LightSource lightSource, Panel panel) {
        this.solids = solids;
        this.view = view;
        this.proj = proj;
        this.lightSource = lightSource;
        this.screenWidth = panel.getWidth();
        this.screenHeight = panel.getHeight();
        this.projOrth = new Mat4OrthoRH((double) panel.getWidth() /10, (double) panel.getHeight() /10,0.1,10000);
    }

    public Camera getView() {
        return view;
    }

    public Mat4 getProj() {
        if (persp)
            return proj;

        return projOrth;
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

    public LightSource getLightSource() {
        return lightSource;
    }

    public Col getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(Col ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void setLightSource(LightSource lightSource) {
        this.lightSource = lightSource;
    }

    ;}
