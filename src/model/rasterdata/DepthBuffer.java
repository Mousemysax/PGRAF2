package model.rasterdata;

import java.awt.*;

public class DepthBuffer implements Raster<Double> {
    private final double[][] buffer;
    private final int width;
    private final int height;

    public DepthBuffer(int width, int height) {

        this.width = width;
        this.height = height;
        this.buffer = new  double[height][width];
    }

    @Override
    public void clear() {

    }

    @Override
    public void repaint(Graphics g) {

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Double getValue(int x, int y) {
        return buffer[y][x];
    }

    @Override
    public void setValue(int x, int y, Double value) {
        buffer[y][x] = value;
    }
}
