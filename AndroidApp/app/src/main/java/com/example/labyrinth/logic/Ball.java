package com.example.labyrinth.logic;

import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;

import androidx.core.content.ContextCompat;

import com.example.labyrinth.R;

public class Ball {
  private float x;
  private float y;
  private Shape shape;
  private ShapeDrawable shapeDrawable;
  private Paint paint;

  public Ball(float x, float y, int radius, int color) {
    shape = new OvalShape();
    shape.resize(radius * 2, radius * 2);
    shapeDrawable = new ShapeDrawable(shape);
    paint = shapeDrawable.getPaint();
    paint.setColor(color);
    this.x = x;
    this.y = y;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public ShapeDrawable getShapeDrawable() {
    return shapeDrawable;
  }

  public float getWidth() {
    return shape.getWidth();
  }

  public void setWidth(float width) {
    shape.resize(width, shape.getHeight());
  }

  public float getHeight() {
    return shape.getHeight();
  }

  public void setHeight(float height) {
    shape.resize(shape.getWidth(), height);
  }

  public void setAlpha(float alpha) {
    shapeDrawable.setAlpha((int) (alpha * 255f + 0.5f));
  }
}