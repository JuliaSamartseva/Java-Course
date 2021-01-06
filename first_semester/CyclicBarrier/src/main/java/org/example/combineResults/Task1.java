package org.example.combineResults;

import org.example.CustomCyclicBarrier;

public class Task1 implements Runnable {
  private Data data;
  private CustomCyclicBarrier cyclicBarrier;

  public Task1(CustomCyclicBarrier cyclicBarrier, Data data) {
    this.data = data;
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    try {
      System.out.println("Start calculating a");
      Thread.sleep(1000);
      data.a = 100;
      System.out.println("a has been calculated");
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
