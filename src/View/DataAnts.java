package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataAnts extends JPanel{
    private Simulation simulation;

    public DataAnts (Simulation simulation) {
        this.simulation = simulation;
        this.setLayout(new GridLayout(10,0));
        this.setPreferredSize(new Dimension(this.simulation.getSize(), this.simulation.getSize()));
    }

    public void formView(){
        //Ne s'affiche pas
        //Title
        JLabel myTitleLabel = new JLabel("Java : Les fourmis (données)");
        this.add(myTitleLabel);
        myTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel jlabel = new JLabel("Nourriture restante : " + simulation.getBunchesOfFood().getTotalFood());
        this.add(jlabel);

        JLabel jlabelFoodHill = new JLabel("Nourriture récupérée : " + simulation.getAntHill().getFoodQuantity());
        this.add(jlabelFoodHill);
    }
}
