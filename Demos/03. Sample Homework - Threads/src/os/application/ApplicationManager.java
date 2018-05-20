package os.application;

import os.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationManager {

    private List<ApplicationThread> threads = new ArrayList<>();

    public ApplicationManager() {
        startApplicationCounter();
    }

    public void executeCommand(String command) {
        if (command == null) {
            return;
        }
        if (isShutdown(command)) {
            System.out.println("Shutting down...");
            System.exit(0);
        }
        String appName = getApplicationName(command);
        if (isStart(command)) {
            start(appName);
        } else if (isStop(command)) {
            stop(appName);
        }
    }

    private void startApplicationCounter() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threads.size() + " programs are running.");
            }
        });
        t.start();
    }

    private void start(String appName) {
        ApplicationThread t = new ApplicationThread();
        t.setName(appName);
        t.start();
        threads.add(t);
    }

    private void stop(String appName) {
        final Optional<ApplicationThread> thread = threads.stream().filter(t -> t.getName().equals(appName)).findFirst();
        if (thread.isPresent()) {
            thread.get().terminate();
            thread.ifPresent(t -> threads.remove(t));
        }
    }

    private boolean isShutdown(String command) {
        return Command.SHUTDOWN.toString().equalsIgnoreCase(command);
    }

    private boolean isStart(String command) {
        return command.toUpperCase().startsWith(Command.START.toString());
    }

    private boolean isStop(String command) {
        return command.toUpperCase().startsWith(Command.STOP.toString());
    }

    private String getApplicationName(String command) {
        return command.split("\\s+")[1];
    }
}
