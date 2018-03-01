package treads.types;

public class RunnableImplementer implements Runnable {

    @Override
    public void run() {
        for (int i = 21; i <= 40; i++) {
            System.out.println(i);
        }
    }
}
