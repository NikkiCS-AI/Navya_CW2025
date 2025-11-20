package com.comp2042;

import com.comp2042.logic.bricks.Brick;

public class BrickRotator {

    private Brick brick;
    private int currentShape = 0;

    //gets the next shape of the brick without changing the current shape
    public NextShapeInfo getNextShape() {
        int nextShape = currentShape;
        nextShape = (++nextShape) % brick.getShapeMatrix().size();
        return new NextShapeInfo(brick.getShapeMatrix().get(nextShape), nextShape);
    }

    //gets the current shape of the brick
    public int[][] getCurrentShape() {
        return brick.getShapeMatrix().get(currentShape);
    }

    //applies the next shape of the brick
    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    //assigns new brick to rotator
    public void setBrick(Brick brick) {
        this.brick = brick;
        currentShape = 0;
    }

    public Brick getBrick() {
        return brick;
    }



}
