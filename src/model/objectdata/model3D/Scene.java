package model.objectdata.model3D;

import model.solid.Solid;

import java.util.List;

public class Scene {

    private final List<Solid> solids;

    public Scene(List<Solid> solids) {
        this.solids = solids;
    }
}
