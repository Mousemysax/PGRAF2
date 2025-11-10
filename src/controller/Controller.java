package controller;

import model.rasterdata.Raster;
import view.Panel;

public interface Controller {

    void initObjects(Raster raster);
    void initListeners(Panel panel);
}
