package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
  double[][] matrix;
  double[] function;

  public Matrix(String path) throws FileNotFoundException, IllegalArgumentException {

    Scanner input = new Scanner(new File(path));
    int rows = 0;
    int columns = 0;
    while (input.hasNextLine()) {
      ++rows;
      Scanner colReader = new Scanner(input.nextLine());
      columns = 0;
      while (colReader.hasNextInt()) {
        colReader.nextInt();
        ++columns;
      }
    }

    if (rows != columns - 1)
      throw new IllegalArgumentException(
          "The matrix is not squared. Rows "
              + Integer.toString(rows)
              + " Columns "
              + Integer.toString(columns));
    matrix = new double[rows][columns - 1];
    function = new double[rows + 1];

    input.close();

    input = new Scanner(new File(path));

    int fCounter = 1;
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        if (input.hasNextInt()) {
          /// Input the last column to the function.
          if (j == columns - 1) function[fCounter++] = input.nextInt();
          else matrix[i][j] = input.nextInt();
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

  public double[] getFunction() {
    return function;
  }
}
