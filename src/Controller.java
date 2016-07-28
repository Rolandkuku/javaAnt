public class Controller {

    public void start() {
        DataFrame dataFrame = new DataFrame();

        if(dataFrame.getIsValid()) {

            Simulation simulation = new Simulation();
            simulation.setSize(dataFrame.getWindowSize());

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
