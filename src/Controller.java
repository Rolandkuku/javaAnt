public class Controller {

    private Simulation simulation;
    private AntHillView antHillView;

    public void start() {
        this.simulation = new Simulation();
        this.antHillView = new AntHillView(this.simulation);
        while (true) {
            this.simulation.nextStep();
            this.antHillView.paint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
