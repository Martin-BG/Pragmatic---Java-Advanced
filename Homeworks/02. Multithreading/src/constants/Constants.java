package constants;

public final class Constants {

    public static final int MAX_RUNNING_PROGRAMS = 10;
    public static final int PROGRAMS_COUNT_INTERVAL = 25000;
    public static final int STATUS_REPORT_INTERVAL = 15000;

    public static final int EXPECTED_TOKENS_SIZE = 2;
    public static final int COMMAND_INDEX = 0;
    public static final int PROGRAM_INDEX = 1;

    public static final String INVALID_COMMAND = "Invalid command!";
    public static final String SHUTDOWN = "shutdown";
    public static final String START = "start";
    public static final String STOP = "stop";
    public static final String RUNNING_PROGRAMS_COUNT = "Running programs count: ";
    public static final String COMMANDS_SEPARATOR = "\\s+";
    public static final String PROGRAM_IS_RUNNING_ALREADY = " is running already";
    public static final String PROGRAM_IS_NOT_RUNNING = " is not running";
    public static final String CANNOT_START_ANOTHER_PROGRAM = "Cannot start another program";
    public static final String SHUTTING_DOWN = "Shutting down...";
    public static final String STARTED = "Started ";
    public static final String STOPPED = "Stopped ";
    public static final String PROGRAM_IS_RUNNING = " is running";

    private Constants() {
    }
}
