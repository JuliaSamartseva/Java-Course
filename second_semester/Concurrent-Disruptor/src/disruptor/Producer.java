package disruptor;

import java.util.List;
import java.util.Random;

public class Producer<E> extends Performer<E> {
  List<E> objects;

  public Producer(List<E> objects, CircularBuffer<E> buffer, PositionHelper positionHelper) {
    super(buffer, positionHelper);
    this.objects = objects;
  }

  public boolean putInBuffer(E object) throws Exception {
    if (positionHelper.isPositionInReaders(buffer.getNextPosition(position))) {
      // Do nothing because one of readers is still reading from this position;
      return false;
    }
    position = buffer.offer(object);
    System.out.print("Producer puts to position (" + position + "): ");
    System.out.println(object.toString());
    positionHelper.setProducerPosition(position);
    return true;
  }

  @Override
  public void run() {
    System.out.println("Producer starts.");
    Random r = new Random();
    int i = 0;
    do {
      try {
        if (putInBuffer(objects.get(i))) {
          i++;
        }
        Thread.sleep(
            r.nextInt(1000 - 500)
                + 500); // Stops for 0.5 to 1 second. Emulate not very long running process
      } catch (Exception e) {
        e.printStackTrace();
      }

    } while (i < objects.size());
  }
}
