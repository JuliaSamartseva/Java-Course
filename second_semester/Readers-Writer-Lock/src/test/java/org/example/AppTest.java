package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {

  // At the time of reading writing is not allowed.
  @Test
  public void testWriteAfterReadLock() throws InterruptedException {
    Service executor = new Service(1);
    final CustomReadersWriterLock lock = new ReadersWriterLock();

    lock.readLock().lock();
    Runnable t =
        () -> {
          lock.writeLock().lock();
          lock.writeLock().unlock();
        };
    ArrayList<Runnable> runnables = new ArrayList<>();
    runnables.add(t);
    executor.execute(runnables);
    Thread.sleep(10);
    assertFalse(executor.isComplete());
    lock.readLock().unlock();
    assertFalse(executor.isComplete());
  }

  // Reads succeed only after a writing thread unlocks
  @Test
  public void testReadAfterWriteLock() throws InterruptedException {
    Service executor = new Service(2);
    final ReadersWriterLock lock = new ReadersWriterLock();
    lock.writeLock().lock();
    Runnable t1 =
        () -> {
          System.out.println("Hello world3");
          lock.readLock().lock();
          System.out.println("Hello world4");
          lock.readLock().unlock();
          System.out.println("Hello world5");
        };

    Runnable t2 =
        () -> {
          lock.readLock().lock();
          lock.readLock().unlock();
        };

    ArrayList<Runnable> runnables = new ArrayList<>();
    runnables.add(t1);
    runnables.add(t2);
    executor.execute(runnables);
    Thread.sleep(10);
    assertFalse(executor.isComplete());
    lock.writeLock().unlock();
    Thread.sleep(10);
    assertTrue(executor.isComplete());
  }

  @Test
  public void testMultipleReads() throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    final CustomReadersWriterLock lock = new ReadersWriterLock();
    lock.readLock().lock();
    Thread t =
        new Thread(
            () -> {
              lock.readLock().lock();
              lock.readLock().unlock();
              lock.readLock().lock();
              lock.readLock().unlock();
              lock.readLock().lock();
              lock.readLock().unlock();
            });
    executor.execute(t);
    executor.awaitTermination(10, TimeUnit.MILLISECONDS);
    lock.readLock().unlock();
  }

  static class Service {
    private final ExecutorService executor;
    private List<Future<?>> futures;

    public Service(int nThreads) {
      executor = Executors.newFixedThreadPool(nThreads);
    }

    public void execute(Collection<Runnable> runs) {
      futures = runs.stream().map(executor::submit).collect(Collectors.toList());
    }

    public boolean isComplete() {
      for (Future<?> future : futures) if (!future.isDone()) return false;

      return true;
    }
  }
}
