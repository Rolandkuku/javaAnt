public class Controller {

    public void start() {
        Simulation simulation = new Simulation();
        AntHillView antHillView = new AntHillView(simulation);
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
