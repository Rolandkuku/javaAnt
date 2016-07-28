import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

class Simulation {
    private int size;
    private Ants ants;
    private BunchOfFood bunchOfFood;
    private AntHill antHill;
    private Obstacle obstacle;
    private ArrayList<Pheromone> pheromones = new ArrayList<>();

    Simulation() {
        this.size = 300;
        this.ants = new Ants(this.size, 1);
        this.bunchOfFood = new BunchOfFood(new Point(150, 150), 30);
        this.antHill = new AntHill(new Point(10, 200), 0, 50);
        this.obstacle = new Obstacle(new Rectangle(new Point(80, 170)), 50);
    }

    ArrayList<Pheromone> getPheromones() {
        return pheromones;
    }

    int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    Ants getAnts() {
        return ants;
    }

    void setAnts(Ants ants) {
        this.ants = ants;
    }

    BunchOfFood getBunchOfFood() {
        return bunchOfFood;
    }

    public void setBunchOfFood(BunchOfFood bunchOfFood) {
        this.bunchOfFood = bunchOfFood;
    }

    AntHill getAntHill() {
        return antHill;
    }

    public void setAntHill(AntHill antHill) {
        this.antHill = antHill;
    }

    Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    void updatePheromones() {
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
    void nextStep() {
        this.updatePheromones(); // Decrease duration of all pheromones
        // If the ant has found any food, it goes back to the antHill
        this.ants.move(this.pheromones, this.antHill, this.bunchOfFood, this.obstacle);
    }
}

class Ant {
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

    boolean isCarryingFood() {
        return carryingFood;
    }

    Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    void setCarryingFood(boolean carryingFood) {
        this.carryingFood = carryingFood;
    }

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Assign ant a new postion if closest to antHill
     * @param antHillPosition
     */
    void goBackHome(Point antHillPosition, Rectangle obstacle) {
        Point newCoordinates = new Point(0, 0);
        while (
                newCoordinates.distanceSq(antHillPosition) >= this.position.distanceSq(antHillPosition) ||
                newCoordinates.getX() == 0 || obstacle.contains(newCoordinates)
                ) {
            newCoordinates.setLocation(randomDirection());
        }
        this.position.setLocation(newCoordinates);
    }

    /**
     * Go around randomly
     */
    void lookForFood(ArrayList<Pheromone> pheromones, Rectangle obstacle) {
        Point newCoordinates = this.lookForPheromone(pheromones, obstacle);
        this.position.setLocation(newCoordinates);
    }

    Point lookForPheromone(ArrayList<Pheromone> pheromones, Rectangle obstacle) {

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
            while (
                    newCoordinates.distanceSq(pheromoneLocation) > this.getPosition().distanceSq(pheromoneLocation) ||
                    obstacle.contains(newCoordinates)) {
                newCoordinates = randomDirection();
            }
            if (newCoordinates.equals(this.getPosition())) {
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

    void dropPheromone(ArrayList<Pheromone> pheromones, boolean onFood) {
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

class Ants {
    private ArrayList<Ant> ants = new ArrayList<>();

    Ants(int worldSize, int nbAnts) {
        for (int i = 0; i < nbAnts; i ++) {
            Ant newAnt = new Ant(
                    i,
                    false,
                    new Point(
                        ThreadLocalRandom.current().nextInt(150, 155 + 1),
                        ThreadLocalRandom.current().nextInt(150, 155 + 1)
                    ),
                    worldSize
            );
            this.ants.add(newAnt);
        }
    }

    ArrayList<Ant> getAnts() {
        return ants;
    }

    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }

    void move(ArrayList<Pheromone> pheromones, AntHill antHill, BunchOfFood food, Obstacle obstacle) {
        for (Ant ant: this.ants) {
            if (ant.isCarryingFood()) {
                ant.goBackHome(antHill.getPosition(), obstacle.getArea());
                while (!this.freePosition(ant, this.getAnts())) { // no ant collision && avoid obstacle
                    ant.goBackHome(antHill.getPosition(), obstacle.getArea());
                }
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
                // If not, it looks for pheromones
                ant.lookForFood(pheromones, obstacle.getArea());
                while (!freePosition(ant, this.getAnts())) {
                    ant.lookForFood(pheromones, obstacle.getArea());
                }
                if (food.getArea().contains(ant.getPosition())) {
                    food.removeFood();
                    food.setSize();
                    ant.setCarryingFood(true);
                }
            }
        }
    }

    private boolean freePosition(Ant ant1, ArrayList<Ant> ants) {
        boolean free = true;
        for (Ant ant2: ants) {
            if (ant2.getPosition().equals(ant1.getPosition()) && ant1.getId() != ant2.getId()) {
                free = false;
            }
        }
        return free;
    }
}

class BunchOfFood {
    private Point position;
    private int foodQuantity;
    private Rectangle area;


    BunchOfFood(Point position, int foodQuantity) {
        this.position = position;
        this.foodQuantity = foodQuantity;
        this.area = new Rectangle(
                (int) this.position.getX(),
                (int) this.position.getY(),
                foodQuantity,
                foodQuantity
        );
    }

    Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    Point getPosition() {
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

    void setSize() {
        this.area.setSize(this.foodQuantity, this.foodQuantity);
    }

    void removeFood() {
        this.foodQuantity -= 1;
    }
}

class AntHill {
    private Point position;
    private Rectangle area;
    private int foodQuantity;

    AntHill(Point position, int foodQuantity, int size) {
        this.position = position;
        this.foodQuantity = foodQuantity;
        this.area = new Rectangle(
                (int) this.position.getX(),
                (int) this.position.getY(),
                size,
                size
        );
    }

    Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public void setSize() {
        this.area.setSize(foodQuantity, foodQuantity);
    }

    Point getPosition() {
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

    void addFood() {
        this.foodQuantity += 1;
    }
}

class Pheromone {
    private int duration;
    private int age;
    private Rectangle pheromoneArea;

    Pheromone(int duration, Point position) {
        this.age = 0;
        this.duration = duration;
        this.pheromoneArea = new Rectangle(position, new Dimension(5, 5));
    }

    int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    Rectangle getPheromoneArea() {
        return pheromoneArea;
    }

    public void setPheromoneArea(Rectangle pheromoneArea) {
        this.pheromoneArea = pheromoneArea;
    }

    void increaseDuration() {
        this.duration += 300;
    }

    private void decreaseDuration() {
        this.duration -= 1;
    }

    void update() {
        this.decreaseDuration();
        this.age++;
    }
}

class Obstacle {
    Rectangle area;
    private int size;

    Obstacle(Rectangle area, int size) {
        this.size = size;
        this.area = area;
        this.area.setSize(this.size, this.size);
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
