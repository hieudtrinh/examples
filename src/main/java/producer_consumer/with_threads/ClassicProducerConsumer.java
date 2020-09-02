package producer_consumer.with_threads;

import java.util.Random;

public class ClassicProducerConsumer {
  public static void main(String[] args) throws InterruptedException {
    Random ran = new Random();
    SharedBuffer<Integer> sbuff = new SharedBuffer<>(2);
    Thread producerThread =
        new Thread(
            () -> {
              try {
                int value = 0;
                while (true) {
                  sbuff.add(value);
                  System.out.println("Produced " + value);
                  value++;
                  Thread.sleep(200 + ran.nextInt(1000));
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    Thread consumerThread =
        new Thread(
            () -> {
              try {
                while (true) {
                  int value = sbuff.poll();
                  System.out.println("Consumed " + value);
                  Thread.sleep(200 + ran.nextInt(1000));
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });

    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.join();
  }
}
