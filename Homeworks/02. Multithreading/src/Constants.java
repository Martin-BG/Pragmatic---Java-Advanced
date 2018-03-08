public final class Constants {

    static final int MAX_RUNNING_PROGRAMS = 10;
    static final int PROGRAMS_COUNT_INTERVAL = 25000;
    static final int STATUS_REPORT_INTERVAL = 15000;

    static final int EXPECTED_TOKENS_SIZE = 2;
    static final int COMMAND_INDEX = 0;
    static final int PROGRAM_INDEX = 1;

    static final String INVALID_COMMAND = "Invalid command!";
    static final String SHUTDOWN = "shutdown";
    static final String START = "start";
    static final String STOP = "stop";
    static final String RUNNING_PROGRAMS_COUNT = "Running programs count: ";
    static final String COMMANDS_SEPARATOR = "\\s+";
    static final String COMMAND_IS_RUNNING_ALREADY = " is running already";
    static final String CANNOT_START_ANOTHER_PROGRAM = "Cannot start another program";
    static final String IS_NOT_RUNNING = " is not running";
    static final String SHUTTING_DOWN = "Shutting down...";
    static final String STARTED = "Started ";
    static final String STOPPED = "Stopped ";
    static final String PROGRAM_IS_RUNNING = " is running";

    private Constants() {
    }
}
