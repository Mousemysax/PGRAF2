package model.rasterdata;

import java.awt.*;

public interface Raster<T> {


    void clear();

    void repaint(Graphics g);


    int getWidth();


    int getHeight();


    T getValue(int x, int y);

    void setValue(int x, int y, T value);
}
