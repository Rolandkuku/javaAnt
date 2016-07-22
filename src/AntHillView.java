import javax.swing.*;
import java.awt.*;

public class AntHillView {
    private Simulation simulation;
    private AntWorld antWorld;
    private AntPlayground antPlayground;

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
        AntHill antHill = this.simulation.getAntHill();
        Ant ant = this.simulation.getAnt();
        BunchOfFood bunchOfFood = this.simulation.getBunchOfFood();
        Obstacle obstacle = this.simulation.getObstacle();

        // AntHill
        g.setColor(Color.GREEN);
        g.fillRect(
                (int) antHill.getPosition().getX(),
                (int) antHill.getPosition().getY(),
                antHill.getArea().width,
                antHill.getArea().height
        );
        // Bunch of Food
        g.setColor(Color.BLUE);
        g.fillRect(
                (int) bunchOfFood.getPosition().getX(),
                (int) bunchOfFood.getPosition().getY(),
                bunchOfFood.getArea().width,
                bunchOfFood.getArea().height
        );
        // Obstacle
        g.setColor(Color.RED);
        g.fillRect(
                obstacle.getPosX(),
                obstacle.getPosY(),
                20,
                20
        );
        // Ant
        g.setColor(Color.BLACK);
        g.fillOval(
                (int) ant.getPosition().getX(),
                (int) ant.getPosition().getY(),
                5,
                5
        );
    }
}
