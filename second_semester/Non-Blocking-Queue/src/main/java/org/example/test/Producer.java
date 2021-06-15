package org.example.test;

import org.example.MichaelScottQueue;

public class Producer implements Runnable {
  MichaelScottQueue queue;
  public Producer(MichaelScottQueue queue) {
    this.queue = queue;
  }

  public void run() {
    System.out.println("Producer started");
    try {
      for (int i = 1; i <= 10; i++) {
        String str = "String" + i;
        queue.enqueue(str);
        System.out.println("Producer enqueued : "
            + str);
        Thread.sleep(200);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}