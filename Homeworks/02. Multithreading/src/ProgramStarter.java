public class ProgramStarter implements Runnable {

    private final String programName;

    public ProgramStarter(String programName) {
        this.programName = programName;
    }

    @Override
    public void run() {
        System.out.println(Constants.STARTED + this.programName);

        while (!Thread.interrupted()) {
            System.out.println(this.programName + Constants.PROGRAM_IS_RUNNING);
            try {
                Thread.sleep(Constants.STATUS_REPORT_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(Constants.STOPPED + this.programName);
    }
}
