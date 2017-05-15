package sdu.group8.common.data;

/**
 * Represents which direction the entity's image is facing and which 
 * direction entity is moving.
 * @author Group 8
 */
public class Direction {

    private boolean left = true;
    private boolean right = false;

    /**
     * Creates a direction that an entity is facing, left and right boolean 
     * must have different values.
     * @param isLeft entity is facing left if true.
     */
    public Direction(boolean isLeft) {
        if (this.left == this.right) {
            throw new IllegalArgumentException("Left and Right boolean cannot"
                    + " have same value.");
        }
        this.left = isLeft;
        this.right = !isLeft;
        
    }
    
    /**
     * Creates a direction for an entity based on another entity's direction
     * @param direction existing entity's direction.
     */
    public Direction(Direction direction) {
        this.left = direction.left;
        this.right = !left;
    }

    public boolean isLeft() {
        return left;
    }

    /**
     * If left is sat to true, right is sat to false. left and right boolean 
     * must have different values.
     * @param left 
     */
    public void setLeft(boolean left) {
        if (this.left == this.right) {
            throw new IllegalArgumentException("Left and Right boolean cannot"
                    + " have same value.");
        }
        this.left = left;
        this.right = !left;        
    }

    public boolean isRight() {
        return right;
    }
    
    /**
     * If right is sat to true, left is sat to false. left and right boolean 
     * must have different values.
     * @param right 
     */
    public void setRight(boolean right) {
        if (this.left == this.right) {
            throw new IllegalArgumentException("Left and Right boolean cannot"
                    + " have same value.");
        }
        this.right = right;
        this.left = !right;        
    }
}
