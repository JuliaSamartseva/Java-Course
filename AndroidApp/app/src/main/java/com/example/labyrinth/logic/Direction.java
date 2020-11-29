package com.example.labyrinth.logic;

public enum Direction {
  UP(new int[]{-1, 0}),
  DOWN(new int[]{1, 0}),
  LEFT(new int[]{0, -1}),
  RIGHT(new int[]{0, 1});

  public final int[] shifts;

  Direction(int[] shifts) {
    this.shifts = shifts;
  }
}
