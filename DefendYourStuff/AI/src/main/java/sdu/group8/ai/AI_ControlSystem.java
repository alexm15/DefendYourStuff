/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commoncharacter.Character;
import sdu.group8.commonenemy.IEnemyAction;

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = AI_Service.class)}
)
public class AI_ControlSystem
        implements AI_Service {

    
    private AbilityData enemyAbility;
    
    @Override
    public void assignAttackAndDodgeEnemyAI(Character enemy, World world, GameData gameData) {
        Entity closestTarget = getClosesTarget(enemy, world);
        moveEnemyToTarget(enemy, closestTarget, gameData);
//        useAbility(enemy, world);
    }

    @Override
    public void rangedAI(Character enemy, World world, GameData gameData, int minDistanceToTarget) {
        enemyAbility = enemy.getAbilityContainer().getAbilites().get(0);
        Entity closestTarget = getClosesTarget(enemy, world);
        boolean tooCloseToTarget = distanceToEntity(enemy, closestTarget) < minDistanceToTarget && !closestTarget.equals(enemy);
        //shoot
        
        float distanceToEntity = distanceToEntity(enemy, closestTarget);
        if (minDistanceToTarget-1 < distanceToEntity && distanceToEntity < minDistanceToTarget  ) { //TODO lav en range
            useAbility(enemy, world, closestTarget);
        } else {

            if (tooCloseToTarget) {
                increaseDistance(enemy, closestTarget, gameData);
                useAbility(enemy, world, closestTarget);

            } else {
                moveEnemyToTarget(enemy, closestTarget, gameData);
            }

        }
    }

    private void moveEnemyToTarget(Character enemy, Entity target, GameData gameData) {
        float targetX = target.getX();
        float horizontalPos = enemy.getX();

        if (enemy.getX() < targetX) {
            horizontalPos += enemy.getMoveSpeed() * gameData.getDelta();
            enemy.setDirection(false);
        } else if (enemy.getX() > targetX) {
            horizontalPos -= enemy.getMoveSpeed() * gameData.getDelta();
            enemy.setDirection(true);
        }
        enemy.setX(horizontalPos);
    }

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
    private Entity getClosesTarget(Character enemy, World world) {

        Entity closestTarget = enemy;
        float shortestdist = Float.MAX_VALUE; //FIXME: if you know a better value!
        float enemyX = enemy.getX();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof IEnemyAction) {
                float currentEntityDist = Math.abs(enemyX - entity.getX());
                if (currentEntityDist < shortestdist) {
                    closestTarget = entity;
                    shortestdist = currentEntityDist;
                }
            }

        }
        return closestTarget;
    }

    private float distanceToEntity(Character enemy, Entity closestTarget) {
        return Math.abs(enemy.getX() - closestTarget.getX());
    }

    private void increaseDistance(Character enemy, Entity closestTarget, GameData gameData) {
        float horizontalPos = enemy.getX();

        if (enemy.getX() > closestTarget.getX()) {
            horizontalPos += enemy.getMoveSpeed() * gameData.getDelta();
            enemy.setDirection(false);
        } else if (enemy.getX() < closestTarget.getX()) {
            horizontalPos -= enemy.getMoveSpeed() * gameData.getDelta();
            enemy.setDirection(true);
        }
        enemy.setX(horizontalPos);
    }

    private void useAbility(Character enemy, World world, Entity closestTarget) {
    //cooldown   
    if(enemyAbility.getCoolDown() <= 0){
        
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        setDirection(enemy, closestTarget);
        world.addEntity(abilityProvider.useAbility(enemy, 0, 0, enemy.getAbilityContainer().getAbilites().get(0)));
        enemyAbility.setCoolDown(20);
    } else {
       float cooldown = enemyAbility.getCoolDown();
       cooldown--;
       enemyAbility.setCoolDown(cooldown);
    }
    }
    
    private void setDirection(Character enemy, Entity closestTarget) {
        
        if (enemy.getX() < closestTarget.getX()) {

            enemy.setDirection(false);
        } else if (enemy.getX() > closestTarget.getX()) {

            enemy.setDirection(true);
        }
    }

}
