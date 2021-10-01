package chrono;

public class ChronoRunnable implements Runnable {

    private int seconds;

    public synchronized int getSeconds() {
        return seconds;
    }

    public synchronized void setSeconds(int seconds) {
        this.seconds = seconds;
        notifyAll();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                synchronized (this) {
                    setSeconds(seconds + 1);
                    System.out.println("Seconds passed: " + seconds);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
