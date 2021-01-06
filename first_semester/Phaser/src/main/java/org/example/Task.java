package org.example;

public class Task implements Runnable {
  private CustomPhaser phaser;
  private int millisWait;

  public Task(CustomPhaser phaser, int millisWait) {
    this.phaser = phaser;
    phaser.register();
    this.millisWait = millisWait;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        System.out.println(Thread.currentThread().getName() + " started its work. ");
        Thread.sleep(millisWait);
        System.out.println(Thread.currentThread().getName() + " arrived. ");
        phaser.arrive();
        System.out.println(Thread.currentThread().getName() + " continues its work ");
        Thread.currentThread().interrupt();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
