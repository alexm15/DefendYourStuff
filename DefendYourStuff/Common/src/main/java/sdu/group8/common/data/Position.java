/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

/**
 *
 * @author Martin
 */
public class Position {

    private float previousX;
    private float x;
    private float y;
    private boolean directionLeft;
    private boolean directionRight;

    public Position(float x, float y, boolean directionLeft, boolean directionRight) {
        this.previousX = x;
        this.x = x;
        this.y = y;
        this.directionLeft = directionLeft;
        this.directionRight = directionRight;
    }
    
    public Position(float x, float y) {
        this.previousX = x;
        this.x = x;
        this.y = y;
        this.directionLeft = false;
        this.directionRight = false;
    }

    public boolean isDirectionLeft() {
        return directionLeft;
    }

    public boolean isDirectionRight() {
        return directionRight;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.previousX = this.x;
        this.x = x;
        setDirection();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.previousX = this.x;
        this.x = x;
        this.y = y;
        setDirection();
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + y;
    }

    private void setDirection() {
        if (previousX > x) {
            directionLeft = true;
            directionRight = false;
        } else if (previousX < x) {
            directionRight = true;
            directionLeft = false;
        }
    }
}
