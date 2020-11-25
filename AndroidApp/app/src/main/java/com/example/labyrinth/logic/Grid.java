package com.example.labyrinth.logic;

public class Grid {
  private Cell[][] cells;
  private int columnsNumber;
  private int rowsNumber;

  public Grid(int rows, int columns) {
    columnsNumber = columns;
    rowsNumber = rows;
    cells = new Cell[rowsNumber][columnsNumber];
    for (int i = 0; i < rowsNumber; i++) {
      for (int j = 0; j < columnsNumber; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }
  }

  public int getColumnsNumber() {
    return columnsNumber;
  }

  public void setColumnsNumber(int columnsNumber) {
    this.columnsNumber = columnsNumber;
  }

  public int getRowsNumber() {
    return rowsNumber;
  }

  public void setRowsNumber(int rowsNumber) {
    this.rowsNumber = rowsNumber;
  }

  public Cell[][] getCells() {
    return cells;
  }

  public void setCells(Cell[][] cells) {
    this.cells = cells;
  }

  public static class Cell {
    public boolean left = true;
    public boolean right = true;
    public boolean top = true;
    public boolean bottom = true;

    private int row;
    private int column;

    public Cell(int row, int column) {
      this.row = row;
      this.column = column;
    }
  }
}
