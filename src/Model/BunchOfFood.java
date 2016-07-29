package Model;

import java.awt.*;

public class BunchOfFood {
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

    public void setSize() {
        this.area.setSize(this.foodQuantity, this.foodQuantity);
    }

    public void removeFood() {
        this.foodQuantity -= 1;
    }
}
