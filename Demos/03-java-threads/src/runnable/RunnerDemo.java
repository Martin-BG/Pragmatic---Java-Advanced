package runnable;

public class RunnerDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runner());
        // this will run in a new thread concurrently with the main thread
        thread.start();
        // this will just run the method
        thread.run();
    }
}
