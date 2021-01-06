package org.example;

import org.example.testclasses.LockTest;
import org.example.testclasses.MultipleLockTest;

public class App {
  public static void main(String[] args) {
    CustomLock lock = new ReentrantLock();
    Runnable runnable = new MultipleLockTest(lock);
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);

    thread1.start();
    thread2.start();
  }
}
