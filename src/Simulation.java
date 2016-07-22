import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    private int size;
    private Ant ant;
    private BunchOfFood bunchOfFood;
    private AntHill antHill;
    private Obstacle obstacle;

    public Simulation() {
        this.size = 400;
        this.ant = new Ant(false, 250, 250, this.size);
        this.bunchOfFood = new BunchOfFood(300, 300, 100);
        this.antHill = new AntHill(10, 100, 0);
        this.obstacle = new Obstacle(200, 200, 50);
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
    }
}

class Ant {
    private boolean carryingFood = false;
    private int posY;
    private int posX;
    private int worldSize;
    private ArrayList<Coordinates> visitedCoordinates = new ArrayList<>();

    public Ant(boolean carryingFood, int posY, int posX, int worldSize) {
        this.carryingFood = carryingFood;
        this.posY = posY;
        this.posX = posX;
        this.worldSize = worldSize;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isCarryingFood() {
        return carryingFood;
    }

    public void setCarryingFood(boolean carryingFood) {
        this.carryingFood = carryingFood;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void randomDirection() {
        double randY = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        double randX = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        Coordinates newCoordinates = new Coordinates();

        if (Math.round(randX) == 0) {
            newCoordinates.setX(this.posX - 5);
        } else if (Math.round(randX) == 2) {
            newCoordinates.setX(this.posX + 5);
        }

        if (Math.round(randY) == 0) {
            newCoordinates.setY(this.posY - 5);
        } else if (Math.round(randY) == 2) {
            newCoordinates.setY(this.posY + 5);
        }
        //!this.visitedCoordinates.contains(newCoordinates)
        if (true) { // If location has not been visited yet
            if (newCoordinates.getX() > 5 && newCoordinates.getX() <= this.worldSize - 5) {
                this.setPosX(newCoordinates.getX());
            }
            if (newCoordinates.getY() > 5 && newCoordinates.getY() <= this.worldSize - 5) {
                this.setPosY(newCoordinates.getY());
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

class Coordinates {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
