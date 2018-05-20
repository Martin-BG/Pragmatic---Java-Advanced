package os;

import os.application.ApplicationManager;

import java.util.Scanner;

public class OS {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("WELCOME TO OUR OS");
        Scanner scanner = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();
        String command = scanner.nextLine();
        while (true) {
            manager.executeCommand(command);
            command = scanner.nextLine();
        }
    }
}
