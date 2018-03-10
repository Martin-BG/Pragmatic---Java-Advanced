import constants.Constants;
import contracts.InputReader;
import contracts.OutputWriter;
import contracts.ProgramsLoader;
import factories.ProgramsLoaderFactory;
import io.ConsoleReader;
import io.ConsoleWriter;
import managers.IoManager;
import managers.ProgramsManager;
import model.RunningProgramsCountReporter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Main {

    public static void main(String[] args) {

        startJavaOS();

    }

    private static void startJavaOS() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        InputReader input = new ConsoleReader(bufferedReader);

        OutputWriter output = new ConsoleWriter();

        ProgramsLoader programsLoaderFactory = ProgramsLoaderFactory.getInstance();

        Map<String, Thread> runningPrograms = new ConcurrentHashMap<>();

        ProgramsManager programsManager = new ProgramsManager(
                runningPrograms, Constants.MAX_RUNNING_PROGRAMS, programsLoaderFactory);

        Runnable programsCountRunnable = new RunningProgramsCountReporter(
                programsManager, Constants.PROGRAMS_COUNT_INTERVAL, output);

        Thread programsCountReporter = new Thread(programsCountRunnable);

        IoManager ioManager = new IoManager(input, output, programsManager, programsCountReporter);

        ioManager.start();
    }
}
