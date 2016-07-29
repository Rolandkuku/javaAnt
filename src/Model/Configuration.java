package Model;

import java.awt.*;

public class Configuration {
    //Var
    private int windowSize;
    private int numberOfAnts;
    private int bunchOfFoodQuantity;
    private int numberOfObstacle;
    private int AntHillQuantity;
    private int AntHillSize;
    private int rectangleSize;

    //Form validation
    private boolean isValid = true;

    //Constructeur avec params
    public Configuration(int windowSize,int numberOfAnts, int bunchOfFoodQuantity, int numberOfObstacle, int AntHillQuantity, int AntHillSize, int rectangleSize ){
        this.windowSize = windowSize;
        this.numberOfAnts = numberOfAnts;
        this.bunchOfFoodQuantity = bunchOfFoodQuantity;
        this.numberOfObstacle = numberOfObstacle;
        this.AntHillQuantity = AntHillQuantity;
        this.AntHillSize = AntHillSize;
        this.rectangleSize = rectangleSize;

    }

    //Constructeur vide
    public Configuration(){

    }

    //Setter

    public void setRectangleSize(int rectangleSize){
        this.rectangleSize = rectangleSize;
    }

    public void setWindowSize(int windowSize){
        this.windowSize = windowSize;
    }

    public void setNumberOfAnts(int numberOfAnts){
        this.numberOfAnts = numberOfAnts;
    }

    public void setAntHillSize(int AntHillSize){
        this.AntHillSize = AntHillSize;
    }

    public void setBunchOfFoodQuantity(int bunchOfFoodQuantity){
        this.bunchOfFoodQuantity = bunchOfFoodQuantity;
    }

    public void setNumberOfObstacle(int numberOfObstacle){
        this.numberOfObstacle = numberOfObstacle;
    }

    public void setAntHillQuantity(int AntHillQuantity){
        this.AntHillQuantity = AntHillQuantity;
    }


    //Getter
    public int getRectangleSize(){
        return this.rectangleSize;
    }

    public int getWindowSize(){
        return this.windowSize;
    }

    public int getAntHillSize(){
        return this.AntHillSize;
    }

    public int getNumberOfAnts(){
        return this.numberOfAnts;
    }

    public int getBunchOfFoodQuantity() {
        return this.bunchOfFoodQuantity;
    }

    public int getNumberOfObstacle(){
        return this.numberOfObstacle;
    }

    public int getAntHillQuantity(){
        return this.AntHillQuantity;
    }
}
