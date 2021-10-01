package chrono;

public class FiveSecondsMessage implements Runnable {

    private final ChronoRunnable chronoRunnable;

    public FiveSecondsMessage(ChronoRunnable chronoRunnable) {
        this.chronoRunnable = chronoRunnable;
    }

    @Override
    public void run() {
        synchronized (chronoRunnable) {
            while (true) {
                try {
                    chronoRunnable.wait();
                    if (chronoRunnable.getSeconds() % 5 == 0)
                        System.out.println("Message from FiveSeconds Runnable");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

