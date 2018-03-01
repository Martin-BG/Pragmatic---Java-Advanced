package anonymous;

public class AnnonymousThreadDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for(int i=0; i<5; i++) {
                System.out.println("Hello: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
    }

}