package org.example;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CustomThreadPool {
  BlockingQueue<Runnable> queue;
  ArrayList<TaskExecutor> tasks;

  public CustomThreadPool(int threadQueueSize, int threadNumber) {
    queue = new BlockingQueue<>(threadQueueSize);
    tasks = new ArrayList<>(threadNumber);

    /// Distributing tasks between the threads.
    for (int count = 0; count < threadNumber; count++) {
      TaskExecutor task = new TaskExecutor(queue);
      tasks.add(task);
      Thread thread = new Thread(task);
      thread.start();
    }
  }

  // Initiates an orderly shutdown in which previously submitted tasks are executed.
  // No new tasks will be accepted.
  public void execute(Runnable task) throws InterruptedException {
    queue.enqueue(task);
  }

  public void executeLambda(final Task task) throws InterruptedException {
    Runnable taskRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          task.start();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    execute(taskRunnable);
  }

  public void shutdown() {
    for (TaskExecutor task : tasks) {
      task.stopExecution();
    }
  }

}
