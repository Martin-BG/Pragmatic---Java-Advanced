package anonymous;

public class OperationDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread operation = new Thread(new Operation());
        operation.start();
        operation.join();
        operation.join(1000);
        // check if the thread is terminated
        boolean hasThreadTerminated = operation.isAlive();
        boolean isSystemThread = operation.isDaemon();
        operation.isInterrupted();
        // keeps going after 1 second
        // keeps going
        System.out.println("Before sleep");
        Thread.sleep(5000);
        System.out.println("After sleep");
    }
}