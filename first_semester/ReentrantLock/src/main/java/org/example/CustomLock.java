package org.example;

public interface CustomLock {
  void lock();
  void unlock();
  boolean tryLock();
  boolean isHeldByCurrentThread();
}
