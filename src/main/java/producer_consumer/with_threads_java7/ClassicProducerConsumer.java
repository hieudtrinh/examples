package producer_consumer.with_threads_java7;

public class ClassicProducerConsumer {
  public static void main(String[] args) throws InterruptedException {
    SharedBuffer sbuff = new SharedBuffer(2);
    Thread producerThread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  sbuff.produce();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });
    Thread consumerThread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  sbuff.consume();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.join();
  }
}
