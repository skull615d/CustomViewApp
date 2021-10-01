package producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {

    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        AtomicInteger count = new AtomicInteger();
        while (!queue.isEmpty()) {
            String word = queue.poll();
            if (word != null)
                count.addAndGet(1);
        }
        System.out.println(count);
    }
}
