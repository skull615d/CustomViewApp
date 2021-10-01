package chrono;

public class SevenSecondsMessageThread extends Thread {

    private final ChronoRunnable chronoRunnable;

    public SevenSecondsMessageThread(ChronoRunnable chronoRunnable) {
        this.chronoRunnable = chronoRunnable;
    }


    @Override
    public void run() {
        synchronized (chronoRunnable) {
            while (true) {
                try {
                    chronoRunnable.wait();
                    if (chronoRunnable.getSeconds() % 7 == 0)
                        System.out.println("Message From SevenSeconds Thread");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
