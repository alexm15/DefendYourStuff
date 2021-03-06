
package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.*;
import java.util.UUID;

public abstract class Entity {

    protected UUID ID;
    protected Dimension dimension;
    protected Position pos;
    protected CollisionType collisionType;
    protected Direction direction;
    protected Image image;

    public Entity(String imageURL, Dimension dimension, Direction direction, Position position, CollisionType collisionType) {
        this.image = new Image(imageURL, direction.isLeft());
        this.ID = UUID.randomUUID();
        this.dimension = new Dimension(dimension);
        this.pos = new Position(position);
        this.collisionType = collisionType;
        this.direction = new Direction(direction);
    }

    public Entity(String imageURL, Dimension dimension, Position position, CollisionType collisionType) {
        this.ID = UUID.randomUUID();
        this.dimension = new Dimension(dimension);
        this.pos = new Position(position);
        this.collisionType = collisionType;
        this.direction = new Direction(true);
        this.image = new Image(imageURL, this.direction.isLeft());
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets which direction the entity is pointing and reversing it's image
     * if nessesary (Left = true), (Right = false). Image's direction should as standard
     * point to the right.
     * @param isLeft
     */
    public void setDirection(boolean isLeft) {
        this.direction.setLeft(isLeft);
        this.getImage().setReversed(isLeft);
    }

    //Add image comment <3
    public void setDirection(Direction direction) {
        this.direction = direction;
        this.getImage().setReversed(direction.isRight());
    }

    public Image getImage() {
        return this.image;
    }

    /**
     * Is used to finde out if the entity is on the ground OR under the ground.
     *
     * @param entity the entity that will be checked.
     * @return true if the enity is on the ground OR under the ground, else
     * false.
     */
    public boolean isEntityOnGround(Entity entity, GameData gameData) {
        if (entity.getPosition().getY() - entity.getHeight() / 2 <= gameData.getGroundHeight()) {
            return true;
        }
        return false;
    }

    /**
     * Sets the player to ground level.
     *
     * @param player
     */
    public void setEntityOnGround(Entity entity, GameData gameData) {
        entity.setPosition(entity.getPosition().getX(), (gameData.getGroundHeight() + entity.getHeight() / 2));
    }

    public UUID getID() {
        return ID;
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }

    public float getWidth() {
        return this.dimension.getWidth();
    }

    public void setWidth(float width) {
        this.dimension.setWidth(width);
    }

    public float getHeight() {
        return this.dimension.getHeight();
    }

    public void setHeight(float height) {
        this.dimension.setHeight(height);
    }

    public void setY(float y) {
        pos.setY(y);
    }

    public void setX(float x) {
        pos.setX(x);
    }

    public float getY() {
        return pos.getY();
    }

    public float getX() {
        return pos.getX();
    }

    public void setPosition(float x, float y) {
        this.pos.setPosition(x, y);
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(float width, float height) {
        this.dimension.setDimension(width, width);
    }

    public void setDimension(Dimension dim) {
        this.dimension = dim;
    }

    public Position getPosition() {
        return pos;
    }

    public abstract void collision(Entity otherEntity);

}
