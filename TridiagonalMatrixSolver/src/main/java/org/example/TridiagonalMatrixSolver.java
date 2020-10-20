package org.example;

import java.io.FileNotFoundException;

public class TridiagonalMatrixSolver {

  void solveMatrix(String path) {
    TridiagonalMatrix matrix = null;
    try {
      matrix = new TridiagonalMatrix(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assert matrix != null;
    double[][] tridiagonalMatrix = matrix.getMatrix();
  }

  void distributeMatrix(double[][] matrix) {
    double B[];
    double A[];
    double C[];

    for (int i = 0; i < )
  }


}
