package producer_consumer;

import parser.Parser;
import parser.ParserScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final Parser parser = new ParserScanner();
    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        File file = new File("voyna.txt");
        ArrayList<String> list = parser.parseFile(file);
        for (String s : list) {
            String[] words = s.split(" ");
            for (String word : words) {
                if (word.toLowerCase(Locale.ROOT).contains("страдани".toLowerCase(Locale.ROOT))) {
                    try {
                        queue.put(word);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
