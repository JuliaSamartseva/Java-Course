package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CustomCyclicBarrier barrier = new CustomCyclicBarrier(3);
    executorService.submit(new Task(barrier, 1000));
    executorService.submit(new Task(barrier, 2000));
    executorService.submit(new Task(barrier, 3000));
  }
}
