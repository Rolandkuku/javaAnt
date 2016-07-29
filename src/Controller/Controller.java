package Controller;
import View.*;
import Model.*;

public class Controller {

    public void start() {
        FormView myView = new FormView();

        if(myView.getIsValid()) {

            Simulation simulation = new Simulation();
            simulation.setSize(myView.getWindowSize());
            simulation.setAnts(new Ants(simulation.getSize(), myView.getNumberOfAnts()));

            View.AntHillView antHillView = new View.AntHillView(simulation);
            while (true) {
                simulation.nextStep();
                antHillView.paint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
