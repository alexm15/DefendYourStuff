/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonenemy;
import java.util.UUID;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Character;
import sdu.group8.common.entity.Entity;
/**
 *
 * @author Alexander
 */
public interface IEnemyService {
    /**
     * Retrieves an enemy object.
     * @param <C> a subtype of Character
     * @param id the id of the enemy
     * @param world the collection of entities to search for the enemy.
     * @return the enemy object
     */
    <C extends Character> C getEnemy(UUID id, World world);
    /**
     * Retrieves the class of the enemy object
     * @param <C> a subtype of Character
     * @return Enemy class that extends Character.
     */
    <C extends Character> C getEnemyClass();
    /**
     * Creates an enemy through another entity in the game.
     * @param <C> a subtype of Character
     * @param creator the entity who creates the enemy.
     * @return the created enemy object. 
     */
    <C extends Character> C createEnemy(Entity creator);
    
}
