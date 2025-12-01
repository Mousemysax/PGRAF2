package model.objectdata;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

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

    public void flip(){
        Collections.reverse(vertices);
    }

    public boolean isCCW(){
        int result = 0;
        for (int i = 0; i < size(); i++) {
            result += (getItem(i).x-getItem((size()+i+1)%size()).x)*((getItem(i).y+getItem((size()+i+1)%size()).y));
        }
        return result<0;
    }

}
