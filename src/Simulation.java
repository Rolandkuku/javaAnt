import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    private int size;
    private Ant ant;
    private BunchOfFood bunchOfFood;
    private AntHill antHill;
    private Obstacle obstacle;

    public Simulation() {
        this.size = 150;
        this.ant = new Ant(false, 10, 10, this.size);
        this.bunchOfFood = new BunchOfFood(100, 100, 100);
        this.antHill = new AntHill(10, 100, 0);
        this.obstacle = new Obstacle(4, 50, 50);
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

        if (Math.round(randX) == 0) {
            if (this.posX >= 0) {
                this.posX -= 10;
            }
        } else if (Math.round(randX) == 2) {
            if (this.posX <= (this.worldSize - 20)) { // panel size - ant size
                this.posX += 10;
            }
        }

        if (Math.round(randY) == 0) {
            if (this.posY >= 0) {
                this.posY -= 10;
            }
        } else if (Math.round(randY) == 2) {
            if (this.posY <= (this.worldSize - 20)) { // panel size - ant size
                this.posY += 10;
            }
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
