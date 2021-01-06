package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.junit.Test;

public class AppTest {
  @Test
  public void testSize() {
    MichaelScottQueue<Integer> q = new MichaelScottQueue<>();
    for (int i = 0; i < 5; ++i) {
      assertEquals(Integer.valueOf(i), q.getSize());
      q.enqueue(i);
    }
  }

  @Test
  public void testSizeWithEnqueueDequeue() {
    int size = 5;
    MichaelScottQueue<Integer> q = addElements(size);
    for (int i = 0; i < size; ++i) {
      assertEquals(Integer.valueOf(size - i), q.getSize());
      q.dequeue();
    }
  }

  @Test
  public void testConcurrentEnqueue() throws InterruptedException {
    final int size1 = 5;
    final int size2 = 3;
    final int size3 = 3;
    final MichaelScottQueue<Integer> q = addElements(size1);

    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < size2; i++) {
          q.enqueue(i + size1);
        }
      }
    });

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < size3; i++) {
          q.enqueue(i + size1 + size2);
        }
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    int overallSize = size1 + size2 + size3;
    assertEquals(q.getSize(), Integer.valueOf(overallSize));
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < overallSize; i++) {
      list.add(q.dequeue());
    }
    for (int i = 0; i < overallSize; i++) {
      assertTrue(list.contains(i));
    }
  }

  @Test
  public void testConcurrentDeque() throws InterruptedException {
    int size = 100;
    final int size1 = size / 2;
    final MichaelScottQueue<Integer> q = addElements(size);

    final Queue<Integer> globalQueue = new ConcurrentLinkedQueue<>();
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < size1; i++) {
          globalQueue.add(q.dequeue());
        }
      }
    });

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < size1; i++) {
          globalQueue.add(q.dequeue());
        }
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    for (int i = 0; i < size; i++) {
      assertTrue(globalQueue.contains(i));
    }
  }

  private MichaelScottQueue<Integer> addElements(int n) {
    MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
    for (int i = 0; i < n; i++) {
      queue.enqueue(i);
    }
    assertFalse(queue.isEmpty());
    assertEquals(Integer.valueOf(n), queue.getSize());
    return queue;
  }
}
