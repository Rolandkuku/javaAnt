import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    private int size;
    private Ant ant;
    private BunchOfFood bunchOfFood;
    private AntHill antHill;
    private Obstacle obstacle;
    private ItemArea foodArea;

    public Simulation() {
        this.size = 300;
        this.ant = new Ant(false, new Point(150, 150), this.size);
        this.bunchOfFood = new BunchOfFood(160, 160, 100);
        this.foodArea = new ItemArea(
                new Point(
                        this.bunchOfFood.getPosX(),
                        this.bunchOfFood.getPosY()
                ),
                30
        );
        this.antHill = new AntHill(10, 100, 0);
        this.obstacle = new Obstacle(100, 100, 50);
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

    public void nextStep() {
        this.ant.randomDirection();
        if (this.foodArea.getArea().contains(this.ant.getPosition())) {
            this.ant.setCarryingFood(true);
            System.out.println("FOOD\n");
        }
    }
}

class Ant {
    private boolean carryingFood = false;
    private Point position;
    private int worldSize;
    private ArrayList<Point> visitedCoordinates = new ArrayList<>();

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

    public void goBackHome() {

    }

    public void randomDirection() {
        double randY = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        double randX = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        Point newCoordinates = new Point(0, 0);
        double posX = 0;
        double posY = 0;

        if (Math.round(randX) == 0) {
            posX = this.position.getX() - 5;
        } else if (Math.round(randX) == 2) {
            posX = this.position.getX() + 5;
            //newCoordinates.setX(this.posX + 5);
        } else {
            posX = this.position.getX();
        }

        if (Math.round(randY) == 0) {
            posY = this.position.getY() - 5;
            //newCoordinates.setY(this.posY - 5);
        } else if (Math.round(randY) == 2) {
            posY = this.position.getY() + 5;
            //newCoordinates.setY(this.posY + 5);
        } else {
            posX = this.position.getX();
        }
        //!this.visitedCoordinates.contains(newCoordinates)
        if (true) { // If location has not been visited yet
            if (posX > 5 && posX <= (this.worldSize - 5) && posY > 5 && posY <= (this.worldSize - 5)) {
                newCoordinates.setLocation(posX, posY);
                this.setPosition(newCoordinates);
            }
            this.visitedCoordinates.add(newCoordinates);
        }
    }

}

class BunchOfFood {
    private int posX;
    private int posY;
    private int foodQuantity;

    public BunchOfFood(int posX, int posY, int foodQuantity) {
        this.posX = posX;
        this.posY = posY;
        this.foodQuantity = foodQuantity;
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

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
}

class AntHill {
    private int posX;
    private int posY;
    private int foodQuantity;

    public AntHill(int posX, int posY, int foodQuantity) {
        this.posX = posX;
        this.posY = posY;
        this.foodQuantity = foodQuantity;
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

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
}

class pheromones {
    private int duration;

    public pheromones(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

/**
 * Contain all the coordinates for a given element
 */
class ItemArea {
    private ArrayList<Point> area = new ArrayList<>();

    public ItemArea(Point coordinates, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                Point newCoordinates = new Point();
                newCoordinates.setLocation((coordinates.getX() + i), (coordinates.getY() + j));
                area.add(newCoordinates);
            }
        }
    }

    public ArrayList<Point> getArea() {
        return area;
    }
}
