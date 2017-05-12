/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

public class Direction {

    private boolean isLeft;
    private boolean isRight;

    public Direction(boolean isLeft) {
        this.isLeft = isLeft;
        this.isRight = !isLeft;
    }
    
    public Direction(Direction direction) {
        this.isLeft = direction.isLeft;
        this.isRight = !isLeft;
    }

    public boolean isLeft() {
        return isLeft;
    }

    /**
     * If isLeft is sat to true, isRight is sat to false.
     * @param isLeft 
     */
    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
        this.isRight = !isLeft;
    }

    public boolean isRight() {
        return isRight;
    }
    
    /**
     * If isRight is sat to true, isLeft is sat to false.
     * @param isRight 
     */
    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
        this.isLeft = !isRight;
    }
    
    

}
