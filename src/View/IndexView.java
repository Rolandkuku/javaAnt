package View;

import Model.*;
import javax.swing.*;
import java.awt.*;

public class IndexView {
    private Configuration cfg;

    public IndexView(Configuration cfg) {
        this.cfg = cfg;
    }

    public void formView(){
        //Select size
        String[] items = {"Petite", "Moyenne", "Grande"};
        JComboBox combo = new JComboBox(items);

        //Input var
        JTextField numberOfAntsInput = new JTextField("1");
        JTextField bunchOfFoodQuantity = new JTextField("30");
        JTextField numberOfObstacle = new JTextField("50");
        JTextField AntHillQuantity = new JTextField("50");
        JTextField AntHillSize = new JTextField("50");
        JTextField rectangleSize = new JTextField("50");

        //Panel
        JPanel panel = new JPanel(new GridLayout(0,1));

        //Select Size
        panel.add(new JLabel("Taille de la fenêtre :"));
        panel.add(combo);

        //Input Ants number
        panel.add(new JLabel("Nombre de fourmis :"));
        panel.add(numberOfAntsInput);

        //Input Food Quantity
        panel.add(new JLabel("Quantité de nourriture :"));
        panel.add(bunchOfFoodQuantity);

        //Input Nombre Obstacle
        panel.add(new JLabel("Combien d'obstacle :"));
        panel.add(numberOfObstacle);

        //Input Nombre Hill
        panel.add(new JLabel("Nombre de Hill :"));
        panel.add(AntHillQuantity);

        //Input size Obstacle
        panel.add(new JLabel("Taille du Hill"));
        panel.add(AntHillSize);

        //Input size Rectangle
        panel.add(new JLabel("Taille du Rectangle"));
        panel.add(rectangleSize);

        int result = JOptionPane.showConfirmDialog(null, panel, "JAVA 3ADW", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        //Success du formulaire
        if (result == JOptionPane.OK_OPTION){


            if(numberOfAntsInput.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = numberOfAntsInput.getText();
                cfg.setNumberOfAnts(Integer.parseInt(nbAnts));
            }

            if(bunchOfFoodQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer une quantité valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = bunchOfFoodQuantity.getText();
                cfg.setBunchOfFoodQuantity(Integer.parseInt(nbAnts));
            }

            if(numberOfObstacle.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer un nombre d'obstacle valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = numberOfObstacle.getText();
                cfg.setNumberOfObstacle(Integer.parseInt(nbAnts));
            }

            if(AntHillQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer une quantité valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = bunchOfFoodQuantity.getText();
                cfg.setAntHillQuantity(Integer.parseInt(nbAnts));
            }

            if(AntHillSize.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer une taille valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = AntHillSize.getText();
                cfg.setAntHillSize(Integer.parseInt(nbAnts));
            }


            if(rectangleSize.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer une coordonnée valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = rectangleSize.getText();
                cfg.setAntHillSize(Integer.parseInt(nbAnts));
            }

            //Set la taille
            String mySize = combo.getSelectedItem().toString();

            switch (mySize) {
                case "Grande":
                    cfg.setWindowSize(750);
                    break;

                case "Moyenne":
                    cfg.setWindowSize(500);
                    break;

                case "Petite":
                    cfg.setWindowSize(350);
                    break;

                default:
                    cfg.setWindowSize(500);
            }


            //Echec du formulaire
        }else{
            System.out.println("Echec");
        }
    }
}
