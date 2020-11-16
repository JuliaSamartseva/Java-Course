package org.example;

import java.util.concurrent.locks.Lock;

public class ReentrantLock implements CustomLock {
  private long owner;
  private int holdCount = 0;

  public ReentrantLock() {
    holdCount = 0;
  }

  /** Acquires the lock. */
  @Override
  public synchronized void lock() {
    long id = Thread.currentThread().getId();

    if (owner == id) {
      holdCount++;
      return;
    }

    /// Waiting for another thread to release the lock.
    while (holdCount != 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    /// The hold count is zero.
    owner = id;
    holdCount = 1;
  }

  /** Attempts to release this lock. */
  @Override
  public synchronized void unlock() {
    /// Current thread is not holding the lock.
    if (holdCount == 0 || owner != Thread.currentThread().getId())
      throw new IllegalMonitorStateException();
    holdCount--;

    /// The lock is released. One waiting thread is notified.
    if (holdCount == 0) {
      owner = 0;
      notify();
    }
  }

  /** Acquires the lock only if it is not held by another thread at the time of invocation. */
  @Override
  public synchronized boolean tryLock() {
    long id = Thread.currentThread().getId();
    if (id == owner || holdCount == 0) {
      lock();
      return true;
    } else {
      return false;
    }
  }

  public synchronized boolean isHeldByCurrentThread() {
    return owner == Thread.currentThread().getId();
  }
}
