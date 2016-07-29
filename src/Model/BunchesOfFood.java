package Model;


import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BunchesOfFood {
    ArrayList<BunchOfFood> bunches = new ArrayList<>();
    private BunchOfFood bunche;
    private int totalFood;

    public BunchesOfFood (int nb_bunches, Rectangle anthill, int world_size) {
        for (int i = 0; i < nb_bunches; i++) {
            Rectangle rect = new Rectangle(
                    ThreadLocalRandom.current().nextInt(0, world_size + 1),
                    ThreadLocalRandom.current().nextInt(0, world_size + 1),
                    30,
                    30
            );
            while (anthill.intersects(rect)) {
                rect.setLocation(
                    ThreadLocalRandom.current().nextInt(0, world_size + 1),
                    ThreadLocalRandom.current().nextInt(0, world_size + 1)
                );
            }
            this.bunches.add(new BunchOfFood(rect));
        }
    }

    public ArrayList<BunchOfFood> getBunches() {
        return bunches;
    }

    public void setBunches(ArrayList<BunchOfFood> bunches) {
        this.bunches = bunches;
    }

    boolean intersects (Rectangle coordinates) {
        for (BunchOfFood bunch : this.bunches) {
            if (bunch.getArea().intersects(coordinates)) {
                return true;
            }
        }
        return false;
    }

    public int getTotalFood(){
        for (BunchOfFood bunch : this.bunches){
            totalFood = totalFood + bunch.getFoodQuantity();
        }
        return totalFood;
    }

    BunchOfFood getBunch(Rectangle coordinates) {
        for (BunchOfFood bunch : this.bunches) {
            if (bunch.getArea().intersects(coordinates)) {
                return bunch;
            }
        }
        return new BunchOfFood(new Rectangle());
    }
}
