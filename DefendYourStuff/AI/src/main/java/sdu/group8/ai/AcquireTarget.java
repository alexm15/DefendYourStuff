/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commoncharacter.Character;
import sdu.group8.commonenemy.IEnemyAction;

/**
 *
 * @author karim møller
 */
public class AcquireTarget {

    /**
     * Gets the closes Entity that implements IEnemyAction.
     *
     * NB: if there is no closes entity then it returns it self!
     *
     * @param enemy
     * @param world
     * @return The closes enemy NB: if there is no closes entity then it returns
     * the enemy itself!
     */
    public Entity getClosesTarget(Character enemy, World world) {
        Entity closestTarget = enemy;
        float shortestdist = Float.MAX_VALUE; //FIXME: if you know a better value!
        float enemyX = enemy.getX();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof IEnemyAction && !(entity instanceof Ability)) {
                float currentEntityDist = Math.abs(enemyX - entity.getX());
                if (currentEntityDist < shortestdist) {
                    closestTarget = entity;
                    shortestdist = currentEntityDist;
                }
            }
        }
        return closestTarget;
    }
    
}
