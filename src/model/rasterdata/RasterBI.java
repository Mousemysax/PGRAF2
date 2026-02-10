package model.rasterdata;

import transforms.Col;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBI implements Raster<Col> {

    private final BufferedImage img;
    private final Color backgroundColor = new Color(0x16161D);

    public RasterBI(int width, int height) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, img.getWidth() , img.getHeight() );
    }

    @Override
    public void repaint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public int getWidth() {
        return  img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public Col getValue(int x, int y) {
        return new Col(img.getRGB(x,y));
    }

    @Override
    public void setValue(int x, int y, Col color) {
        img.setRGB(x,y,color.getRGB());
    }

    public BufferedImage getImg() {
        return img;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
