package org.example;

public class App {
  public static void main(String[] args) throws InterruptedException {
    CustomThreadPool threadPool = new CustomThreadPool(3, 3);

    for (int taskNumber = 1; taskNumber <= 7; taskNumber++) {
      TestTask task = new TestTask();
      threadPool.execute(task);
    }

    threadPool.executeLambda(SampleTasks::helloWorld);

    //threadPool.shutdown();
  }
}
