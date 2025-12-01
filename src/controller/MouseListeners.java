package controller;

import model.objectdata.Line;
import model.objectdata.Point2D;
import view.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListeners implements MouseListener {

    Controller2D controller;
    Panel panel;
    public MouseListeners(Controller2D controller) {
        this.panel = controller.getPanel();
        this.controller = controller;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(controller.polygonMode){
            controller.polygon.addItem(new Point2D(e.getX(),e.getY()));

        }
        else if(controller.rectMode){
            if(controller.rectSecondStage){

            }
            else{
                controller.line = new Line(new Point2D(e.getX(),e.getY(), Color.red),new Point2D(e.getX(),e.getY(),Color.red));
            }
        }
        else {
            controller.line = new Line(new Point2D(e.getX(),e.getY(), Color.blue),new Point2D(e.getX(),e.getY(),Color.magenta));
        }
        controller.render(controller.getPanel().getRaster());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(controller.polygonMode){

        }else if (controller.rectMode){
            if(controller.rectSecondStage){
                controller.bakePolygon();
                controller.rectSecondStage = false;
            }
            else{
                controller.rectSecondStage = true;
            }

        }
        else
        {
            controller.bakeLine();
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
