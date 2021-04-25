package disruptor;

public class CircularBuffer<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private final int capacity;
  private final E[] data;
  private int sequence;

  public CircularBuffer() {
    this(-1);
  }

  public CircularBuffer(int capacity) {
    this.capacity = (capacity < 1) ? DEFAULT_CAPACITY : capacity;
    this.data = (E[]) new Object[this.capacity];
    this.sequence = -1;
  }

  public int getCapacity() {
    return capacity;
  }

  public int offer(E element) throws Exception {
    sequence = getNextPosition(sequence);
    if (sequence > capacity - 1) {
      sequence = 0;
    }
    data[sequence] = element;
    return sequence;
  }

  public E poll(int position) throws Exception {
    if (position < 0 || position > capacity - 1) {
      throw new Exception("Wrong position");
    }
    return data[position];
  }

  public int getNextPosition(int position) throws Exception {
    if (position > capacity - 1) {
      throw new Exception("Wrong position");
    }
    position++;
    if (position > capacity - 1) {
      return 0;
    }
    return position;
  }
}
