package disruptor;

import java.util.ArrayList;
import java.util.List;

public class PositionHelper {
  int producerPosition = -1;
  List<Integer> readersPositions = new ArrayList<>();

  public int getProducerPosition() {
    return producerPosition;
  }

  public void setProducerPosition(int producerPosition) {
    this.producerPosition = producerPosition;
  }

  public void setReaderPosition(int position) {
    readersPositions.add(position);
  }

  public void unsetReaderPosition(int position) {
    readersPositions.remove(position);
  }

  public boolean isPositionInReaders(int position) {
    return readersPositions.contains(position);
  }
}
