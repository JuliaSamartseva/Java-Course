package org.example;

public interface CustomReadersWriterLock {
  CustomLock readLock();

  CustomLock writeLock();
}
