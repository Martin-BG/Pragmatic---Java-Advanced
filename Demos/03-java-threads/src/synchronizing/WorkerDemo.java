package synchronizing;

public class WorkerDemo {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.run();
    }
}
