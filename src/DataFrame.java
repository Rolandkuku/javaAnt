import javax.swing.*;
import java.awt.*;

public class DataFrame extends JFrame{
    //Var
    private int windowSize;
    private int numberOfAnts;

    //Setter
    public void setWindowSize(Integer windowSize){
        this.windowSize = windowSize;
    }

    public void setNumberOfAnts(Integer numberOfAnts){
        this.numberOfAnts = numberOfAnts;
    }

    //Getter
    public Integer getWindowSize(){
        return windowSize;
    }

    public Integer getNumberOfAnts(){
        return numberOfAnts;
    }

    //Constructeur
    DataFrame() {
        //Var

        //Select size
        String[] items = {"Grande", "Moyenne", "Petite"};
        JComboBox combo = new JComboBox(items);

        //Input Ants
        JTextField numberOfAntsInput = new JTextField(10);

        //Data du panel
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Taille de la fenêtre :"));
        panel.add(combo);
        panel.add(new JLabel("Nombre de fourmis :"));
        panel.add(numberOfAntsInput);

        //Confirm box
        int result = JOptionPane.showConfirmDialog(null, panel, "JAVA 3ADW",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        //Success du formulaire
        if (result == JOptionPane.OK_OPTION) {

            //Set la taille
            String mySize = combo.getSelectedItem().toString();

            switch (mySize) {
                case "Grande":
                    setWindowSize(750);
                    break;

                case "Moyenne":
                    setWindowSize(500);
                    break;

                case "Petite":
                    setWindowSize(350);
                    break;

                default:
                    setWindowSize(500);
            }

            //Set le nombre de fourmis
            String nbAnts = numberOfAntsInput.getText();
            setNumberOfAnts(Integer.parseInt(nbAnts));

        //Echec du formulaire
        } else {
            System.out.println("Echec");
        }
    }
}
