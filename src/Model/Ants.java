package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Ants {
    private ArrayList<Ant> ants = new ArrayList<>();

    public Ants(int worldSize, int nbAnts) {
        for (int i = 0; i < nbAnts; i ++) {
            Ant newAnt = new Ant(
                    i,
                    false,
                    new Point(
                            ThreadLocalRandom.current().nextInt(150, 170 + 1),
                            ThreadLocalRandom.current().nextInt(150, 170 + 1)
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

    public void move(ArrayList<Pheromone> pheromones, AntHill antHill, BunchesOfFood foods, Obstacles obstacles) {
        for (Ant ant: this.ants) {
            if (ant.isCarryingFood()) {
                ant.goBackHome(antHill.getPosition(), obstacles);
                while (this.intersects(ant)) { // no ant collision && avoid obstacle
                    ant.goBackHome(antHill.getPosition(), obstacles);
                }
                if (foods.contains(ant.getArea())) {
                    ant.dropPheromone(pheromones, true);
                    ant.markFood(pheromones, foods.getBunch(ant.getArea()));
                } else {
                    ant.dropPheromone(pheromones, false);
                }
                if (antHill.getArea().intersects(ant.getArea())) {
                    antHill.addFood();
                    ant.setCarryingFood(false);
                }
            } else {
                // If not, it looks for pheromones
                ant.lookForFood(this, pheromones, obstacles);
                int testedPosition = 0;
                while (this.intersects(ant) && testedPosition < 9) {
                    ant.lookForFood(this, pheromones, obstacles);
                    testedPosition++;
                }
                if (foods.contains(ant.getArea())) {
                    BunchOfFood bunch = foods.getBunch(ant.getArea());
                    bunch.removeFood();
                    bunch.setSize();
                    ant.setCarryingFood(true);
                }
            }
        }
    }

    public boolean freePosition(Ant ant1, ArrayList<Ant> ants) {
        boolean free = true;
        for (Ant ant2: ants) {
            if (ant2.getArea().getLocation().equals(ant1.getArea().getLocation()) && ant1.getId() != ant2.getId()) {
                free = false;
            }
        }
        return free;
    }

    boolean intersects (Ant ant1) {
        for (Ant ant2 : this.ants) {
            if (ant2.getArea().intersects(ant1.getArea()) && ant1.getId() != ant2.getId()) {
                return true;
            }
        }
        return false;
    }
}
