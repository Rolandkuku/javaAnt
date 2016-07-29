package Model;

import java.awt.*;
import java.util.ArrayList;

public class Obstacle {
    Rectangle area;
    private int size;

    Obstacle(Rectangle area) {
        this.area = area;
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
