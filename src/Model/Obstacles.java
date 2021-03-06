package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Obstacles {
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Obstacles(int world_size, int nb_obstacles, Rectangle anthill, BunchesOfFood foods) {
        for (int i = 0; i < nb_obstacles; i++) {
            Rectangle rect = new Rectangle(
                            ThreadLocalRandom.current().nextInt(0, world_size + 1),
                            ThreadLocalRandom.current().nextInt(0, world_size + 1),
                            10,
                            10
                    );
            while (anthill.intersects(rect) || foods.intersects(rect)) {
                rect.setLocation(
                        ThreadLocalRandom.current().nextInt(0, world_size + 1),
                        ThreadLocalRandom.current().nextInt(0, world_size + 1));
            }
            Obstacle obstacle = new Obstacle(
                    rect
            );
            this.obstacles.add(obstacle);
        }
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    boolean intersects (Rectangle coordinates) {
        for (Obstacle obs : this.obstacles) {
            if (obs.getArea().intersects(coordinates)) {
                return true;
            }
        }
        return false;
    }
}
