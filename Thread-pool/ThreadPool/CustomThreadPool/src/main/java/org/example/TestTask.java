package org.example;

public class TestTask implements Runnable {
  private int number;

  public TestTask() { }

  @Override
  public void run() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
