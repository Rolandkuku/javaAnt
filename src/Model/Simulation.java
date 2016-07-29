package Model;
import Controller.*;
import View.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    private int size;
    private Ants ants;
    private BunchOfFood bunchOfFood;
    private AntHill antHill;
    private Obstacle obstacle;
    private ArrayList<Pheromone> pheromones = new ArrayList<>();

    public Simulation() {
        this.size = 300;
        this.ants = new Ants(this.size, 1);
        this.bunchOfFood = new BunchOfFood(new Point(150, 150), 30);
        this.antHill = new AntHill(new Point(10, 200), 0, 50);
        this.obstacle = new Obstacle(new Rectangle(new Point(80, 170)), 50);
    }

    public ArrayList<Pheromone> getPheromones() {
        return pheromones;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Ants getAnts() {
        return ants;
    }

    public void setAnts(Ants ants) {
        this.ants = ants;
    }

    public BunchOfFood getBunchOfFood() {
        return bunchOfFood;
    }

    public void setBunchOfFood(BunchOfFood bunchOfFood) {
        this.bunchOfFood = bunchOfFood;
    }

    public AntHill getAntHill() {
        return antHill;
    }

    public void setAntHill(AntHill antHill) {
        this.antHill = antHill;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public void updatePheromones() {
        for (Iterator<Pheromone> iterator = this.pheromones.iterator(); iterator.hasNext();) {
            Pheromone pheromone = iterator.next();
            pheromone.update();
            if (pheromone.getDuration() == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * Next step of the simulation = the next frame
     */
    public void nextStep() {
        this.updatePheromones(); // Decrease duration of all pheromones
        // If the ant has found any food, it goes back to the antHill
        this.ants.move(this.pheromones, this.antHill, this.bunchOfFood, this.obstacle);
    }
}
