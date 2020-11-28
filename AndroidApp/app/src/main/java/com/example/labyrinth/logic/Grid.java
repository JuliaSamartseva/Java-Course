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

  private Cell playerLocation;
  private Cell exitLocation;

  public Grid(int rows, int columns) {
    columnsNumber = columns;
    rowsNumber = rows;
    cells = initialiseCells();
    generateLabyrinth();

    playerLocation = cells[0][0];
    exitLocation = cells[rowsNumber - 1][columnsNumber - 1];
  }

  public boolean movePlayer(Direction direction) {
    boolean successfullyMoved = false;
    switch (direction) {
      case UP:
        if (!playerLocation.top) {
          playerLocation = cells[playerLocation.row - 1][playerLocation.column];
          successfullyMoved = true;
        }
        break;
      case DOWN:
        if (!playerLocation.bottom) {
          playerLocation = cells[playerLocation.row + 1][playerLocation.column];
          successfullyMoved = true;
        }
        break;
      case LEFT:
        if (!playerLocation.left) {
          playerLocation = cells[playerLocation.row][playerLocation.column - 1];
          successfullyMoved = true;
        }
        break;
      case RIGHT:
        if (!playerLocation.right) {
          playerLocation = cells[playerLocation.row][playerLocation.column + 1];
          successfullyMoved = true;
        }
        break;
    }
    return successfullyMoved;
  }

  public void updateLabyrinth() {
    for (int i = 0; i < rowsNumber; i++) {
      for (int j = 0; j < columnsNumber; j++) {
        cells[i][j].updateCell();
      }
    }
    generateLabyrinth();
    playerLocation = cells[0][0];
    exitLocation = cells[rowsNumber - 1][columnsNumber - 1];
  }

  private Cell[][] initialiseCells() {
    Cell[][] result = new Cell[rowsNumber][columnsNumber];
    for (int i = 0; i < rowsNumber; i++) {
      for (int j = 0; j < columnsNumber; j++) {
        result[i][j] = new Cell(i, j);
      }
    }
    return result;
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

  public Cell getPlayerLocation() {
    return playerLocation;
  }

  public Cell getExitLocation() {
    return exitLocation;
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

    public int row;
    public int column;

    public Cell(int row, int column) {
      this.row = row;
      this.column = column;
    }

    private void updateCell() {
      left = true;
      top = true;
      bottom = true;
      right = true;
    }
  }
}
