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
  private static final int WALL_WIDTH = 4;
  private static final int ROWS = 8;
  private static final int COLUMNS = 15;
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

    canvas.drawColor(Color.WHITE);
    int heightMargin = calculateHeightMargin(cellSize);
    int widthMargin = calculateWidthMargin(cellSize);

    canvas.translate(heightMargin, widthMargin);
    for (int i = 0; i < grid.getRowsNumber(); i++) {
      for (int j = 0; j < grid.getColumnsNumber(); j++) {
        if (grid.getCells()[i][j].top)
          canvas.drawLine(i * cellSize, j * cellSize, (i + 1) * cellSize, j * cellSize, painter);
        if (grid.getCells()[i][j].bottom)
          canvas.drawLine(
              i * cellSize, (j + 1) * cellSize, (i + 1) * cellSize, (j + 1) * cellSize, painter);
        if (grid.getCells()[i][j].left)
          canvas.drawLine(i * cellSize, j * cellSize, i * cellSize, (j + 1) * cellSize, painter);
        if (grid.getCells()[i][j].right)
          canvas.drawLine(
              (i + 1) * cellSize, j * cellSize, (i + 1) * cellSize, (j + 1) * cellSize, painter);
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
