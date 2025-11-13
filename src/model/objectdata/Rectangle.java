package model.objectdata;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Polygon{


    public Rectangle(ArrayList<Point2D> vertices, Line line, Point2D point){
        super();
        line = line.orientatedX();
        int dist = line.getDistanceToPoint(point);
        double normalx = line.getNormal()[0];
        double normaly = line.getNormal()[1];
        addItem(line.start);
        addItem(line.end);
        addItem(new Point2D(line.end.getX()+(normalx*dist),line.end.getY()+(normaly*dist),getColor()));
        addItem(new Point2D(line.start.getX()+(normalx*dist),line.start.getY()+(normaly*dist),getColor()));
    }
}
