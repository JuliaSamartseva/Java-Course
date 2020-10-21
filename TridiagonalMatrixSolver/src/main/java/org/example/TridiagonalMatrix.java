package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TridiagonalMatrix {
  double[][] matrix;

  public TridiagonalMatrix(String path) throws FileNotFoundException {
    Scanner input = new Scanner(new File(path));
    int rows = 0;
    int columns = 0;
    while (input.hasNextLine()) {
      ++rows;
      Scanner colReader = new Scanner(input.nextLine());
      while (colReader.hasNextInt()) {
        ++columns;
      }
    }
    matrix = new double[rows][columns];

    input.close();

    input = new Scanner(new File(path));
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        if (input.hasNextInt()) {
          matrix[i][j] = input.nextInt();
        }
      }
    }

    if (!validateMatrix()) throw new IllegalArgumentException("The matrix is not tridiagonal.");
  }

  private boolean validateMatrix() {
    for (int x = 0; x < matrix.length; x++) {
      if (matrix.length != matrix[x].length) return false;

      for (int y = 0; y < matrix[x].length; y++) {
        double cell = matrix[x][y];

        if ((x == y) || (x - 1 == y) || (x + 1 == y)) {
          if (cell == 0) return false;
        } else {
          if (cell != 0) return false;
        }
      }
    }
    return true;
  }

  public double[][] getMatrix() {
    return matrix;
  }
}
