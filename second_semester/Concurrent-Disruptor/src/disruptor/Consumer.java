package disruptor;

import java.util.Random;

public class Consumer<E> extends Performer<E> {

  String consumerName;

  public Consumer(String consumerName, CircularBuffer<E> buffer, PositionHelper positionHelper) {
    super(buffer, positionHelper);
    this.consumerName = consumerName;
    position = -1;
  }

  public void readFromBuffer() throws Exception {
    if (positionHelper.getProducerPosition() == -1
        || positionHelper.getProducerPosition() == buffer.getNextPosition(position)) {
      // Producer has not yet filled in this position. Do nothing;
      return;
    }
    positionHelper.unsetReaderPosition(position);
    this.position = buffer.getNextPosition(position); // Moves to the next position
    E object = buffer.poll(position); // Reads from this new position
    System.out.print("Consumer " + consumerName + " reads from position (" + position + "): ");
    System.out.println(object.toString());
    positionHelper.setReaderPosition(position);
  }

  @Override
  public void run() {
    System.out.println("Consumer " + consumerName + " starts.");
    Random r = new Random();
    while (true) {
      try {
        readFromBuffer();
        Thread.sleep(
            r.nextInt(5000 - 1000)
                + 1000); // Stops for 1 to 5 seconds. Emulate long running process
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
