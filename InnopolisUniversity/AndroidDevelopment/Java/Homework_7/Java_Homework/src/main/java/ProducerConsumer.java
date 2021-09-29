import producer_consumer.Consumer;
import producer_consumer.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(producer);
        executorService.submit(consumer);

        executorService.shutdown();

    }

}
