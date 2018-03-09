package managers;

import constants.Constants;
import contracts.InputReader;
import contracts.OutputWriter;
import exceptions.MaxRunningProgramsLimitReachedException;
import exceptions.ProgramAlreadyStartedException;
import exceptions.ProgramIsNotRunningException;

public class IoManager {

    private final InputReader reader;
    private final OutputWriter writer;
    private final ProgramsManager programsManager;
    private final Thread programsCountReporter;

    public IoManager(InputReader reader, OutputWriter writer, ProgramsManager programsManager, Thread programsCountReporter) {
        this.reader = reader;
        this.writer = writer;
        this.programsManager = programsManager;
        this.programsCountReporter = programsCountReporter;
    }

    public void start() {

        String input;
        String program = null;

        this.programsCountReporter.start();

        while (!Constants.SHUTDOWN.equalsIgnoreCase(input = reader.readLine().trim())) {
            try {
                String[] tokens = input.split(Constants.COMMANDS_SEPARATOR);

                if (tokens.length != Constants.EXPECTED_TOKENS_SIZE) {
                    this.writer.println(Constants.INVALID_COMMAND);
                    continue;
                }

                String command = tokens[Constants.COMMAND_INDEX].toLowerCase();
                program = tokens[Constants.PROGRAM_INDEX];

                switch (command) {
                case Constants.START:
                    this.programsManager.startProgram(program, Constants.STATUS_REPORT_INTERVAL, this.writer);
                    break;
                case Constants.STOP:
                    this.programsManager.stopProgram(program);
                    break;
                default:
                    this.writer.println(Constants.INVALID_COMMAND);
                    break;
                }
            } catch (ProgramAlreadyStartedException e) {
                this.writer.println(program + Constants.PROGRAM_IS_RUNNING_ALREADY);
            } catch (MaxRunningProgramsLimitReachedException e) {
                this.writer.println(Constants.CANNOT_START_ANOTHER_PROGRAM);
            } catch (ProgramIsNotRunningException e) {
                this.writer.println(program + Constants.PROGRAM_IS_NOT_RUNNING);
            }
        }

        System.out.println(Constants.SHUTTING_DOWN);
        this.programsManager.shutdown();
        this.programsCountReporter.interrupt();
    }
}
