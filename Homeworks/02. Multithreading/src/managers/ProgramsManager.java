package managers;

import contracts.OutputWriter;
import contracts.ProgramsLoader;
import exceptions.MaxRunningProgramsLimitReachedException;
import exceptions.ProgramAlreadyStartedException;
import exceptions.ProgramIsNotRunningException;

import java.util.Map;

public class ProgramsManager {

    private final Map<String, Thread> threads;
    private final int maxRunningPrograms;
    private final ProgramsLoader programsLoader;

    public ProgramsManager(Map<String, Thread> threads, int maxRunningPrograms, ProgramsLoader programsLoader) {
        this.threads = threads;
        this.maxRunningPrograms = maxRunningPrograms;
        this.programsLoader = programsLoader;
    }

    public void startProgram(String program, int statusReportInterval, OutputWriter outputWriter)
            throws ProgramAlreadyStartedException, MaxRunningProgramsLimitReachedException {

        if (this.threads.containsKey(program)) {
            throw new ProgramAlreadyStartedException();
        }

        if (this.threads.size() >= this.maxRunningPrograms) {
            throw new MaxRunningProgramsLimitReachedException();
        }

        Thread thread = this.programsLoader.loadProgram(program, statusReportInterval, outputWriter);
        thread.start();
        this.threads.put(program, thread);
    }

    public void stopProgram(String program) throws ProgramIsNotRunningException {

        if (!this.threads.containsKey(program)) {
            throw new ProgramIsNotRunningException();
        }

        Thread thread = this.threads.get(program);

        thread.interrupt();

        this.threads.remove(program);
    }

    public void shutdown() {
        this.threads.values().forEach(Thread::interrupt);
    }

    public int getRunningProgramsCount() {
        return this.threads.size();
    }
}
