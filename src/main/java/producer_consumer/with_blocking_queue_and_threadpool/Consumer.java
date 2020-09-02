package producer_consumer.with_blocking_queue_and_threadpool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * This class implement the consumer side of the producer/consumer problem.
 * It read item from the shared blocking queue and print the result to the
 * console. When it has consumed n items, the task will exit.
 */
public class Consumer implements Runnable {
  private final BlockingQueue<Integer> q;
  private final String name;
  private final Random ran = new Random();
  private int n;

  public Consumer(String name, BlockingQueue<Integer> q) {
    this(name, q, Integer.MAX_VALUE);
  }

  public Consumer(String name, BlockingQueue<Integer> q, int n) {
    this.name = name;
    this.q = q;
    this.n = n;
  }

  @Override
  public void run() {
    try {
      do {
        consume(q.take());
        n--;
      } while (n != 0);
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
