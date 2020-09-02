package producer_consumer.with_threads;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer<T> {
  private Queue<T> queue;
  private int size;

  public SharedBuffer(int size) {
    this.size = size;
    queue = new LinkedList<>();
  }

  public void add(T value) throws InterruptedException {
    synchronized (this) {
      while (queue.size() >= size) {
        wait();
      }
      queue.offer(value);
      notify();
    }
  }

  public T poll() throws InterruptedException {
    synchronized (this) {
      while (queue.size() == 0) {
        wait();
      }
      T value = queue.poll();
      notify();
      return value;
    }
  }
}
