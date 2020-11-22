package org.example;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class Node {

  private static final int MAX_LEVEL = 10;
  public final Integer value;
  public final AtomicMarkableReference<Node>[] next;
  public int topLevel;

  public Node(Integer value) {
    this.value = value;
    next = (AtomicMarkableReference<Node>[]) new AtomicMarkableReference[MAX_LEVEL + 1];
    for (int i = 0; i < next.length; i++) {
      next[i] = new AtomicMarkableReference<>(null, false);
    }
    topLevel = MAX_LEVEL;
  }

  public Node(Integer x, int height) {
    value = x;
    next = (AtomicMarkableReference<Node>[]) new AtomicMarkableReference[height + 1];
    for (int i = 0; i < next.length; i++) {
      next[i] = new AtomicMarkableReference<Node>(null, false);
    }
    topLevel = height;
  }
}
