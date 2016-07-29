package Model;

import java.awt.*;

public class Obstacle {
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
