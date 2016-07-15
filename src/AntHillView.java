import javax.swing.*;
import java.awt.*;

public class AntHillView {
    Simulation simulation = new Simulation();
    AntWorld antWorld = new AntWorld(simulation.getSize());
    AntPlayground antPlayground = new AntPlayground(simulation.getSize());

    public AntHillView() {
        this.antWorld.setContentPane(this.antPlayground);
        this.antWorld.setVisible(true);
        antPlayground.repaint();
    }
}

class AntWorld extends JFrame {
    public AntWorld(int size) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ant World");
        this.setSize(new Dimension(size, size));
        this.setLocation(100, 100);
    }
}

class AntPlayground extends JPanel {

    public AntPlayground (int size) {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(size, size));
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, 20, 20);
    }
}
