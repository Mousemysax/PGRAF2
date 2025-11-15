package controller;

import view.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListeners implements KeyListener
{
    Controller2D controller;
    public KeyboardListeners(Controller2D controller) {
        this.controller = controller;
    }
    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.VK_SHIFT == e.getKeyCode()){
            controller.shiftMode = true;
        }
        if(e.getKeyCode() == e.VK_C){
            controller.clearAll();
        }
        if(e.getKeyCode() == e.VK_P){
            controller.polygonMode = !controller.polygonMode;
            System.out.println(controller.polygonMode);
        }
        if(e.getKeyCode() == e.VK_O){
            controller.rectMode = !controller.rectMode;
            System.out.println(controller.rectMode);
        }
        if(e.getKeyCode() == e.VK_ENTER&& controller.polygonMode){
            controller.bakePolygon();
            controller.render(controller.getPanel().getRaster());
        }
        if(e.getKeyCode() == e.VK_ALT&& controller.polygonMode){
            controller.bakeClipper();
            controller.render(controller.getPanel().getRaster());
        }
        if(e.getKeyCode() == e.VK_F){
            controller.addSeed(controller.mousePos);
            controller.render(controller.getPanel().getRaster());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.VK_SHIFT == e.getKeyCode()){
            controller.shiftMode = false;
        }
    }
}
