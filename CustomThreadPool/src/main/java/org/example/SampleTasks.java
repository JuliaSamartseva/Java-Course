package org.example;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SampleTasks {
  public static void helloWorld() throws InterruptedException {
    SECONDS.sleep(1);
    System.out.println("Hello world");
  }
}
