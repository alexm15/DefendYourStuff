/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.collision;

import sdu.group8.common.collision.CollisionEvent;
import java.util.Collection;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.collision.DamageEvent;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;

import sdu.group8.common.entity.Character;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Item;
import sdu.group8.common.entity.Projectile;
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

        Collection<Character> characters = world.getCharacters();
        Collection<Projectile> projectiles = world.getProjectiles();
        Collection<Item> items = world.getItems();

        // Character collision
        for (Character character : characters) {

            // Check character against other characters
            Collection<Character> collidableCharacters = world.getCharacters(character.getCollidableTypes());
            characterCollision(character, collidableCharacters);

            // Check character against items
            characterCollision(character, items);

            // Check character against buildings
            Collection<Building> collidableBuildings = world.getBuildings(character.getCollidableTypes());
            characterCollision(character, collidableBuildings);
        }

        // Projectile collision
        for (Projectile projectile : projectiles) {

            // Check projectile against other projectiles
            Collection<Projectile> collidableProjectiles = world.getProjectiles(projectile.getCollidableTypes());
            projectileCollision(projectile, collidableProjectiles);

            // Check projectile against characters
            Collection<Character> collidableCharacters = world.getCharacters(projectile.getCollidableTypes());
            projectileCollision(projectile, collidableCharacters);

            // Check projectile against buildings
            Collection<Building> collidableBuildings = world.getBuildings(projectile.getCollidableTypes());
            projectileCollision(projectile, collidableBuildings);

        }
    }

    private <E extends Entity> void characterCollision(Character character, Collection<E> collidableEntities) {
        for (E collidableEntity : collidableEntities) {

            // check ability box-circle collision
            handleAbilityCollision(character, collidableEntity);
            handleAbilityCollision(collidableEntity, character);

            // check character-building collision
            if (boxCollision(character.getPosition(), character.getDimension(), collidableEntity.getPosition(), collidableEntity.getDimension())) {
                createCollisionEvents(character, collidableEntity);
            }
        }
    }

    private <E extends Entity> void projectileCollision(Projectile projectile, Collection<E> collidableEntities) {
        for (E collidableEntity : collidableEntities) {

            // check ability box-circle collision
            handleAbilityCollision(projectile, collidableEntity);
            handleAbilityCollision(collidableEntity, projectile);

            // check projectile-entity collision
            if (circleBoxCollision(projectile.getPosition(), projectile.getRadius(), collidableEntity.getPosition(), collidableEntity.getDimension())) {
                createCollisionEvents(projectile, collidableEntity);
            }
        }
    }

    // Wildcard Ability collision with Entity
    private <E extends Entity, V extends Entity> void handleAbilityCollision(E ownerEntity, V collidableEntity) {
        for (Ability ability : ownerEntity.getAbilities()) {
            if (ability.isActive()) {
                // check Ability-Entity collision
                if (circleBoxCollision(ability.getPosition(), ability.getAOE(), collidableEntity.getPosition(), collidableEntity.getDimension())) {
                    ability.setIsHit(true);
                    gameData.addDamageEvent(new DamageEvent(collidableEntity.getID(), collidableEntity.getEntityType(), ability.getDamage()));
                }
            }
        }
    }

    // Wildcard crate collision event
    private <E extends Entity> void createCollisionEvents(E entity, E collidableEntity) {
        gameData.addCollisionEvent(new CollisionEvent(entity.getID(), collidableEntity.getID()));
        gameData.addCollisionEvent(new CollisionEvent(collidableEntity.getID(), entity.getID()));
    }

    private boolean boxCollision(Position posE1, Dimension dimensionE1, Position posE2, Dimension dimensionE2) {

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

    private boolean circleBoxCollision(Position circlePosition, float radius, Position boxPosition, Dimension boxDimension) {

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

    private boolean circleCollision(Position e1, float radiusE1, Position e2, float radiusE2) {
        // Find distance between both entities (hyp)
        float x = e1.getX() - e2.getX();
        float y = e1.getY() - e2.getY();
        float hyp = (float) Math.hypot(x, y);

        // If the distance between both entities (hyp) is less than equal their combined radius, there is a collision.
        return hyp <= radiusE1 + radiusE2;
    }

    // https://jsperf.com/math-clamp/9 - Best performance
    private float clamp(float value, float min, float max) {
        return Math.min(max, Math.max(min, value));
    }
}
