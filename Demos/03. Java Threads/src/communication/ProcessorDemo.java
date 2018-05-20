package communication;

import java.util.Scanner;

public class ProcessorDemo {

    public static void main(String[] args) {
        Processor pro = new Processor();
        pro.start();
        new Scanner(System.in).nextLine();
        pro.shutdown();
    }

}