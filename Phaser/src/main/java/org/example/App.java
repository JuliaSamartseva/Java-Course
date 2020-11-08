package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CustomPhaser phaser = new CustomPhaser();
    executorService.submit(new Task(phaser, 1000));
    executorService.submit(new BlockingTask(phaser, 2000));
    executorService.submit(new Task(phaser, 3000));

    phaser.awaitAdvance(0);
    System.out.println("Threads have finished their work up to the phaser. ");
    System.out.println("Phaser is now on the " + phaser.getPhase() + " phase. ");
  }
}
