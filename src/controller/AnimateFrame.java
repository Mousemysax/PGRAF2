package controller;

import model.objectdata.model3D.Mesh;
import transforms.Mat4;

public class AnimateFrame implements Runnable {

    Mesh mesh;
    Mat4 animTransform;
    Controller3D cont;


    public AnimateFrame(Mesh mesh, Mat4 animTransform,Controller3D cont) {
        this.mesh = mesh;
        this.animTransform = animTransform;
        this.cont = cont;
    }

    @Override
    public void run() {
        mesh.setModel(mesh.getModel().mul(animTransform));
        cont.renderScene();
    }
}
