package View;

import javax.swing.*;
import java.awt.*;

public class AntWorld extends JFrame {
    public AntWorld(int size) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ant World");
        this.setSize(new Dimension(size, size));
        this.setLocationRelativeTo(null);
    }
}
