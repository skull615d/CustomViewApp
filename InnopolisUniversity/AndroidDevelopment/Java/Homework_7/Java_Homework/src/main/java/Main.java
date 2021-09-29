import chrono.ChronoRunnable;
import chrono.FiveSecondsMessage;
import chrono.SevenSecondsMessageThread;

public class Main {

    public static void main(String[] args) {

        ChronoRunnable chronoRunnable = new ChronoRunnable();
        new Thread(chronoRunnable).start();

        FiveSecondsMessage fiveSecondsMessage = new FiveSecondsMessage(chronoRunnable);
        new Thread(fiveSecondsMessage).start();

        SevenSecondsMessageThread sevenSecondsMessageThread = new SevenSecondsMessageThread(chronoRunnable);
        sevenSecondsMessageThread.start();

    }

}
