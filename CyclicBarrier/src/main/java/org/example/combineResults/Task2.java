package org.example.combineResults;

import org.example.CustomCyclicBarrier;

public class Task2 implements Runnable {
  private Data data;
  private CustomCyclicBarrier cyclicBarrier;

  public Task2(CustomCyclicBarrier cyclicBarrier, Data data) {
    this.data = data;
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    try {
      System.out.println("Start calculating b");
      Thread.sleep(2000);
      data.b = 100;
      System.out.println("b has been calculated");
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
