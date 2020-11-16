package org.example;

public class Task implements Runnable {
  CustomCyclicBarrier barrier;
  int millisWait;

  public Task(CustomCyclicBarrier cyclicBarrier, int millisWait) {
    this.barrier = cyclicBarrier;
    this.millisWait = millisWait;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        System.out.println(Thread.currentThread().getName() + " started its work. ");
        Thread.sleep(millisWait);
        System.out.println(
            Thread.currentThread().getName() + " is waiting for the barrier to be released.");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " continues its work ");
        Thread.currentThread().interrupt();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
