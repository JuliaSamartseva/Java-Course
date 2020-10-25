package org.example;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixSolver {
  private int n;
  private double[] A;
  private double[] B;
  private double[] C;
  private double[] function;
  private double[] alpha;
  private double[] betta;
  private double[] ksi;
  private double[] psi;
  private double[] result;
  private int num;

  void solveMatrix(String path) throws InterruptedException {
    double[][] matrix = inputMatrix(path);
    distributeMatrix(matrix);
    result = new double[n + 1];

    Thread left = firstThread();
    Thread right = secondThread();

    left.start();
    right.start();

    left.join();
    right.join();

    num = (int) Math.floor(n / 2);

    result[num] = (alpha[num] * psi[num] + betta[num]) / (1 - alpha[num] * ksi[num]);
    result[num + 1] = (ksi[num + 1] * betta[num + 1] + psi[num + 1]) / (1 - ksi[num + 1] * alpha[num + 1]);

    Thread leftResult = firstThreadResult();
    Thread rightResult = secondThreadResult();

    leftResult.start();
    rightResult.start();

    leftResult.join();
    rightResult.join();

    for (int i = 1; i < result.length; i++) {
      System.out.println(result[i]);
    }
  }

  private Thread firstThread() {
    alpha = new double[n + 1];
    betta = new double[n + 1];

    final int num = (int) Math.floor(n / 2);
    return new Thread(
        new Runnable() {
          @Override
          public void run() {
            alpha[2] = -B[1] / C[1];
            betta[2] = function[1] / C[1];

            for (int i = 2; i <= num; i++) {
              alpha[i + 1] = -B[i] / (A[i] * alpha[i] + C[i]);
              betta[i + 1] = (function[i] - A[i] * betta[i]) / (A[i] * alpha[i] + C[i]);
            }
          }
        });
  }

  private Thread secondThread() {
    ksi = new double[n + 1];
    psi = new double[n + 1];

    return new Thread(
        new Runnable() {
          @Override
          public void run() {
            ksi[n] = -A[n] / C[n];
            psi[n] = function[n] / C[n];

            for (int i = n - 1; i >= num; i--) {
              ksi[i] = -A[i] / (C[i] + B[i] * ksi[i + 1]);
              psi[i] = (function[i] - B[i] * psi[i + 1]) / (C[i] + B[i] * ksi[i + 1]);
            }
          }
        });
  }

  private Thread firstThreadResult() {
    return new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = num - 1; i > 0; i--)
          result[i] = alpha[i + 1] * result[i + 1] + betta[i + 1];
      }
    });
  }

  private Thread secondThreadResult() {
    return new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = num; i < n; i++)
          result[i + 1] = ksi[i + 1] * result[i] + psi[i + 1];
      }
    });
  }

  private double[][] inputMatrix(String path) {
    Matrix matrix = null;
    try {
      matrix = new Matrix(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assert matrix != null;
    function = matrix.getFunction();
    return matrix.getMatrix();
  }

  private void distributeMatrix(double[][] matrix) {
    n = matrix.length;

    A = new double[n + 1];
    B = new double[n + 1];
    C = new double[n + 1];

    int cIndex = 1;
    int bIndex = 1;
    int aIndex = 2;

    for (int i = 1; i < n + 1; i++) {
      C[cIndex++] = matrix[i - 1][i - 1];
      if (cIndex != n + 1) {
        B[bIndex++] = matrix[i - 1][i];
        A[aIndex++] = matrix[i][i - 1];
      }
    }
  }
}
