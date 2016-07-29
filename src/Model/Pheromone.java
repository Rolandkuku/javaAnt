package Model;

import java.awt.*;

public class Pheromone {
    private int duration;
    private int age;
    private Rectangle pheromoneArea;

    Pheromone(int duration, Point position) {
        this.age = 0;
        this.duration = duration;
        this.pheromoneArea = new Rectangle(position, new Dimension(5, 5));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Rectangle getPheromoneArea() {
        return pheromoneArea;
    }

    public void setPheromoneArea(Rectangle pheromoneArea) {
        this.pheromoneArea = pheromoneArea;
    }

    public void increaseDuration() {
        this.duration += 100;
    }

    private void decreaseDuration() {
        this.duration -= 1;
    }

    public void update() {
        this.duration--;
        this.age++;
    }
}

