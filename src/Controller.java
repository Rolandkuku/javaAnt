public class Controller {

    public void start() {
        View myView = new View();

        if(myView.getIsValid()) {

            Simulation simulation = new Simulation();
            simulation.setSize(myView.getWindowSize());
            simulation.setAnts(new Ants(simulation.getSize(), myView.getNumberOfAnts()));

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
}
