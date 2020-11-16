package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class LockFreeSkipList<T> {
  static final int MAX_LEVEL = 10;
  final Node<T> head = new Node<T>(Integer.MIN_VALUE);
  final Node<T> tail = new Node<T>(Integer.MAX_VALUE);
  Random rand;

  public LockFreeSkipList() {
    rand = new Random();
    for (int i = 0; i < head.next.length; i++) {
      head.next[i] = new AtomicMarkableReference<Node<T>>(tail, false);
    }
  }

  public void add(T item) {
    int topLevel = randomLevel(MAX_LEVEL);
    int bottomLevel = 0;
    Node<T>[] preds = (Node<T>[]) new Node[MAX_LEVEL + 1];
    Node<T>[] succs = (Node<T>[]) new Node[MAX_LEVEL + 1];

    while (true) {
      boolean found = find(item, preds, succs);
      if (!found) {
        Node<T> newNode = new Node<T>(item, topLevel);
        for (int level = bottomLevel; level <= topLevel; level++) {
          Node<T> succ = succs[level];
          newNode.next[level].set(succ, false);
        }
        Node<T> succ = succs[bottomLevel];
        Node<T> pred = preds[bottomLevel];
        newNode.next[bottomLevel].set(succ, false);

        if (!pred.next[bottomLevel].compareAndSet(succ, newNode, false, false)) continue;

        for (int level = bottomLevel + 1; level <= topLevel; level++) {
          while (true) {
            pred = preds[level];
            succ = succs[level];
            if (pred.next[level].compareAndSet(succ, newNode, false, false)) {
              break;
            }
            find(item, preds, succs);
          }
        }
        return;
      } else {
        return;
      }
    }
  }

  boolean remove(T x) {
    int bottomLevel = 0;
    Node<T>[] preds = (Node<T>[]) new Node[MAX_LEVEL + 1];
    Node<T>[] succs = (Node<T>[]) new Node[MAX_LEVEL + 1];
    Node<T> succ;
    while (true) {
      boolean found = find(x, preds, succs);
      if (!found) {
        return false;
      } else {
        Node<T> nodeToRemove = succs[bottomLevel];
        for (int level = nodeToRemove.topLevel; level >= bottomLevel + 1; level--) {
          boolean[] marked = {false};
          succ = nodeToRemove.next[level].get(marked);
          while (!marked[0]) {
            nodeToRemove.next[level].attemptMark(succ, true);
            succ = nodeToRemove.next[level].get(marked);
          }
        }
        boolean[] marked = {false};
        succ = nodeToRemove.next[bottomLevel].get(marked);
        while (true) {
          boolean iMarkedIt = nodeToRemove.next[bottomLevel].compareAndSet(succ, succ, false, true);
          succ = succs[bottomLevel].next[bottomLevel].get(marked);
          if (iMarkedIt) {
            find(x, preds, succs);
            return true;
          } else if (marked[0]) return false;
        }
      }
    }
  }

  boolean contains(T x) {
    int bottomLevel = 0;
    int v = x.hashCode();
    boolean[] marked = {false};
    Node<T> pred = head, curr = null, succ = null;
    for (int level = MAX_LEVEL; level >= bottomLevel; level--) {
      curr = pred.next[level].getReference();
      while (true) {
        succ = curr.next[level].get(marked);
        while (marked[0]) {
          curr = pred.next[level].getReference();
          succ = curr.next[level].get(marked);
        }
        if (curr.key < v) {
          pred = curr;
          curr = succ;
        } else {
          break;
        }
      }
    }
    return (curr.key == v);
  }

  private boolean find(T item, Node<T>[] preds, Node<T>[] succs) {
    int bottomLevel = 0;
    int key = item.hashCode();
    boolean[] marked = {false};
    boolean snip;
    Node<T> pred = null, curr = null, succ = null;
    retry:
    while (true) {
      pred = head;
      for (int level = MAX_LEVEL; level >= bottomLevel; level--) {
        curr = pred.next[level].getReference();
        while (true) {
          succ = curr.next[level].get(marked);
          while (marked[0]) {
            snip = pred.next[level].compareAndSet(curr, succ, false, false);
            if (!snip) continue retry;

            curr = pred.next[level].getReference();
            succ = curr.next[level].get(marked);
          }
          if (curr.key < key) {
            pred = curr;
            curr = succ;
          } else {
            break;
          }
        }
        preds[level] = pred;
        succs[level] = curr;
      }
      return (curr.key == key);
    }
  }

  private int randomLevel(int max) {
    int level = 0;
    while (level < max){
      int val = rand.nextInt() % 2;
      if (val == 1){
        level++;
      }
      else{
        return level;
      }
    }
    return level;
  }
}
