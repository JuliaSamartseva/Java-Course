package org.example.test;

import org.example.MichaelScottQueue;

public class Consumer implements Runnable {
  MichaelScottQueue queue;
  public Consumer(MichaelScottQueue queue) {
    this.queue = queue;
  }

  public void run() {
    String str;
    System.out.println("Consumer started\n");

    try {
      Thread.sleep(500);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    while (queue.getSize() > 0) {
      if ((str = (String) queue.dequeue()) != null)
        System.out.println("  Consumer dequeued : "
            + str);

      try {
        Thread.sleep(500);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    }
  }
}

