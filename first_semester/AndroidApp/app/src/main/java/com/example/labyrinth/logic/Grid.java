package com.example.labyrinth.logic;

import java.lang.reflect.Array;
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

  private Direction[] directions = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};

  public Grid(int rows, int columns) {
    columnsNumber = columns;
    rowsNumber = rows;
    cells = initialiseCells();
    generateLabyrinth();

    playerLocation = cells[0][0];
    exitLocation = cells[rowsNumber - 1][columnsNumber - 1];
  }

  public boolean movePlayer(Direction direction) {
    boolean canMove = canMove(playerLocation, direction);
    if (canMove
        && canTraverse(
            playerLocation.row + direction.shifts[0], playerLocation.column + direction.shifts[1]))
      playerLocation =
          cells[playerLocation.row + direction.shifts[0]][
              playerLocation.column + direction.shifts[1]];
    return canMove;
  }

  private boolean canMove(Cell currentCell, Direction direction) {
    switch (direction) {
      case UP:
        if (!currentCell.top) return true;
        break;
      case DOWN:
        if (!currentCell.bottom) return true;
        break;
      case LEFT:
        if (!currentCell.left) return true;
        break;
      case RIGHT:
        if (!currentCell.right) return true;
        break;
    }
    return false;
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

  public ArrayList<Cell> getSolution() {
    ArrayList<Cell> result = new ArrayList<>();
    result.add(playerLocation);
    HashSet<Cell> visited = new HashSet<>();
    visited.add(playerLocation);
    if (!hasPathToEnd(playerLocation, result, visited)) result.remove(result.size() - 1);
    return result;
  }

  private boolean hasPathToEnd(Cell node, ArrayList<Cell> path, HashSet<Cell> visited) {
    if (node.equals(exitLocation)) return true;

    for (Direction direction : directions) {
      int row = node.row + direction.shifts[0];
      int column = node.column + direction.shifts[1];
      if (canTraverse(row, column)) {
        Cell next = cells[row][column];
        if (!visited.contains(next) && canMove(node, direction)) {
          visited.add(next);
          path.add(next);
          if (hasPathToEnd(next, path, visited)) return true;
          path.remove(path.size() - 1);
        }
      }
    }
    return false;
  }

  public boolean canTraverse(int row, int column) {
    return row >= 0 && row < rowsNumber && column >= 0 && column < columnsNumber;
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
