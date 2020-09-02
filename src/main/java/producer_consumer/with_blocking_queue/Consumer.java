package producer_consumer.with_blocking_queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
  private final BlockingQueue<Integer> q;
  private final String name;
  private final Random ran = new Random();

  public Consumer(String name, BlockingQueue<Integer> q) {
    this.name = name;
    this.q = q;
  }

  @Override
  public void run() {
    try {
      while (true) {
        consume(q.take());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void consume(int value) {
    try {
      Thread.sleep(100 + ran.nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + " Consumed " + value);
  }
}
