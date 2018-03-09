package factories;

import contracts.OutputWriter;
import contracts.ProgramsLoader;
import model.ProgramLoader;

public class ProgramsLoaderFactory implements ProgramsLoader {

    private static final ProgramsLoaderFactory factory = new ProgramsLoaderFactory();

    private ProgramsLoaderFactory() {
    }

    public static ProgramsLoaderFactory getInstance() {
        return factory;
    }

    @Override
    public Thread loadProgram(String program, int statusReportInterval, OutputWriter outputWriter) {
        return new Thread(new ProgramLoader(program, statusReportInterval, outputWriter), program);
    }
}
