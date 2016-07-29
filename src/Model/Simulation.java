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
    private Configuration cfg;
    private BunchesOfFood bunchesOfFood;
    private AntHill antHill;
    private Obstacles obstacles;
    private ArrayList<Pheromone> pheromones = new ArrayList<>();

    //Constructeur avec la Configuration
    public Simulation(Configuration cfg){
        this.size = cfg.getWindowSize();
        this.ants = new Ants(this.size, cfg.getNumberOfAnts());
        this.antHill = new AntHill(new Point(10, 200), cfg.getAntHillQuantity(), cfg.getAntHillSize());
        this.bunchesOfFood = new BunchesOfFood(cfg.getBunchOfFoodQuantity(), this.antHill.getArea(), cfg.getWindowSize());
        this.obstacles = new Obstacles(
                cfg.getWindowSize(),
                cfg.getNumberOfObstacle(),
                this.antHill.getArea(),
                this.bunchesOfFood);
    }

    //Constructeur par default
    public Simulation() {
        this.size = 300;
        this.ants = new Ants(this.size, 1);
        //this.bunchOfFood = new BunchOfFood(new Point(150, 150), 30);
        this.antHill = new AntHill(new Point(10, 200), 0, 50);
        //this.obstacles = new Obstacle(new Rectangle(new Point(80, 170)), 50);
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

    public BunchesOfFood getBunchesOfFood() {
        return bunchesOfFood;
    }

    public void setBunchesOfFood(BunchesOfFood bunchesOfFood) {
        this.bunchesOfFood = bunchesOfFood;
    }

    public AntHill getAntHill() {
        return antHill;
    }

    public void setAntHill(AntHill antHill) {
        this.antHill = antHill;
    }

    public Obstacles getObstacles() {

        return obstacles;
    }

    public void setObstacles(Obstacles obstacles) {
        this.obstacles = obstacles;
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
        this.ants.move(this.pheromones, this.antHill, this.bunchesOfFood, this.obstacles);
    }
}
