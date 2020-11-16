package org.example;


import org.example.test.Producer;
import org.example.test.Consumer;

public class App {
  public static void main(String[] args) {
    MichaelScottQueue<String> queue = new MichaelScottQueue();

    Thread producer = new Thread(new Producer(queue));
    Thread consumer = new Thread(new Consumer(queue));
    producer.start();
    consumer.start();

    while (consumer.isAlive()) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.exit(0);
  }
}
