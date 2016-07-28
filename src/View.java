import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
    //Var
    private int windowSize;
    private int numberOfAnts;
    private boolean isValid = true;

    //Setter
    public void setWindowSize(Integer windowSize){
        this.windowSize = windowSize;
    }

    public void setNumberOfAnts(Integer numberOfAnts){
        this.numberOfAnts = numberOfAnts;
    }

    public void setIsValid(Boolean isValid) { this.isValid = isValid; }

    //Getter
    public Integer getWindowSize(){
        return windowSize;
    }

    public Integer getNumberOfAnts(){
        return numberOfAnts;
    }

    public Boolean getIsValid(){ return isValid; }

    //Constructeur
    View() {
        //Init
        setIsValid(true);

        //Select size
        String[] items = {"Petite", "Moyenne", "Grande"};
        JComboBox combo = new JComboBox(items);

        //Input Ants
        JTextField numberOfAntsInput = new JTextField("1");

        //Data du panel
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Taille de la fenêtre :"));
        panel.add(combo);
        panel.add(new JLabel("Nombre de fourmis :"));
        panel.add(numberOfAntsInput);

        int result = JOptionPane.showConfirmDialog(null, panel, "JAVA 3ADW", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        //Success du formulaire
        if (result == JOptionPane.OK_OPTION){

            //Set le nombre de fourmis
            if(numberOfAntsInput.getText().equals("")) {
                setIsValid(false);
                JOptionPane.showMessageDialog(panel, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.WARNING_MESSAGE);
            }else{
                String nbAnts = numberOfAntsInput.getText();
                setNumberOfAnts(Integer.parseInt(nbAnts));
            }

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


        //Echec du formulaire
        }else{
            System.out.println("Echec");
            setIsValid(false);
        }
    }
}
