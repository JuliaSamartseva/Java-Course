package com.example.labyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.labyrinth.logic.Grid;

public class LabyrinthView extends View {
  private Paint painter;
  private static final int WALL_WIDTH = 5;
  private static final int ROWS = 15;
  private static final int COLUMNS = 8;
  private Grid grid;

  public LabyrinthView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    painter = new Paint();
    painter.setColor(ContextCompat.getColor(context, R.color.colorBorder));
    painter.setStrokeWidth(WALL_WIDTH);
    grid = new Grid(ROWS, COLUMNS);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int cellSize = calculateCellSize();

    int heightMargin = calculateHeightMargin(cellSize);
    int widthMargin = calculateWidthMargin(cellSize);

    canvas.translate(widthMargin, heightMargin);
    for (int row = 0; row < grid.getRowsNumber(); row++) {
      for (int column = 0; column < grid.getColumnsNumber(); column++) {
        if (grid.getCells()[row][column].top)
          canvas.drawLine(column * cellSize, row * cellSize, (column + 1) * cellSize, row * cellSize, painter);
        if (grid.getCells()[row][column].bottom)
          canvas.drawLine(
              column * cellSize, (row + 1) * cellSize, (column + 1) * cellSize, (row + 1) * cellSize, painter);
        if (grid.getCells()[row][column].left)
          canvas.drawLine(column * cellSize, row * cellSize, column * cellSize, (row + 1) * cellSize, painter);
        if (grid.getCells()[row][column].right)
          canvas.drawLine(
              (column + 1) * cellSize, row * cellSize, (column + 1) * cellSize, (row + 1) * cellSize, painter);
      }
    }
  }

  private int calculateCellSize() {
    int cellSize;

    int canvasWidth = getWidth();
    int canvasHeight = getHeight();
    cellSize = canvasWidth / (grid.getColumnsNumber() + 1);

    return cellSize;
  }

  private int calculateHeightMargin(int cellSize) {
    return (getHeight() - grid.getRowsNumber() * cellSize) / 2;
  }

  private int calculateWidthMargin(int cellSize) {
    return (getWidth() - grid.getColumnsNumber() * cellSize) / 2;
  }
}
