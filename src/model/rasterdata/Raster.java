package model.rasterdata;

import java.awt.*;

public interface Raster<T> {

    /**
     * Metoda pre vycistenie rastra
     */
    void clear();

    void repaint(Graphics g);

    /**
     * Vrati pocet stlpcov v rastri
     * @return pocet stlpcov
     */
    int getWidth();


    int getHeight();


    T getValue(int x, int y);

    void setValue(int x, int y, T value);
}
