package controller;

import model.objectdata.Line;
import model.objectdata.Point2D;
import view.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.atan;

public class DragListeners implements MouseMotionListener {

    Controller2D controller;
    Panel panel;
    public DragListeners(Controller2D controller) {
        this.panel = controller.getPanel();
        this.controller = controller;
    }




    @Override
    public void mouseDragged(MouseEvent e) {
        if(controller.polygonMode){
            controller.polygon.getLast().x = e.getX();
            controller.polygon.getLast().y = e.getY();
        }
        else if(controller.rectMode){
            if(controller.rectSecondStage){
                controller.constructRect(controller.line,new Point2D(e.getX(),e.getY()));
            }
            else{
                controller.line = new Line(controller.line.start, new Point2D(e.getX(), e.getY(), controller.line.end.getColor()));

            }
        }
        else
        {
            if (!controller.shiftMode) {
                controller.line = new Line(controller.line.start, new Point2D(e.getX(), e.getY(), controller.line.end.getColor()));
            } else {
                double k = ((double) (controller.line.start.y - e.getY()) / (double) (controller.line.start.x - e.getX()));
                if (Math.abs(atan(k)) < Math.PI / 6) {
                    controller.line = new Line(controller.line.start, new Point2D(e.getX(), controller.line.start.y, controller.line.end.getColor()));

                } else if (Math.abs(atan(k)) < Math.PI / 3) {

                    controller.line = new Line(controller.line.start, new Point2D(e.getX(), (e.getX() * -Math.signum(e.getX() - controller.line.start.x) - controller.line.start.x * -Math.signum(e.getX() - controller.line.start.x)) * Math.signum(controller.line.start.y - e.getY()) + controller.line.start.y, controller.line.end.getColor()));

                } else {
                    controller.line = new Line(controller.line.start, new Point2D(controller.line.start.x, e.getY(), controller.line.end.getColor()));

                }

            }
        }
        controller.render(controller.getPanel().getRaster());

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        controller.mousePos = new Point2D(e.getX(),e.getY());
    }
}
