package org.example.combineResults;

public class CombineResults implements Runnable {
  private Data data;

  public CombineResults(Data data) {
    this.data = data;
  }

  @Override
  public void run() {
    int result = data.a + data.b;
    System.out.println("Combined result: ");
    System.out.println("a = " + data.a);
    System.out.println("b = " + data.b);
    System.out.println("a + b = " + result);
  }
}
