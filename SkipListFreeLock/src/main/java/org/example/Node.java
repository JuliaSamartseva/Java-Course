package org.example;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class Node<T> {

  private static final int MAX_LEVEL = 10;
  public final T value;
  public int key;
  public final AtomicMarkableReference<Node<T>>[] next;
  public int topLevel;
  // constructor for sentinel nodes
  public Node(int key) {
    value = null;
    this.key = key;
    next = (AtomicMarkableReference<Node<T>>[]) new AtomicMarkableReference[MAX_LEVEL + 1];
    for (int i = 0; i < next.length; i++) {
      next[i] = new AtomicMarkableReference<>(null, false);
    }
    topLevel = MAX_LEVEL;
  }

  public Node(T x, int height) {
    value = x;
    key = x.hashCode();
    next = (AtomicMarkableReference<Node<T>>[]) new AtomicMarkableReference[height + 1];
    for (int i = 0; i < next.length; i++) {
      next[i] = new AtomicMarkableReference<Node<T>>(null, false);
    }
    topLevel = height;
  }
}
