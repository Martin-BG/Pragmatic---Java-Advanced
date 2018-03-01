package treads.counter;

public class CounterDemo {

    public static void main(String[] args) throws InterruptedException {

        CounterAccessors counterAccessors = new CounterAccessors();
        counterAccessors.run();

        counterAccessors.multipleThreads(100, 1000);
        counterAccessors.multipleThreadsVolatile(100, 1000);
    }
}
