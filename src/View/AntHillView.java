package View;
import Model.*;

public class AntHillView {
    private Simulation simulation;
    private AntWorld antWorld;
    private AntPlayground antPlayground;

    public AntHillView(Simulation simulation) {
        this.simulation = simulation;
        this.antWorld = new AntWorld(simulation.getSize());
        this.antPlayground = new AntPlayground(simulation);
        this.antWorld.setContentPane(this.antPlayground);
        this.antWorld.setVisible(true);
    }

    public void paint() {
        antPlayground.repaint();
    }
}


