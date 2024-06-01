import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> q = new ArrayBlockingQueue<>(10);
        Thread producer = new Thread(new Producer(q), "producer");
        producer.start();
        new Thread(new Consumer(q), "Consumer ").start();
    }
}
