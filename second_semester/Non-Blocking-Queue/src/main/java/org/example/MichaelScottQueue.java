package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MichaelScottQueue<T> {
  private final AtomicReference<Node> queueHead;
  private final AtomicReference<Node> queueTail;
  private final AtomicInteger size = new AtomicInteger(0);

  public MichaelScottQueue() {
    Node newNode = new Node();
    queueHead = new AtomicReference<>(newNode);
    queueTail = new AtomicReference<>(newNode);
  }

  public void enqueue(T x) {
    Node n = new Node(x);
    Node tail = null;
    while (true) {
      tail = queueTail.get();
      Node next = (Node) tail.next.get();

      if (tail == queueTail.get()) {
        if (next == null) {
          if (tail.next.compareAndSet(next, n)) {
            break;
          }
        } else {
          queueTail.compareAndSet(tail, next);
        }
      }
    }
    queueTail.compareAndSet(tail, n);
    size.incrementAndGet();
  }

  public T dequeue() {
    T data = null;
    size.decrementAndGet();
    while (true) {
      Node head = queueHead.get();
      Node tail = queueTail.get();
      Node next = (Node) head.next.get();

      if (head == queueHead.get()) {
        if (head == tail) {
          if (next == null) {
            return null;
          }
          queueTail.compareAndSet(tail, next);
        } else {
          data = (T) next.data;

          if (queueHead.compareAndSet(head, next)) {
            break;
          }
        }
      }
    }

    return data;
  }

  public Integer getSize() {
    return size.get();
  }

  public boolean isEmpty() {
    return size.get() == 0;
  }

  private class Node {
    private final AtomicReference<Node> next;
    private final T data;

    Node() {
      this(null);
    }

    Node(T x) {
      data = x;
      next = new AtomicReference<>();
    }

  }
}
