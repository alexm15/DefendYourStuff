/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.collision;

import java.util.Collection;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import static sdu.group8.common.entity.CollisionType.*;

import sdu.group8.commoncharacter.Character;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePostProcessingService;

/**
 *
 * @author Martin
 */
public class CollisionProcess implements IGamePostProcessingService {

    GameData gameData;

    @Override
    public void process(GameData gameData, World world) {

        this.gameData = gameData;

        // Character collision
        for (Entity entity : world.getEntities()) {

            for (Entity otherEntity : world.getEntities()) {
                if (!entity.getID().equals(otherEntity.getID())) {
                    if (entity.getCollisionType().equals(BOX)) {
                        if (otherEntity.getCollisionType().equals(BOX)) {
                            if (boxCollision(entity, otherEntity)) {
                                entity.collision(otherEntity);
                            }
                        } else if (boxCircleCollision(entity, otherEntity)) {
                            entity.collision(otherEntity);
                        }
                    } else if (otherEntity.getCollisionType().equals(BOX)) {
                        if (boxCircleCollision(otherEntity, entity)) {
                            entity.collision(otherEntity);
                        }
                    } else if (circleCollision(entity, otherEntity)) {
                        entity.collision(otherEntity);
                    }
                }
            }
        }
    }

    private <E extends Entity> void handleCollision(Character character, Collection<E> collidableEntities) {
        
    }

    private boolean boxCollision(Entity entity, Entity otherEntity) {

        Position posE1 = entity.getPosition();
        Position posE2 = otherEntity.getPosition();
        Dimension dimensionE1 = entity.getDimension();
        Dimension dimensionE2 = otherEntity.getDimension();

        float distanceX = Math.abs(posE1.getX() - posE2.getX());
        float distanceY = Math.abs(posE1.getY() - posE2.getY());
        float combinedLengthX = (dimensionE1.getHeight() / 2) + (dimensionE2.getHeight() / 2);
        float combinedLengthY = (dimensionE1.getWidth() / 2) + (dimensionE2.getWidth() / 2);

        // If dimension is inside or intersecting with the other dimensions, then there is a collision.
        if (distanceX < combinedLengthX) {
            if (distanceY < combinedLengthY) {
                return true;
            }
        }

        return false;
    }

    private boolean boxCircleCollision(Entity box, Entity circle) {
        Position circlePosition = circle.getPosition();
        Position boxPosition = box.getPosition();
        Dimension boxDimension = box.getDimension();
        float radius = circle.getDimension().getRadius();

        float boxInnerBoundaryX = boxDimension.getWidth() / 2;
        float boxInnerBoundaryY = boxDimension.getHeight() / 2;

        // get position in world where each side of the rectangle is
        float rectangleLeft = boxPosition.getX() - boxInnerBoundaryX;
        float rectangleRight = boxPosition.getX() + boxInnerBoundaryX;
        float rectangleTop = boxPosition.getY() - boxInnerBoundaryY;
        float rectangleBottom = boxPosition.getY() + boxInnerBoundaryY;

        // get side of the rectangle the circle is nearest
        float closestX = clamp(rectangleLeft, circlePosition.getX(), rectangleRight);
        float closestY = clamp(rectangleTop, circlePosition.getX(), rectangleBottom);

        // if circle is inside of the rectangle
        if (closestX == circlePosition.getX()) {
            if (closestY == circlePosition.getY()) {
                return true;
            }
        }

        // calculate x and y distance from circle and rectangle side
        float distanceX = Math.abs(circlePosition.getX() - closestX);
        float distanceY = Math.abs(circlePosition.getY() - closestY);

        // calculate actual distance between circle and nearst point in rectangle
        float hyp = (float) Math.hypot(distanceX, distanceY);

        // if the distance is less than the circle radius, a collision is true
        return (hyp <= radius);
    }

    private boolean circleCollision(Entity circle, Entity otherCircle) {
        // Find distance between both entities (hyp)
        float x = circle.getX() - otherCircle.getX();
        float y = circle.getY() - otherCircle.getY();
        float hyp = (float) Math.hypot(x, y);

        // If the distance between both entities (hyp) is less than equal their combined radius, there is a collision.
        return hyp <= circle.getDimension().getRadius() + otherCircle.getDimension().getRadius();
    }

    // https://jsperf.com/math-clamp/9 - Best performance
    private float clamp(float value, float min, float max) {
        return Math.min(max, Math.max(min, value));
    }
}
