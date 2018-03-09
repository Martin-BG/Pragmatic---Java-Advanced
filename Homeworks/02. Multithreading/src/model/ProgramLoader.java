package model;

import constants.Constants;
import contracts.OutputWriter;

public class ProgramLoader implements Runnable {

    private final String programName;
    private final int statusReportInterval;
    private final OutputWriter outputWriter;

    public ProgramLoader(String programName, int statusReportInterval, OutputWriter outputWriter) {
        this.programName = programName;
        this.statusReportInterval = statusReportInterval;
        this.outputWriter = outputWriter;
    }

    @Override
    public void run() {
        outputWriter.println(Constants.STARTED + this.programName);

        while (!Thread.interrupted()) {
            outputWriter.println(this.programName + Constants.PROGRAM_IS_RUNNING);
            try {
                Thread.sleep(this.statusReportInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        outputWriter.println(Constants.STOPPED + this.programName);
    }
}
