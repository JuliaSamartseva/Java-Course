package org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
  private void testState(CustomPhaser phaser, int phase, int parties, int unarrived) {
    assertEquals(phase, phaser.getPhase());
    assertEquals(parties, phaser.getRegisteredParties());
    assertEquals(unarrived, phaser.getUnarrivedParties());
    assertEquals(parties - unarrived, phaser.getArrivedParties());
  }

  @Test
  public void testRegister() {
    CustomPhaser phaser = new CustomPhaser();
    testState(phaser, 0, 0, 0);
    phaser.register();
    testState(phaser, 0, 1, 1);
  }

  @Test
  public void testRegister2() {
    CustomPhaser phaser = new CustomPhaser();
    phaser.register();
    phaser.arrive();
    phaser.register();
    testState(phaser, 1, 2, 2);
  }

  @Test
  public void testPhaseChange() {
    CustomPhaser phaser = new CustomPhaser(1);
    phaser.arrive();
    phaser.register();
    phaser.arrive();
    testState(phaser, 1, 2, 1);
  }

  @Test
  public void testArrive() {
    CustomPhaser phaser = new CustomPhaser(1);
    testState(phaser, 0, 1, 1);
    phaser.arrive();
    testState(phaser, 1, 1, 1);
  }

  @Test
  public void testArriveAndDeregister() throws InterruptedException {
    CustomPhaser phaser = new CustomPhaser(1);
    testState(phaser, 0, 1, 1);
    phaser.register();
    testState(phaser, 0, 2, 2);
    phaser.arriveAndDeregister();
    testState(phaser, 0, 1, 1);
  }

  @Test
  public void testAwaitAdvance() throws InterruptedException {
    final CustomPhaser phaser = new CustomPhaser(1);
    phaser.arrive();
    assertEquals(1, phaser.awaitAdvance(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeConstructorValues() {
    new CustomPhaser(-1);
  }

  @Test
  public void testDefaultValues() {
    CustomPhaser phaser = new CustomPhaser();
    assertEquals(0, phaser.getRegisteredParties());
    assertEquals(0, phaser.getArrivedParties());
    assertEquals(0, phaser.getUnarrivedParties());
    assertEquals(0, phaser.getPhase());
  }
}
