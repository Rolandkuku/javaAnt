package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AntPlayground extends JPanel {
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
        Ants ants = this.simulation.getAnts();
        BunchOfFood bunchOfFood = this.simulation.getBunchOfFood();
        Obstacles obstacle = this.simulation.getObstacles();
        ArrayList<Pheromone> pheromones = this.simulation.getPheromones();

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
        for (Obstacle obs : this.simulation.getObstacles().getObstacles()) {
            g.fillRect(
                    (int) obs.getArea().getLocation().getX(),
                    (int) obs.getArea().getLocation().getY(),
                    (int) obs.getArea().getWidth(),
                    (int) obs.getArea().getHeight()
            );
        }
        // Ant
        g.setColor(Color.BLACK);
        for (Ant ant: ants.getAnts()) {
            g.fillOval(
                    (int) ant.getPosition().getX(),
                    (int) ant.getPosition().getY(),
                    5,
                    5
            );
        }
        // Pheromones
        for (Pheromone pheromone: pheromones) {
            int duration = pheromone.getDuration();
            if (duration > 50) {
                g.setColor(Color.darkGray);
            }
            if (duration > 25 && duration <= 50) {
                g.setColor(Color.GRAY);
            }
            if (duration <= 25) {
                g.setColor(Color.lightGray);
            }
            g.fillOval(
                    (int) pheromone.getPheromoneArea().getLocation().getX(),
                    (int) pheromone.getPheromoneArea().getLocation().getY(),
                    5,
                    5
            );
        }
    }
}