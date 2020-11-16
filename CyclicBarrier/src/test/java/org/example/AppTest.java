package org.example;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

public class AppTest {

  @Test
  public void testGetPartiesAndCurrentlyWaitingThreads() {
    CustomCyclicBarrier b = new CustomCyclicBarrier(2);
    assertEquals(2, b.getParties());
    assertEquals(0, b.getPartiesCurrentlyWaiting());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeConstructorWithRunnable() {
    new CustomCyclicBarrier(-1, (Runnable) null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeConstructor() {
    new CustomCyclicBarrier(-1);
  }

  @Test
  public void testAwait() throws InterruptedException {
    countAction = 0;
    CustomCyclicBarrier b = new CustomCyclicBarrier(1, new MyAction());
    assertEquals(1, b.getParties());
    assertEquals(0, b.getPartiesCurrentlyWaiting());
    b.await();
    b.await();
    assertEquals(0, b.getPartiesCurrentlyWaiting());
    assertEquals(2, countAction);
  }

  @Test
  public void testSingleParty() throws Exception {
    CustomCyclicBarrier b = new CustomCyclicBarrier(1);
    assertEquals(1, b.getParties());
    assertEquals(0, b.getPartiesCurrentlyWaiting());
    b.await();
    b.await();
    assertEquals(0, b.getPartiesCurrentlyWaiting());
  }

  private int countAction;

  private class MyAction implements Runnable {
    public void run() {
      ++countAction;
    }
  }
}
