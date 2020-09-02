package producer_consumer.with_blocking_queue_and_threadpool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * This class implements the producer side of the producer/consumer problem. It continue to produce
 * items in an infinite loop.
 */
public class Producer implements Runnable {
  private BlockingQueue<Integer> q;
  private int value;
  private String name;
  private Random ran = new Random();

  public Producer(String name, BlockingQueue<Integer> q) {
    this.name = name;
    this.q = q;
    this.value = 0;
  }

  @Override
  public void run() {
    try {
      while (true) {
        q.put(produce());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private Integer produce() throws InterruptedException {
    Thread.sleep(100 + ran.nextInt(1000));
    System.out.println(name + " Produced " + value);
    return value++;
  }
}
