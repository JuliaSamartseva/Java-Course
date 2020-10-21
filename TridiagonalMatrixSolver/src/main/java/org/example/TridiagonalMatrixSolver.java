package org.example;

import java.io.FileNotFoundException;

public class TridiagonalMatrixSolver {
  double[] A;
  double[] B;
  double[] C;

  void solveMatrix(String path) {
    double[][] matrix = getMatrix(path);
    distributeMatrix(matrix);
  }



  private double[][] getMatrix(String path) {
    TridiagonalMatrix matrix = null;
    try {
      matrix = new TridiagonalMatrix(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assert matrix != null;
    return matrix.getMatrix();
  }

  private void distributeMatrix(double[][] matrix) {
    int n = matrix.length;

    A = new double[n];
    B = new double[n];
    C = new double[n];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        A[i] = matrix[i][j];
        if (j+1 != n - 1) B[i] = matrix[i][j+1];
        if (i+1 != n - 1) C[i+1] = matrix[i+1][j];
      }
  }


}
