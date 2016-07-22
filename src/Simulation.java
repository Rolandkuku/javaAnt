import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    private int size;
    private Ant ant;
    private BunchOfFood bunchOfFood;
    private AntHill antHill;
    private Obstacle obstacle;
    private ArrayList<Pheromone> pheromones = new ArrayList<>();

    public Simulation() {
        this.size = 300;
        this.ant = new Ant(false, new Point(150, 150), this.size);
        this.bunchOfFood = new BunchOfFood(new Point(150, 150), 30);
        this.antHill = new AntHill(new Point(10, 200), 0, 50);
        this.obstacle = new Obstacle(100, 100, 50);
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

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
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
            pheromone.decreaseDuration();
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
        if (this.ant.isCarryingFood()) {
            this.ant.goBackHome(this.antHill.getPosition());
            this.ant.dropPheromone(this.pheromones);
            if (this.antHill.getArea().contains(this.ant.getPosition())) {
                this.antHill.addFood();
                this.ant.setCarryingFood(false);
            }
        } else {
            // If not, it goes around randomly
            this.ant.lookForFood(this.pheromones);
            if (this.bunchOfFood.getArea().contains(this.ant.getPosition())) {
                this.bunchOfFood.removeFood();
                this.bunchOfFood.setSize();
                this.ant.setCarryingFood(true);
            }
        }
    }
}

class Ant {
    private boolean carryingFood = false;
    private Point position;
    private int worldSize;

    public Ant(boolean carryingFood, Point position, int worldSize) {
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

    /**
     * Assign ant a new postion if closest to antHill
     * @param antHillPosition
     */
    public void goBackHome(Point antHillPosition) {
        Point newCoordinates = new Point(0, 0);
        while (
                newCoordinates.distanceSq(antHillPosition) >= this.position.distanceSq(antHillPosition) ||
                newCoordinates.getX() == 0
                ) {
            newCoordinates.setLocation(randomDirection());
        }
        this.position.setLocation(newCoordinates);
    }

    /**
     * Go around randomly
     */
    public void lookForFood(ArrayList<Pheromone> pheromones) {
        Point newCoordinates = this.lookForPheromone(pheromones);
        this.position.setLocation(newCoordinates);
    }

    public Point lookForPheromone(ArrayList<Pheromone> pheromones) {

        Point pheromoneDirection = new Point();

        // Array with all possible directions
        ArrayList<Point> directions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Point direction = new Point(i, j);
                if (!this.getPosition().equals(direction)) { // We don't want the current direction
                    directions.add(new Point(i, j));
                }
            }
        }

        // Iterate over each direction
        for (Iterator<Point> iterator = directions.iterator(); iterator.hasNext();) {
            Point direction = iterator.next();
            boolean empty_direction = true;
            int pheromoneDuration = 0;
            // Iterate over all pheromones
            for (Pheromone pheromone: pheromones) {
                // If there is a pheromone in that direction
                if (pheromone.getPosition().equals(direction)) {
                    empty_direction = false;
                    // If first try
                    if (pheromoneDuration == 0) {
                        pheromoneDirection.setLocation(pheromone.getPosition());
                        pheromoneDuration = pheromone.getDuration();
                    } else if (pheromone.getDuration() < pheromoneDuration){ // We want the older pheromone
                        pheromoneDirection.setLocation(pheromone.getPosition());
                        pheromoneDuration = pheromone.getDuration();
                    }
                }
            }
        }
        if (pheromoneDirection.getX() > 5) {
            System.out.printf(pheromoneDirection.getLocation() + "\n");
            return pheromoneDirection;
        } else {
            return this.randomDirection();
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

    public void dropPheromone(ArrayList<Pheromone> pheromones) {
        boolean emptyPosition = true;
        for (Pheromone pheromone: pheromones) {
            if (pheromone.getPosition().equals(this.getPosition())) {
                emptyPosition = false;
                pheromone.increaseDuration();
            }
        }
        if (emptyPosition) {
            Pheromone newPheromone = new Pheromone(300, new Point(this.getPosition()));
            pheromones.add(newPheromone);
        }
    }
}

class BunchOfFood {
    private Point position;
    private int foodQuantity;
    private Rectangle area;


    public BunchOfFood(Point position, int foodQuantity) {
        this.position = position;
        this.foodQuantity = foodQuantity;
        this.area = new Rectangle(
                (int) this.position.getX(),
                (int) this.position.getY(),
                foodQuantity,
                foodQuantity
        );
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public void setSize () {
        this.area.setSize(this.foodQuantity, this.foodQuantity);
    }

    public void removeFood () {
        this.foodQuantity -= 1;
    }
}

class AntHill {
    private Point position;
    private Rectangle area;
    private int foodQuantity;

    public AntHill(Point position, int foodQuantity, int size) {
        this.position = position;
        this.foodQuantity = foodQuantity;
        this.area = new Rectangle(
                (int) this.position.getX(),
                (int) this.position.getY(),
                size,
                size
        );
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public void setSize() {
        this.area.setSize(foodQuantity, foodQuantity);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public void addFood () {
        this.foodQuantity += 1;
    }
}

class Pheromone {
    private int duration;
    private Point position;

    public Pheromone(int duration, Point position) {

        this.duration = duration;
        this.position = position;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void increaseDuration() {
        this.duration += 300;
    }

    public void decreaseDuration () {
        this.duration -= 1;
    }
}

class Obstacle {
    private int posX;
    private int posY;
    private int size;

    public Obstacle(int size, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
