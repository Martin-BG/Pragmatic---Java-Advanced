package model;

import constants.Constants;
import contracts.OutputWriter;
import managers.ProgramsManager;

public class RunningProgramsCountReporter implements Runnable {

    private final ProgramsManager programsManager;
    private final int runningProgramsCountReportInterval;
    private final OutputWriter outputWriter;

    public RunningProgramsCountReporter(ProgramsManager programsManager, int runningProgramsCountReportInterval,
                                        OutputWriter outputWriter) {
        this.programsManager = programsManager;
        this.runningProgramsCountReportInterval = runningProgramsCountReportInterval;
        this.outputWriter = outputWriter;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

            this.outputWriter.println(Constants.RUNNING_PROGRAMS_COUNT + this.programsManager.getRunningProgramsCount());

            try {
                Thread.sleep(this.runningProgramsCountReportInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
