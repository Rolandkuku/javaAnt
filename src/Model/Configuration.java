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
    private int obstacleSize;
    private Point bunchOfFoodPosition;

    //Form validation
    private boolean isValid;

    //Constructeur avec params
    public Configuration(Point bunchOfFoodPosition, int windowSize,int numberOfAnts, int bunchOfFoodQuantity, int numberOfObstacle, int AntHillQuantity, int AntHillSize, int obstacleSize ){
        this.windowSize = windowSize;
        this.numberOfAnts = numberOfAnts;
        this.bunchOfFoodQuantity = bunchOfFoodQuantity;
        this.numberOfObstacle = numberOfObstacle;
        this.AntHillQuantity = AntHillQuantity;
        this.AntHillSize = AntHillSize;
        this.obstacleSize = obstacleSize;
        this.bunchOfFoodPosition = bunchOfFoodPosition;
    }

    //Constructeur vide
    public Configuration(){

    }

    //Setter
    public void setIsValid(boolean isValid){ this.isValid = isValid; }

    public void setObstacleSize(int obstacleSize){
        this.obstacleSize = obstacleSize;
    }

    public void setWindowSize(int windowSize){
        this.windowSize = windowSize;
    }

    public void setNumberOfAnts(int numberOfAnts){
        this.numberOfAnts = numberOfAnts;
    }

    public void setBunchOfFoodPosition(Point bunchOfFoodPosition){
        this.bunchOfFoodPosition = bunchOfFoodPosition;
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
    public boolean isValid() { return this.isValid; }

    public Point getBunchOfFoodPosition(){ return this.bunchOfFoodPosition; }
    public int getObstacleSize(){
        return this.obstacleSize;
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
