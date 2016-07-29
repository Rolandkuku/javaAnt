package Model;

import java.awt.*;

public class AntHill {
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

    public void addFood() {
        this.foodQuantity += 1;
    }
}
