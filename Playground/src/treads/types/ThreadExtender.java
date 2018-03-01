package treads.types;

public class ThreadExtender extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println(i);
        }
    }
}
