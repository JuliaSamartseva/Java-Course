package org.example;

import java.util.logging.Logger;

public class TaskExecutor implements Runnable {
  BlockingQueue<Runnable> queue;
  private volatile boolean running = true;
  private static final Logger log = Logger.getLogger(TaskExecutor.class.getName());

  public TaskExecutor(BlockingQueue<Runnable> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (running) {
        String name = Thread.currentThread().getName();
        Runnable task = queue.dequeue();
        log.info("Started task by thread: " + name);
        task.run();
        log.info("Finished task by thread : " + name);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void stopExecution() {
    running = false;
  }
}
