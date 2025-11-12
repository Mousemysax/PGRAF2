package controller;

import model.objectdata.Line;
import model.objectdata.Point2D;
import model.objectdata.Polygon;
import model.rasterdata.Raster;
import model.rasterops.fill.Filler;
import model.rasterops.fill.ScanLine;
import model.rasterops.fill.SeedFill;
import model.rasterops.rasterizers.LineRasterizerTrivial;
import model.rasterops.rasterizers.PolygonRasterizer;
import view.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller2D implements Controller {

    private final Panel panel;
    private final Raster raster;
    private LineRasterizerTrivial lrt;
    private PolygonRasterizer pr;
    private SeedFill seedFiller;


    public Polygon polygon;
    public Line line;
    public Boolean shiftMode = false;
    public Boolean polygonMode = false;
    public Point2D mousePos = new Point2D(0,0);
    private ArrayList<Line> lineList = new ArrayList<>();
    private ArrayList<Point2D> seedList = new ArrayList<>();
    private ArrayList<Polygon> polygonList = new ArrayList<>();

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        this.lrt = new LineRasterizerTrivial(raster);
        this.pr = new PolygonRasterizer(raster,lrt);
        this.seedFiller = new SeedFill(raster);

        raster.clear();
        initObjects(raster);
        initListeners(panel);

        render(raster);
        render(raster);
    }

    @Override
    public void initObjects(Raster raster) {
        polygon = new Polygon();
        line = new Line(new Point2D(10,100,Color.blue),new Point2D(20,300,Color.magenta));


    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseMotionListener(new DragListeners(this));
        panel.addMouseListener(new MouseListeners(this));
        panel.addKeyListener(new KeyboardListeners(this));
    }


    public void render(Raster raster) {
        panel.clear();
        for(Line line1 : lineList){
            lrt.drawLineColorLerp(line1);

        }

        for (Polygon poly : polygonList){
            pr.rasterize(poly);
        }
        for (Point2D seed : seedList){
            seedFiller.floodFill4(seed.x,seed.y,new Color(0x16161D),Color.cyan);
        }
        pr.rasterize(polygon);
        lrt.drawLineColorLerp(line);
        panel.repaint();
    }

    public void bakeLine(){
        lineList.add(new Line(line.start,line.end));
        line = new Line(0,0,0,0);
    }

    public void bakePolygon(){
        polygonList.add(polygon);
        polygon = new Polygon();
    }

    public static double lerp(double a,double b, double t){
        return a*(1.00 - t) + b*t;
    }

    public static int lerp(int a,int b, double t){

        if(t>1){
            return b;
        }
        return (int)(a*(1.00 - t) + b*t);
    }

    public static Color colorLerp(Color x,Color y, double t){
        if(t>1){
            return y;
        }
        int r = lerp(x.getRed(),y.getRed(),t);
        int g = lerp(x.getGreen(),y.getGreen(),t);
        int b = lerp(x.getBlue(), y.getBlue(), t);


        return new Color(r,g,b);
    }

    public void clearAll(){
        bakePolygon();
        lineList.clear();
        polygonList.clear();
        raster.clear();
        render(raster);
    }

    public void addSeed(Point2D seedPoint){
        seedList.add(seedPoint);
    }

    /**
     * Clears canvas and all the data structures
     */
    private void hardClear() {

    }

    public ArrayList<Object> quicksort(ArrayList<Object> list){
        return  list;
    }

    public Panel getPanel(){
        return panel;
    }
}
