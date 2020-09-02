package producer_consumer.with_blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerWithBlockingQueue {
  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);
    Producer p = new Producer("p", queue);
    Consumer c1 = new Consumer("c1", queue);
    Consumer c2 = new Consumer("c2", queue);
    new Thread(p).start();
    new Thread(c1).start();
    new Thread(c2).start();
  }
}
