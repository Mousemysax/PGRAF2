import controller.Controller3D;
import view.Window;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window(400, 300);
            new Controller3D(window.getPanel());
            // new Controller3D(window.getPanel());
            window.setVisible(true); // zobrazit okno
        });


    }
}
