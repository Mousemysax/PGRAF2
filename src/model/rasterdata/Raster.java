package model.rasterdata;

import java.awt.*;

public interface Raster<T> {

    /**
     * Metoda pre vycistenie rastra
     */
    void clear();

    /**
     * Metoda pre preklesenie platna
     * @param g
     */
    void repaint(Graphics g);

    /**
     * Vrati pocet stlpcov v rastri
     * @return pocet stlpcov
     */
    int getWidth();

    /**
     * Vrati pocet riadkov v rastri
     * @return pocet riakdov
     */
    int getHeight();


    T getValue(int x, int y);

    void setValue(int x, int y, T value);
}
