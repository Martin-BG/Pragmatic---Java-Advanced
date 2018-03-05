package treads.types;

public class ThreadsDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread threadExtender = new ThreadExtender();
        Thread runnableImplementer = new Thread(new RunnableImplementer());
        Thread lambdaRunnable = new Thread(() -> {
            for (int i = 41; i <= 60; i++) {
                System.out.println(i);
            }
        });

        threadExtender.start();
        runnableImplementer.start();
        lambdaRunnable.start();

        lambdaRunnable.join();
        threadExtender.join();
        runnableImplementer.join();
    }
}
