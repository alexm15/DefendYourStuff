package sdu.group8.common.data;

/**
 *
 * Places the entity on a given position in the world map, y should be > 0.
 * @author Group 8
 */
public class Position {

    private float x;
    private float y;

    /**
     * Places the entity on a given position in the world map, y should be > 0.
     * y restriction might be changed later.
     * @param x
     * @param y must be > 0, however if dungeons later on are added below y 
     * value of 0 then this exception might have to be removed in the future
     */
    public Position(float x, float y) {
        if (y < 0) {
            throw new IllegalArgumentException("y cannot be negative");
        }
        this.x = x;
        this.y = y;
        
    }

    /**
     * Places a entity on a position based on an existing entity's position.
     * @param position existing entity's position object.
     */
    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Sets the position of an entity on the world map, no restriction is present
     * here. 
     * @param x the new x position
     * @param y the new y position
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + y;
    }
}
