package disruptor;

import java.util.ArrayList;
import java.util.List;

public class Disruptor {
  public static void main(String[] argv) {
    System.out.println("Start application");
    System.out.println("Create a producer");

    CircularBuffer<String> buffer = new CircularBuffer<>(10);
    PositionHelper positionHelper = new PositionHelper();
    List<String> data = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      data.add("String #" + i);
    }

    Thread producerThread = new Thread(new Producer<>(data, buffer, positionHelper));
    Thread consumerAThread = new Thread(new Consumer<>("A", buffer, positionHelper));
    Thread consumerBThread = new Thread(new Consumer<>("B", buffer, positionHelper));
    Thread consumerCThread = new Thread(new Consumer<>("C", buffer, positionHelper));

    producerThread.start();
    consumerAThread.start();
    consumerBThread.start();
    consumerCThread.start();
  }
}
