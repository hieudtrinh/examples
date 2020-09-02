package producer_consumer.with_blocking_queue_and_threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Test class for the producer/consumer problem. It uses:
 * 1) a shared BlockingQueue for synchronize read and write access.
 * 2) a fixed size thread pool for running tasks.
 */
public class ProducerConsumerWithBlockingQueueAndThreadPool {
  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Producer p = new Producer("p", queue);
    Consumer c1 = new Consumer("c1", queue);

    // Since there are 3 threads in the thread pool, the c3 will not be schedule
    // until there is a thread become available in the thread pool.
    // Task c2 is configured to consume only 10 items and terminate itself.
    // When c2 exit, the thread serving c2 become available for c2 to start.
    Consumer c2 = new Consumer("c2", queue, 10);
    Consumer c3 = new Consumer("c3", queue);
    executorService.submit(p);
    executorService.submit(c1);
    executorService.submit(c2);
    executorService.submit(c3);
  }
}
