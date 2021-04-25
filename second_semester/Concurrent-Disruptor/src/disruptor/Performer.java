package disruptor;

abstract class Performer<E> implements Runnable {
  int position = -1;
  PositionHelper positionHelper;
  CircularBuffer<E> buffer;

  public Performer(CircularBuffer<E> buffer, PositionHelper positionHelper) {
    this.buffer = buffer;
    this.positionHelper = positionHelper;
  }

  public int getPosition() {
    return position;
  }
}
