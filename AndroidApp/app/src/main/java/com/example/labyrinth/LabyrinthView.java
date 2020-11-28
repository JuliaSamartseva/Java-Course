package com.example.labyrinth;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.labyrinth.logic.Ball;
import com.example.labyrinth.logic.Direction;
import com.example.labyrinth.logic.Grid;

public class LabyrinthView extends View {
  private Paint painter;
  private Paint player, exit;
  private static final int WALL_WIDTH = 5;
  private static final int EXIT_WIDTH = 5;
  private static final int ROWS = 15;
  private static final int COLUMNS = 10;
  private Grid grid;
  private int cellSize;
  private int heightMargin;
  private int widthMargin;
  private Ball ball;
  private int ballColor;
  private boolean gameStarted = false;

  public LabyrinthView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    painter = new Paint();
    player = new Paint();
    exit = new Paint();
    ballColor = ContextCompat.getColor(context, R.color.colorBall);
    player.setColor(ballColor);
    exit.setColor(ballColor);
    exit.setStyle(Paint.Style.STROKE);
    exit.setStrokeWidth(EXIT_WIDTH);
    painter.setColor(ContextCompat.getColor(context, R.color.colorBorder));
    painter.setStrokeWidth(WALL_WIDTH);
    grid = new Grid(ROWS, COLUMNS);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    displayLabyrinth(canvas);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_MOVE
        || event.getAction() == MotionEvent.ACTION_DOWN) {
      float x = event.getX();
      float y = event.getY();

      int xPlayerCoordinate = getXCoordinate(grid.getPlayerLocation());
      int yPlayerCoordinate = getYCoordinate(grid.getPlayerLocation());

      float dx = x - xPlayerCoordinate;
      float dy = y - yPlayerCoordinate;

      if (Math.abs(dx) > cellSize || Math.abs(dy) > cellSize) {
        boolean moved = false;
        Direction direction;
        if (Math.abs(dx) > Math.abs(dy)) {
          if (dx > 0) direction = Direction.RIGHT;
          else direction = Direction.LEFT;
        } else {
          if (dy > 0) direction = Direction.DOWN;
          else direction = Direction.UP;
        }

        moved = grid.movePlayer(direction);

        if (moved) {
          int radius = (int) ((cellSize / 2) * 0.8);
          xPlayerCoordinate = getPlayerXCoordinate(grid.getPlayerLocation(), radius);
          yPlayerCoordinate = getPlayerYCoordinate(grid.getPlayerLocation(), radius);
          ObjectAnimator animator = getAnimator(xPlayerCoordinate, yPlayerCoordinate);
          animator.start();
          if (grid.getPlayerLocation() == grid.getExitLocation()) grid.updateLabyrinth();
        }
      }
      return true;
    }
    return super.onTouchEvent(event);
  }

  private ObjectAnimator getAnimator(int x, int y) {
    ObjectAnimator animator;
    Path path = new Path();
    path.moveTo(ball.getX(), ball.getY());
    path.lineTo(x, y);
    animator = ObjectAnimator.ofFloat(ball, "x", "y", path);
    animator.setDuration(500);
    animator.addUpdateListener(
        new ObjectAnimator.AnimatorUpdateListener() {
          public void onAnimationUpdate(ValueAnimator animation) {
            invalidate();
          }
        });
    return animator;
  }

  private void displayLabyrinth(Canvas canvas) {
    cellSize = calculateCellSize();
    heightMargin = calculateHeightMargin(cellSize);
    widthMargin = calculateWidthMargin(cellSize);

    int radius = (int) ((cellSize / 2) * 0.8);

    int xExitCoordinate = getXCoordinate(grid.getExitLocation());
    int yExitCoordinate = getYCoordinate(grid.getExitLocation());

    canvas.save();
    canvas.drawCircle(xExitCoordinate, yExitCoordinate, radius, exit);
    canvas.translate(widthMargin, heightMargin);
    for (int row = 0; row < grid.getRowsNumber(); row++) {
      for (int column = 0; column < grid.getColumnsNumber(); column++) {
        if (grid.getCells()[row][column].top)
          canvas.drawLine(
              column * cellSize, row * cellSize, (column + 1) * cellSize, row * cellSize, painter);
        if (grid.getCells()[row][column].bottom)
          canvas.drawLine(
              column * cellSize,
              (row + 1) * cellSize,
              (column + 1) * cellSize,
              (row + 1) * cellSize,
              painter);
        if (grid.getCells()[row][column].left)
          canvas.drawLine(
              column * cellSize, row * cellSize, column * cellSize, (row + 1) * cellSize, painter);
        if (grid.getCells()[row][column].right)
          canvas.drawLine(
              (column + 1) * cellSize,
              row * cellSize,
              (column + 1) * cellSize,
              (row + 1) * cellSize,
              painter);
      }
    }

    if (!gameStarted) {
      int xPlayerCoordinate = getPlayerXCoordinate(grid.getPlayerLocation(), radius);
      int yPlayerCoordinate = getPlayerYCoordinate(grid.getPlayerLocation(), radius);
      ball = new Ball(xPlayerCoordinate, yPlayerCoordinate, radius, ballColor);
      gameStarted = true;
    }
    canvas.restore();
    canvas.translate(ball.getX(), ball.getY());
    ball.getShapeDrawable().draw(canvas);
  }

  private int getPlayerXCoordinate(Grid.Cell x, int radius) {
    return (int) (widthMargin + x.column * cellSize + (cellSize / 2 - radius));
  }

  private int getPlayerYCoordinate(Grid.Cell x, int radius) {
    return (int) (heightMargin + x.row * cellSize + (cellSize / 2 - radius));
  }

  private int getXCoordinate(Grid.Cell x) {
    return widthMargin + x.column * cellSize + (cellSize / 2);
  }

  private int getYCoordinate(Grid.Cell x) {
    return heightMargin + x.row * cellSize + (cellSize / 2);
  }

  private int calculateCellSize() {
    int canvasWidth = getWidth();
    int result = canvasWidth / (grid.getColumnsNumber() + 1);

    return result;
  }

  private int calculateHeightMargin(int cellSize) {
    return (getHeight() - grid.getRowsNumber() * cellSize) / 2;
  }

  private int calculateWidthMargin(int cellSize) {
    return (getWidth() - grid.getColumnsNumber() * cellSize) / 2;
  }
}
