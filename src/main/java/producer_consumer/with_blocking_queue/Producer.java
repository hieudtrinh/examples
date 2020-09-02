package producer_consumer.with_blocking_queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

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

  Integer produce() {
    try {
      Thread.sleep(100 + ran.nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + " Produced " + value);
    return value++;
  }
}
