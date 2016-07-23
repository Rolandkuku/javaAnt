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
        this.ants = new Ants(this.size);
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
        this.ants.move(this.pheromones, this.antHill, this.bunchOfFood);
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

        Pheromone pheromoneDirection = new Pheromone(0, new Point(0, 0));
        Point newCoordinates = new Point(0, 0);

        // Array with all possible directions
        ArrayList<Point> directions = new ArrayList<>();
        Rectangle rectangle = new Rectangle();
        double currentX = this.getPosition().getX();
        double currentY = this.getPosition().getY();
        rectangle.setLocation((int) currentX - 20, (int) currentY -20);
        rectangle.setSize(40, 40);

        // Iterate over each direction
        for (Pheromone pheromone: pheromones) {
            if (rectangle.contains(pheromone.getPheromoneArea()) && pheromone.getDuration() > pheromoneDirection.getDuration()) {
                pheromoneDirection = pheromone;
            }
        }

        Point pheromoneLocation = pheromoneDirection.getPheromoneArea().getLocation();
        if (pheromoneLocation.getX() != 0) {
            while (newCoordinates.distanceSq(pheromoneLocation) > this.getPosition().distanceSq(pheromoneLocation)) {
                newCoordinates = randomDirection();
            }
        }



        if (pheromoneLocation.getX() == 0) {
            return this.randomDirection();
        } else {
            System.out.println("DIRECTION\n");
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
                amountPheromones = 1000;
            } else {
                amountPheromones =300;
            }
            Pheromone newPheromone = new Pheromone(amountPheromones, new Point(this.getPosition()));
            pheromones.add(newPheromone);
        }
    }
}

class Ants {
    private ArrayList<Ant> ants = new ArrayList<>();

    public Ants (int worldSize) {
        for (int i = 0; i < 30; i ++) {
            Ant newAnt = new Ant(
                    false,
                    new Point(
                        ThreadLocalRandom.current().nextInt(100, 150 + 1),
                        ThreadLocalRandom.current().nextInt(100, 150 + 1)
                    ),
                    worldSize
            );
            this.ants.add(newAnt);
        }
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }

    public void move(ArrayList<Pheromone> pheromones, AntHill antHill, BunchOfFood food) {
        for (Ant ant: this.ants) {
            if (ant.isCarryingFood()) {
                ant.goBackHome(antHill.getPosition());
                if (food.getArea().contains(ant.getPosition())) {
                    ant.dropPheromone(pheromones, true);
                } else {
                    ant.dropPheromone(pheromones, false);
                }
                if (antHill.getArea().contains(ant.getPosition())) {
                    antHill.addFood();
                    ant.setCarryingFood(false);
                }
            } else {
                // If not, it goes around randomly
                ant.lookForFood(pheromones);
                if (food.getArea().contains(ant.getPosition())) {
                    food.removeFood();
                    food.setSize();
                    ant.setCarryingFood(true);
                }
            }
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
    private Rectangle pheromoneArea;

    public Pheromone(int duration, Point position) {

        this.duration = duration;
        this.pheromoneArea = new Rectangle(position, new Dimension(5, 5));
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
