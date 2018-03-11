package os.application;

public class ApplicationThread extends Thread {

    private boolean running = true;

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running " + getName());
        }
    }

    public void terminate() {
        running = false;
    }
}
