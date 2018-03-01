package treads.types;

public class ThreadsDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread lambdaRunnable = new Thread(() -> {
            for (int i = 41; i <= 60; i++) {
                System.out.println(i);
            }
        });
        Thread threadExtender = new Thread(new ThreadExtender());
        Thread runnableImplementer = new Thread(new RunnableImplementer());

        lambdaRunnable.start();
        threadExtender.start();
        runnableImplementer.start();

        lambdaRunnable.join();
        threadExtender.join();
        runnableImplementer.join();
    }
}
