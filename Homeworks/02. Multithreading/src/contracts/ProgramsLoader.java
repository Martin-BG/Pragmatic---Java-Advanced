package contracts;

public interface ProgramsLoader {

    Thread loadProgram(String program, int statusReportInterval, OutputWriter outputWriter);
}
