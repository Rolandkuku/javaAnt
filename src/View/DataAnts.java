package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataAnts extends JPanel{
    private Simulation simulation;

    public DataAnts (Simulation simulation) {
        this.simulation = simulation;
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(this.simulation.getSize(), this.simulation.getSize()));
    }

    public void formView(){
        //Ne s'affiche pas
        JLabel jlabel = new JLabel("This is a label");
        this.add(jlabel);
    }
}
