package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
  @Test
  public void testConcurrentAdd() throws InterruptedException {
    final int size = 101;
    final LockFreeSkipList list = new LockFreeSkipList();
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < size; i += 2)
        list.add(i);
      }
    });

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 1; i < size; i += 2)
          list.add(i);
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    for (int i = 0; i < size; i++) {
      assertTrue(list.contains(i));
      list.remove(i);
      assertFalse(list.contains(i));
    }
  }

}
