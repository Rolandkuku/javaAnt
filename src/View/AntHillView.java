package View;
import Model.*;

import javax.swing.*;

public class AntHillView {
    private Simulation simulation;
    private AntWorld antWorld;
    private DataAnts dataAnts;
    private AntPlayground antPlayground;
    private JSplitPane splitPane;

    public AntHillView(Simulation simulation) {
        this.simulation = simulation;
        this.antWorld = new AntWorld(simulation.getSize());
        this.antPlayground = new AntPlayground(simulation);
        this.dataAnts = new DataAnts(simulation);
        this.splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                antPlayground, dataAnts );
        this.antWorld.setContentPane(this.splitPane);
        this.antWorld.setVisible(true);
    }

    public void paint() {
        antPlayground.repaint();
    }

    public void dataView(){
        dataAnts.formView();
    }
}


