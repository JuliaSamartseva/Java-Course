package com.example.labyrinth.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

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
    generateLabyrinth();
  }

  private void generateLabyrinth() {
    Stack<Cell> stack = new Stack<>();
    Cell current = cells[0][0];
    HashSet<Cell> visited = new HashSet<>();
    visited.add(current);
    do {
      visited.add(current);
      Cell next = getNeighbour(current, visited);
      if (next != null) {
        removeWall(current, next);
        stack.push(current);
        current = next;
      } else current = stack.pop();
    } while (!stack.isEmpty());
  }

  private void removeWall(Cell current, Cell next) {
    if (current.column == next.column && current.row == next.row + 1) {
      current.top = false;
      next.bottom = false;
    }
    if (current.column == next.column && current.row == next.row - 1) {
      current.bottom = false;
      next.top = false;
    }
    if (current.column == next.column + 1 && current.row == next.row) {
      current.left = false;
      next.right = false;
    }
    if (current.column == next.column - 1 && current.row == next.row) {
      current.right = false;
      next.left = false;
    }
  }

  private Cell getNeighbour(Cell cell, HashSet<Cell> visited) {
    ArrayList<Cell> neighbours = new ArrayList<>();

    if (cell.column > 0 && !visited.contains(cells[cell.row][cell.column - 1]))
      neighbours.add(cells[cell.row][cell.column - 1]);

    if (cell.row > 0 && !visited.contains(cells[cell.row - 1][cell.column]))
      neighbours.add(cells[cell.row - 1][cell.column]);

    if (cell.column + 1 < columnsNumber && !visited.contains(cells[cell.row][cell.column + 1]))
      neighbours.add(cells[cell.row][cell.column + 1]);

    if (cell.row + 1 < rowsNumber && !visited.contains(cells[cell.row + 1][cell.column]))
      neighbours.add(cells[cell.row + 1][cell.column]);

    if (neighbours.size() > 0) {
      Random random = new Random();
      int index = random.nextInt(neighbours.size());
      return neighbours.get(index);
    } else return null;
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
