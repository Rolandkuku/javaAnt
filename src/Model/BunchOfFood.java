package Model;


import java.awt.*;

public class BunchOfFood {
    private int foodQuantity;
    private Rectangle area;


    BunchOfFood(Rectangle rect) {
        this.foodQuantity = (int) rect.getWidth();
        this.area = rect;
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
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
