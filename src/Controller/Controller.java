package Controller;

import Model.*;

public class Controller {

    //Start func
    public void start() {
        Configuration cfg = new Configuration();

        View.IndexView indexView = new View.IndexView(cfg);

        indexView.formView();

        Simulation simulation = new Simulation(cfg);
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
