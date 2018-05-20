package communication;

class Processor extends Thread {

    private volatile boolean running = true;

    public void run() {
        while(running) {
            System.out.println("Running");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
