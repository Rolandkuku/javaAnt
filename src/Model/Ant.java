package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Ant {
    private int id;
    private boolean carryingFood = false;
    private Rectangle area;
    private int worldSize;

    Ant(int id, boolean carryingFood, Point position, int worldSize) {
        this.id = id;
        this.carryingFood = carryingFood;
        this.area = new Rectangle((int)position.getX(), (int)position.getY(), 5, 5);
        this.worldSize = worldSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCarryingFood() {
        return carryingFood;
    }

    public void setCarryingFood(boolean carryingFood) {
        this.carryingFood = carryingFood;
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    /**
     * Assign ant a new postion if closest to antHill
     * @param antHillPosition
     */
    public void goBackHome(Point antHillPosition, Obstacles obstacles) {
        Rectangle newCoordinates = new Rectangle(new Point(0, 0));
        newCoordinates.setSize(5, 5);
        int testedDirections = 0;
        while (
                (newCoordinates.getLocation().distanceSq(antHillPosition) >= this.getArea().getLocation().distanceSq(antHillPosition) ||
                newCoordinates.getX() == 0 || obstacles.intersects(newCoordinates)) &&
                testedDirections < 9
                ) {
            newCoordinates.setLocation(randomDirection());
            testedDirections++;
        }
        while (
                (newCoordinates.getX() == 0 || obstacles.intersects(newCoordinates)) &&
                testedDirections == 8
                ) {
            newCoordinates.setLocation(randomDirection());
        }
        this.getArea().setLocation(newCoordinates.getLocation());
    }

    /**
     * Go around randomly
     */
    public void lookForFood(Ants ants, ArrayList<Pheromone> pheromones, Obstacles obstacles) {
        Point newCoordinates = this.lookForPheromone(ants, pheromones, obstacles);
        this.getArea().setLocation(newCoordinates);
    }

    public Point lookForPheromone(Ants ants, ArrayList<Pheromone> pheromones, Obstacles obstacles) {

        Pheromone pheromoneDirection = new Pheromone(0, new Point(0, 0));
        Rectangle newCoordinates = new Rectangle(new Point(0, 0));
        newCoordinates.setSize(5, 5);

        // Array with all possible directions
        ArrayList<Point> directions = new ArrayList<>();
        Rectangle rectangle = new Rectangle();
        double currentX = this.getArea().getX();
        double currentY = this.getArea().getY();
        rectangle.setLocation((int) currentX - 20, (int) currentY -20);
        rectangle.setSize(40, 40);

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
                    (newCoordinates.getLocation().distanceSq(pheromoneLocation) > this.getArea().getLocation().distanceSq(pheromoneLocation) ||
                            obstacles.intersects(newCoordinates)) && testedDirections < 9) {
                newCoordinates.setLocation(randomDirection());
                testedDirections++;
            }
            // If ant fails to find closer direction
            while (obstacles.intersects(newCoordinates) && testedDirections == 8) {
                newCoordinates.setLocation(randomDirection());
            }
        }

        if (pheromoneLocation.getX() == 0) {
            newCoordinates.setLocation(randomDirection());
            while (obstacles.intersects(newCoordinates)) {
                newCoordinates.setLocation(randomDirection());
            }
            return newCoordinates.getLocation();
        } else {
            return newCoordinates.getLocation();
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
                posX = this.getArea().getX() - 5;
            } else if (Math.round(randX) == 2) {
                posX = this.getArea().getX() + 5;
                //newCoordinates.setX(this.posX + 5);
            } else {
                posX = this.getArea().getX();
            }

            if (Math.round(randY) == 0) {
                posY = this.getArea().getY() - 5;
                //newCoordinates.setY(this.posY - 5);
            } else if (Math.round(randY) == 2) {
                posY = this.getArea().getY() + 5;
                //newCoordinates.setY(this.posY + 5);
            } else {
                posY = this.getArea().getY();
            }
        }
        newCoordinates.setLocation(posX, posY);

        return newCoordinates;
    }

    public void dropPheromone(ArrayList<Pheromone> pheromones, boolean onFood) {
        boolean emptyPosition = true;
        for (Pheromone pheromone: pheromones) {
            if (pheromone.getPheromoneArea().intersects(this.getArea())) {
                emptyPosition = false;
                pheromone.increaseDuration();
            }
        }
        if (emptyPosition) {
            int amountPheromones;
            if (onFood) {
                amountPheromones = 100;
            } else {
                amountPheromones = 100;
            }
            Pheromone newPheromone = new Pheromone(amountPheromones, new Point(this.getArea().getLocation()));
            pheromones.add(newPheromone);
        }
    }

    public void markFood(ArrayList<Pheromone> phs, BunchOfFood food) {
        Pheromone ph = new Pheromone(
                900,
                new Point(food.getArea().getLocation())
        );
        phs.add(ph);
    }
}
