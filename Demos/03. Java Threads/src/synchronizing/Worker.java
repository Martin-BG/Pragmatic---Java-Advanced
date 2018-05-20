package synchronizing;

public class Worker {
    private int count = 0;

    private synchronized void increment() {
        count++;
    }

    public void run() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                increment();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                increment();
            }
        });
        thread2.start();

//        try {
//            thread1.join(); // count will be 10000
//            thread2.join(); // count will 20000
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.sleep(5000);
        System.out.println("Count is: " + count);
    }
}