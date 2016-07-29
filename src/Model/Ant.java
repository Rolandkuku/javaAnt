package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Ant {
    int id;
    private boolean carryingFood = false;
    private Point position;
    private int worldSize;

    Ant(int id, boolean carryingFood, Point position, int worldSize) {
        this.id = id;
        this.carryingFood = carryingFood;
        this.position = position;
        this.worldSize = worldSize;
    }

    public boolean isCarryingFood() {
        return carryingFood;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setCarryingFood(boolean carryingFood) {
        this.carryingFood = carryingFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Assign ant a new postion if closest to antHill
     * @param antHillPosition
     */
    public void goBackHome(Point antHillPosition, Obstacles obstacles) {
        Point newCoordinates = new Point(0, 0);
        int testedDirections = 0;
        while (
                (newCoordinates.distanceSq(antHillPosition) >= this.position.distanceSq(antHillPosition) ||
                newCoordinates.getX() == 0 || obstacles.contains(newCoordinates)) &&
                testedDirections < 9
                ) {
            newCoordinates.setLocation(randomDirection());
            testedDirections++;
        }
        while (
                (newCoordinates.getX() == 0 || obstacles.contains(newCoordinates)) &&
                testedDirections == 8
                ) {
            newCoordinates.setLocation(randomDirection());
        }
        this.position.setLocation(newCoordinates);
    }

    /**
     * Go around randomly
     */
    public void lookForFood(ArrayList<Pheromone> pheromones, Obstacles obstacles) {
        Point newCoordinates = this.lookForPheromone(pheromones, obstacles);
        this.position.setLocation(newCoordinates);
    }

    public Point lookForPheromone(ArrayList<Pheromone> pheromones, Obstacles obstacles) {

        Pheromone pheromoneDirection = new Pheromone(0, new Point(0, 0));
        Point newCoordinates = new Point(0, 0);

        // Array with all possible directions
        ArrayList<Point> directions = new ArrayList<>();
        Rectangle rectangle = new Rectangle();
        double currentX = this.getPosition().getX();
        double currentY = this.getPosition().getY();
        rectangle.setLocation((int) currentX - 40, (int) currentY -40);
        rectangle.setSize(80, 80);

        // Iterate over each direction
        for (Pheromone pheromone: pheromones) {
            if (rectangle.contains(pheromone.getPheromoneArea()) && pheromone.getAge() > pheromoneDirection.getAge()) {
                pheromoneDirection = pheromone;
            }
        }

        Point pheromoneLocation = pheromoneDirection.getPheromoneArea().getLocation();
        if (pheromoneLocation.getX() != 0) {
            int testedDirections = 0;
            while (
                    (newCoordinates.distanceSq(pheromoneLocation) > this.getPosition().distanceSq(pheromoneLocation) ||
                            obstacles.contains(newCoordinates)) && testedDirections < 9) {
                newCoordinates = randomDirection();
                testedDirections++;
            }
            // If ant fails to find closer direction
            while (obstacles.contains(newCoordinates) && testedDirections == 8) {
                newCoordinates = randomDirection();
            }
        }

        if (pheromoneLocation.getX() == 0) {
            return this.randomDirection();
        } else {
            return newCoordinates;
        }
    }

    public Point randomDirection() {
        double randY;
        double randX;
        Point newCoordinates = new Point(0, 0);
        double posX = 0;
        double posY = 0;

        while (
                !(posX > 5 &&
                        posX <= (this.worldSize - 5) &&
                        posY > 5 &&
                        posY <= (this.worldSize - 5))
                ) {
            randY = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            randX = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            if (Math.round(randX) == 0) {
                posX = this.position.getX() - 1;
            } else if (Math.round(randX) == 2) {
                posX = this.position.getX() + 1;
                //newCoordinates.setX(this.posX + 5);
            } else {
                posX = this.position.getX();
            }

            if (Math.round(randY) == 0) {
                posY = this.position.getY() - 1;
                //newCoordinates.setY(this.posY - 5);
            } else if (Math.round(randY) == 2) {
                posY = this.position.getY() + 1;
                //newCoordinates.setY(this.posY + 5);
            } else {
                posY = this.position.getY();
            }
        }
        newCoordinates.setLocation(posX, posY);

        return newCoordinates;
    }

    public void dropPheromone(ArrayList<Pheromone> pheromones, boolean onFood) {
        boolean emptyPosition = true;
        for (Pheromone pheromone: pheromones) {
            if (pheromone.getPheromoneArea().contains(this.getPosition())) {
                emptyPosition = false;
                pheromone.increaseDuration();
            }
        }
        if (emptyPosition) {
            int amountPheromones;
            if (onFood) {
                amountPheromones = 30;
            } else {
                amountPheromones = 30;
            }
            Pheromone newPheromone = new Pheromone(amountPheromones, new Point(this.getPosition()));
            pheromones.add(newPheromone);
        }
    }
}
