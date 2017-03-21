/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.collision;

import sdu.group8.common.collision.CollisionEvent;
import java.util.Collection;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;

import sdu.group8.common.entity.EntityType;
import sdu.group8.common.entity.Character;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Item;
import sdu.group8.common.entity.Projectile;
import sdu.group8.common.events.*;
import sdu.group8.common.services.IGamePostProcessingService;

/**
 *
 * @author Martin
 */
public class CollisionProcess implements IGamePostProcessingService {

    // Ability collision with Entity
    private <E extends Entity> void handleAbilityCollision(GameData gameData, E entity, E collidableEntity) {
        for (Ability ability : entity.getAbilities()) {
            if (ability.isActive()) {

                // check Ability-Entity collision
                if (circleBoxCollision(ability.getPosition(), ability.getAOE(), collidableEntity.getPosition(), collidableEntity.getDimension())) {
                    ability.setIsHit(true);
                }

            }
        }
    }

    private <E extends Entity> void createCollisionEvents(GameData gameData, E entity, E collidableEntity) {
        gameData.addCollisionEvent(new CollisionEvent(entity.getID(), entity.getEntityType()));
        gameData.addCollisionEvent(new CollisionEvent(collidableEntity.getID(), collidableEntity.getEntityType()));
    }
    
    @Override
    public void process(GameData gameData, World world) {
        Collection<Character> characters = world.getCharacters();
        Collection<Projectile> projectiles = world.getProjectiles();
        Collection<Item> items = world.getItems();

        // Character collision
        for (Character character : characters) {

            // Check character against other characters
            Collection<Character> collidableCharacters = world.getCharacters(character.getCollidableTypes());
            for (Character collidableCharacter : collidableCharacters) {

                handleAbilityCollision(character, collidableCharacter);

                // check Character-character collision
                if (boxCollision(character.getPosition(), character.getDimension(), collidableCharacter.getPosition(), collidableCharacter.getDimension())) {
                    createCollisionEvents(gameData, character, collidableCharacter);
                }
            }

            // Check character against items
            for (Item item : items) {
                if (boxCollision(character.getPosition(), character.getDimension(), item.getPosition(), item.getDimension())) {
                    createCollisionEvents(gameData, character, item);
                }
            }

            // Check character against buildings
            Collection<Building> collidableBuildings = world.getBuildings(character.getCollidableTypes());
            for (Building collidableBuilding : collidableBuildings) {

                handleAbilityCollision(character, collidableBuilding);

                if (boxCollision(character.getPosition(), character.getDimension(), collidableBuilding.getPosition(), collidableBuilding.getDimension())) {
                    createCollisionEvents(gameData, character, collidableBuilding);
                }
            }
        }

        // Projectile collision
        for (Projectile projectile : projectiles) {

            // Check projectile against other projectiles
            Collection<Projectile> collidableProjectiles = world.getProjectiles(projectile.getCollidableTypes());
            for (Projectile collidableProjectile : collidableProjectiles) {

                // check projectile-projectile collision
                if (circleCollision(projectile.getPosition(), projectile.getRadius(), collidableProjectile.getPosition(), collidableProjectile.getRadius())) {
                    createCollisionEvents(gameData, projectile, collidableProjectile);
                }
            }

            // Check projectile against characters
            Collection<Character> collidableCharacters = world.getCharacters(projectile.getCollidableTypes());
            for (Character collidableCharacter : collidableCharacters) {

                handleAbilityCollision(projectile, collidableCharacter);

                // Check projectile-character collision
                if (circleBoxCollision(projectile.getPosition(), projectile.getRadius(), collidableCharacter.getPosition(), collidableCharacter.getDimension())) {
                   createCollisionEvents(gameData, projectile, collidableCharacter);
                }
            }

            // Check projectile against buildings
            Collection<Building> collidableBuildings = world.getBuildings(projectile.getCollidableTypes());
            for (Building collidableBuilding : collidableBuildings) {

                // check projectile-building collision
                handleAbilityCollision(projectile, collidableBuilding);

                // check projectile-building collision
                if (circleBoxCollision(projectile.getPosition(), projectile.getRadius(), collidableBuilding.getPosition(), collidableBuilding.getDimension())) {
                    createCollisionEvents(gameData, projectile, collidableBuilding);
                }
            }

        }
    }

    private boolean boxCollision(Position posE1, Dimension dimensionE1, Position posE2, Dimension dimensionE2) {

        float combinedX = Math.abs(posE1.getX() - posE2.getX());
        float combinedY = Math.abs(posE1.getY() - posE2.getY());
        float yLength = (dimensionE1.getHeight() / 2) + (dimensionE2.getHeight() / 2);
        float xLength = (dimensionE1.getWidth() / 2) + (dimensionE2.getWidth() / 2);

        // If circle is inside or intersecting with the box dimensions, then there is a collision.
        if (combinedX < xLength) {
            if (combinedY < yLength) {
                return true;
            }
        }

        return false;
    }

    //TODO: Make a more specific(true) box-circle collision
    private boolean circleBoxCollision(Position posE1, float radiusE1, Position posE2, Dimension dimensionE2) {

        float combinedX = Math.abs(posE1.getX() - posE2.getX());
        float combinedY = Math.abs(posE1.getY() - posE2.getY());
        float yLength = radiusE1 + (dimensionE2.getHeight() / 2);
        float xLength = radiusE1 + (dimensionE2.getWidth() / 2);

        // If circle is inside or intersecting with the box dimensions, then there is a collision.
        if (combinedX < xLength) {
            if (combinedY < yLength) {
                return true;
            }
        }

        return false;
    }

    private boolean circleCollision(Position e1, float radiusE1, Position e2, float radiusE2) {
        boolean b = false;

        // hyp = sqrt((x1-x2)^2 + (y1-y2)^2)
        float x = e1.getX() - e2.getX();
        float y = e1.getY() - e2.getY();
        float hyp = (float) Math.hypot(x, y);

        // If the distance between both entities (hyp) is less than their combined radius, there is a collision.
        if ((radiusE1 + radiusE2) > hyp) {
            b = true;
        }

        return b;
    }

}
