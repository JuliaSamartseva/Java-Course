package org.example;

public class CustomPhaser {
  private int phase;
  /// Number of parties required to advance to the next phase
  private int parties;
  private int arrived;

  CustomPhaser() {
    this(0);
  }

  public CustomPhaser(int parties) {
    if (parties < 0) throw new IllegalArgumentException("Parties number should be more than 0.");
    this.parties = parties;
    phase = 0;
    arrived = 0;
  }

  public synchronized void register() {
    parties++;
  }

  /// Arrive at the phaser without waiting for others
  public synchronized void arrive() {
    arrived++;
    if (arrived == parties) updatePhaser();
  }

  /// Arrive at the phaser and wait for others
  public synchronized void arriveAndAwaitAdvance() throws InterruptedException {
    arrived++;
    if (arrived < parties) {
      wait();
    } else updatePhaser();
  }

  public synchronized void arriveAndDeregister() throws InterruptedException {
    parties--;
    if (parties < 0)
      throw new IllegalStateException("Number of registered parties is now less than a zero.");
  }

  public synchronized int awaitAdvance(int phase) throws InterruptedException {
    if (this.phase == phase) wait();
    else if (this.phase != phase + 1)
      throw new IllegalStateException("Phase number was incorrectly passed.");
    return this.phase;
  }

  public synchronized int getPhase() {
    return phase;
  }

  public synchronized int getRegisteredParties() {
    return parties;
  }

  public synchronized int getUnarrivedParties() {
    return parties - arrived;
  }

  public synchronized int getArrivedParties() {
    return arrived;
  }

  private void updatePhaser() {
    arrived = 0;
    phase++;
    notifyAll();
  }
}
