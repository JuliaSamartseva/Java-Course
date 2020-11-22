package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class LockFreeSkipList {
  private static final int MAX_LEVEL = 10;
  private final Node head = new Node(Integer.MIN_VALUE);
  private final Node tail = new Node(Integer.MAX_VALUE);
  private final Random rand;

  public LockFreeSkipList() {
    rand = new Random();
    for (int i = 0; i < head.next.length; i++) {
      head.next[i] = new AtomicMarkableReference<>(tail, false);
    }
  }

  public void add(int item) {
    int topLevel = randomLevel(MAX_LEVEL);
    int bottomLevel = 0;
    Node[] preds = new Node[MAX_LEVEL + 1];
    Node[] succs = new Node[MAX_LEVEL + 1];

    while (true) {
      boolean found = find(item, preds, succs);
      if (!found) { // if the node already exists, we don't add it
        Node newNode = new Node(item, topLevel);
        for (int level = bottomLevel; level <= topLevel; level++) {
          Node succ = succs[level];
          newNode.next[level].set(succ, false);
        }
        Node succ = succs[bottomLevel];
        Node pred = preds[bottomLevel];
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

  boolean remove(int x) {
    int bottomLevel = 0;
    Node[] preds = (Node[]) new Node[MAX_LEVEL + 1];
    Node[] succs = (Node[]) new Node[MAX_LEVEL + 1];
    Node succ;
    while (true) {
      boolean found = find(x, preds, succs);
      if (!found) {
        return false;
      } else {
        Node nodeToRemove = succs[bottomLevel];
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

  boolean contains(int x) {
    int bottomLevel = 0;
    boolean[] marked = {false};
    Node pred = head;
    Node curr = null;
    Node succ = null;
    for (int level = MAX_LEVEL; level >= bottomLevel; level--) {
      curr = pred.next[level].getReference();
      while (true) {
        succ = curr.next[level].get(marked);
        while (marked[0]) {
          curr = pred.next[level].getReference();
          succ = curr.next[level].get(marked);
        }
        if (curr.value < x) {
          pred = curr;
          curr = succ;
        } else {
          break;
        }
      }
    }
    return (curr.value == x);
  }

  private boolean find(int item, Node[] preds, Node[] succs) {
    int bottomLevel = 0;
    boolean[] marked = {false};
    boolean success;
    Node pred = null, curr = null, succ = null;
    retry:
    while (true) {
      pred = head;
      for (int level = MAX_LEVEL; level >= bottomLevel; level--) {
        curr = pred.next[level].getReference();
        while (true) {
          succ = curr.next[level].get(marked);
          while (marked[0]) {
            success = pred.next[level].compareAndSet(curr, succ, false, false);
            if (!success) continue retry;

            curr = pred.next[level].getReference();
            succ = curr.next[level].get(marked);
          }
          if (curr.value < item) {
            pred = curr;
            curr = succ;
          } else break;
        }
        preds[level] = pred;
        succs[level] = curr;
      }
      return (curr.value == item);
    }
  }

  private int randomLevel(int max) {
    int level = 0;
    while (level < max) {
      int val = rand.nextInt() % 2;
      if (val == 1) {
        level++;
      } else {
        return level;
      }
    }
    return level;
  }
}
