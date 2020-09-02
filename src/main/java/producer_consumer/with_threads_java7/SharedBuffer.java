package producer_consumer.with_threads_java7;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
  private Queue<Integer> queue;
  private int size;


  public SharedBuffer(int size) {
    this.size = size;
    queue = new LinkedList<>();
  }

  public void produce() throws InterruptedException {
    Integer value = 0;
    while (true) {
      synchronized (this) {
        while (queue.size() >= size) {
          // wait for the consumer to consume the item.
          wait();
        }
        queue.offer(value);
        System.out.println("Produced: " + value);
        value++;
        notify();
        Thread.sleep(1000);
      }
    }
  }

  public void consume() throws InterruptedException {
    while (true) {
      synchronized (this) {
        while (queue.size() == 0) {
          // wait for the producer
          wait();
        }
        int value = queue.poll();
        System.out.println("Consume: " + value);
        notify();
        Thread.sleep(1000);
      }
    }
  }
}
