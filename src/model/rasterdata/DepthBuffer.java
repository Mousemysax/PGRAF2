package model.rasterdata;

import java.awt.*;
import java.util.Arrays;

public class DepthBuffer implements Raster<Double> {
    private final double[][] buffer;
    private final int width;
    private final int height;

    public DepthBuffer(int width, int height) {

        this.width = width;
        this.height = height;
        this.buffer = new  double[height][width];
        clear();
    }

    @Override
    public void clear() {
        for (double[] row: buffer)
            Arrays.fill(row, Integer.MAX_VALUE);
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
