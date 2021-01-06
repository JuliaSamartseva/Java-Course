package org.example.combineResults;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.example.CustomCyclicBarrier;

public class CombineResultsTest {
  public static void main(String[] args) {
    Data sharedData = new Data();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    CustomCyclicBarrier barrier = new CustomCyclicBarrier(2, new CombineResults(sharedData));
    executorService.submit(new Task1(barrier, sharedData));
    executorService.submit(new Task2(barrier, sharedData));
  }
}
