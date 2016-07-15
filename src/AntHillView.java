import javax.swing.*;
import java.awt.*;

public class AntHillView {
    Simulation simulation;
    AntWorld antWorld;
    AntPlayground antPlayground;

    public AntHillView(Simulation simulation) {
        this.simulation = simulation;
        this.antWorld = new AntWorld(simulation.getSize());
        this.antPlayground = new AntPlayground(simulation);
        this.antWorld.setContentPane(this.antPlayground);
        this.antWorld.setVisible(true);
    }

    public void paint() {
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
    private Simulation simulation;

    public AntPlayground (Simulation simulation) {
        this.simulation = simulation;
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(
                this.simulation.getSize(),
                this.simulation.getSize()));
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(
                this.simulation.getAnt().getPosX(),
                this.simulation.getAnt().getPosY(),
                20,
                20
        );
    }
}
