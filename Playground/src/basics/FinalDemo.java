package basics;

public class FinalDemo {
    final static String str;
    final String str2;

    public FinalDemo(String str2) {
        this.str2 = str2;
    }

    static {
        str = "Ole";
    }

    public static void main(String[] args) {
        final String str;

        str = "Demo";
    }
}
