package treads.counter;

public class CounterAccessors {

    private volatile int cnt = 0;
    private int counter = 0;

    private synchronized void increase() {
        counter++;
    }

    private synchronized void decrease() {
        counter--;
    }

    public void run() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increase();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                decrease();
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                increase();
            }
        });
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + counter);
    }

    public void multipleThreads(int threads, int count) throws InterruptedException {
        this.counter = 0;
        Thread[] threadsArray = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            threadsArray[i] = new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    increase();
                }
            });
        }

        for (int i = 0; i < threads; i++) {
            threadsArray[i].start();
        }

        for (int i = 0; i < threads; i++) {
            threadsArray[i].join();
        }

        System.out.println(this.counter);
    }

    public void multipleThreadsVolatile(int threads, int count) throws InterruptedException {
        // This is unsafe and results of incorrect count
        this.cnt = 0;
        Thread[] threadsArray = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            threadsArray[i] = new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    cnt++;
                }
            });
        }

        for (int i = 0; i < threads; i++) {
            threadsArray[i].start();
        }

        for (int i = 0; i < threads; i++) {
            threadsArray[i].join();
        }

        System.out.println(this.cnt);
    }
}
