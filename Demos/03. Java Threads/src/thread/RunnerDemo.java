package thread;

public class RunnerDemo {
    public static void main(String[] args) {
        Runner runner1 = new Runner();
        runner1.start();
        System.out.println("between threads");
        Runner runner2 = new Runner();
        runner2.start();
        System.out.println("after threads");
    }
}
