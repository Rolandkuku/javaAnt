package Controller;

import Model.*;
import View.DataAnts;

import javax.swing.*;

public class Controller {

    //Start func
    public void start() {
        Configuration cfg = new Configuration();

        View.IndexView indexView = new View.IndexView(cfg);
        indexView.formView();

        if(cfg.isValid()) {
            Simulation simulation = new Simulation(cfg);
            View.AntHillView antHillView = new View.AntHillView(simulation);
            antHillView.dataView();

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
