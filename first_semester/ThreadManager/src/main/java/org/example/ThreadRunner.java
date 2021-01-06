package org.example;

public class ThreadRunner implements Runnable {
  private int time;

  ThreadRunner(int time) {
    this.time = time;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
