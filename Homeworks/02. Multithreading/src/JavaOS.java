import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JavaOS {

    public static void main(String[] args) {
        Map<String, Thread> threads = new ConcurrentHashMap<>();

        Thread programsCount = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println(Constants.RUNNING_PROGRAMS_COUNT + threads.size());

                try {
                    Thread.sleep(Constants.PROGRAMS_COUNT_INTERVAL);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        programsCount.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String input;

            while (!Constants.SHUTDOWN.equalsIgnoreCase(input = reader.readLine().trim())) {

                String[] tokens = input.split(Constants.COMMANDS_SEPARATOR);

                if (tokens.length != Constants.EXPECTED_TOKENS_SIZE) {
                    System.out.println(Constants.INVALID_COMMAND);
                    continue;
                }

                String command = tokens[Constants.COMMAND_INDEX].toLowerCase();
                String program = tokens[Constants.PROGRAM_INDEX];

                switch (command) {
                case Constants.START:
                    if (threads.containsKey(program)) {
                        System.out.println(program + Constants.COMMAND_IS_RUNNING_ALREADY);
                        continue;
                    }

                    if (threads.size() >= Constants.MAX_RUNNING_PROGRAMS) {
                        System.out.println(Constants.CANNOT_START_ANOTHER_PROGRAM);
                        continue;
                    }

                    Thread thread = new Thread(new ProgramStarter(program), program);
                    thread.start();
                    threads.put(program, thread);

                    break;
                case Constants.STOP:
                    if (!threads.containsKey(program)) {
                        System.out.println(program + Constants.IS_NOT_RUNNING);
                        continue;
                    }

                    thread = threads.get(program);

                    thread.interrupt();

                    threads.remove(program);

                    break;
                default:
                    System.out.println(Constants.INVALID_COMMAND);
                    break;
                }
            }

            System.out.println(Constants.SHUTTING_DOWN);

            threads.values().forEach(Thread::interrupt);
            programsCount.interrupt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
