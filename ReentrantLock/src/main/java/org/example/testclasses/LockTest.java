package org.example.testclasses;

import org.example.CustomLock;

public class LockTest implements Runnable {
  CustomLock lock;

  public  LockTest(CustomLock lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    System.out.println(name + " is waiting to acquire lock.");
    lock.lock();
    System.out.println(name + " has acquired custom lock.");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    lock.unlock();
    System.out.println(name + "has released custom lock.");
  }
}
