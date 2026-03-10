package model.rasterdata;

import transforms.Col;

public class ZBuffer {
    private final Raster<Col> imageBuffer;
    private final Raster<Double> depthBuffer;

    public ZBuffer(Raster<Col> imageBuffer) {
        this.imageBuffer = imageBuffer;
        this.depthBuffer = new DepthBuffer(imageBuffer.getWidth(), imageBuffer.getHeight());
    }

    public void setPixelZ(int x, int y, double z,Col color) {
        if(x <0 || y <0 || z <0 || x >= imageBuffer.getWidth() || y >= imageBuffer.getHeight()) {
            return;
        }
        if(depthBuffer.getValue(x,y) > z){
            imageBuffer.setValue(x,y,color);
            depthBuffer.setValue(x,y,z);
        }
    }

    public void clear() {
        imageBuffer.clear();
        depthBuffer.clear();
    }
}
