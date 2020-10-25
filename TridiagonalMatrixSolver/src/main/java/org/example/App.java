package org.example;

public class App {

  public static void main(String[] args) throws InterruptedException {
    MatrixSolver solver = new MatrixSolver();
    solver.solveMatrix("src/matrix.txt");
  }
}
