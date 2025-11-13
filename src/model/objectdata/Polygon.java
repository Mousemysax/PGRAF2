package model.objectdata;

import java.awt.*;
import java.util.ArrayList;

public class Polygon {

    private ArrayList<Point2D> vertices;
    private Color color = Color.gray;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Polygon() {
        this.vertices = new ArrayList<>();
    }


    public Point2D getItem(int index){
        return vertices.get(index);
    }

    public int size(){
        return vertices.size();
    }

    public void addItem(Point2D vertex){
        vertices.add(vertex);
    }
    public void addItemToIndex(Point2D vertex,int index){
        vertices.add(index,vertex);
    }

    public void removeItem(int index){
        vertices.remove(index);
    }

    public Point2D getLast(){
        return vertices.getLast();
    }

    public void clear(){
        vertices.clear();
    }

    public void getFirst(){
        vertices.getFirst();
    }


}
