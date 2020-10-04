package org.example;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadsInformation {

  public ArrayList<Thread> processedThreads = new ArrayList<>();

  public Thread getInformation(ThreadGroup group) {
    Runnable runnable = () -> {
      while (group.activeCount() > 0) {
        ArrayList<ThreadInformation> threads = new ArrayList<>();
        getThreadGroups(group, threads);
        for (ThreadInformation thread : threads) {
          System.out.println(thread);
        }
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Thread t = new Thread(runnable);
    t.start();
    return t;
  }

  private void getThreadGroups(ThreadGroup group, ArrayList<ThreadInformation> threadGroups) {
    Thread[] threads = new Thread[group.activeCount()];
    group.enumerate(threads, false);
    for (Thread thread : threads) {
      if (thread == null) {
        continue;
      }
      threadGroups.add(new ThreadInformation(group.getName(), thread.getName(), thread.getId()));
      processedThreads.add(thread);
    }
    ThreadGroup[] subGroups = new ThreadGroup[group.activeGroupCount()];
    group.enumerate(subGroups, false);
    for (ThreadGroup subGroup : subGroups) {
      getThreadGroups(subGroup, threadGroups);
    }
  }

  class ThreadInformation {
    public String groupName;
    public String threadName;
    public long id;

    public ThreadInformation(String groupName, String threadName, long id) {
      this.groupName = groupName;
      this.threadName = threadName;
      this.id = id;
    }

    @Override
    public String toString() {
      return String.format("Group:%s, Thread Name:%s, Thread id: %s", groupName, threadName, id);
    }
  }

}

