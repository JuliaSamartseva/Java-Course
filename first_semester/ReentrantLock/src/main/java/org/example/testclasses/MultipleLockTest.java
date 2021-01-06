package org.example.testclasses;

import org.example.CustomLock;

public class MultipleLockTest implements Runnable {
  CustomLock lock;

  public  MultipleLockTest(CustomLock lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    System.out.println(name + " is waiting to acquire lock.");
    lock.lock();
    System.out.println(name + " has acquired custom lock. Hold count = 1");

    System.out.println(name + " is locking once again. ");
    lock.lock();
    System.out.println(name + " has acquired custom lock. Hold count = 2");

    System.out.println(name + " has released the lock once. Hold count = 1");
    lock.unlock();

    System.out.println(name + " has released custom lock. Hold count = 0");
    lock.unlock();
  }
}