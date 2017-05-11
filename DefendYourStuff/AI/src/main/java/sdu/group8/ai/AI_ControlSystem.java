/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import java.util.Random;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.services.AbilitySPI;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commoncharacter.Character;
/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = AI_Service.class)}
)
public class AI_ControlSystem implements AI_Service {

    //FIXME: enemy needs to have specific cooldown for specific abilities.
    private AbilityData enemyAbility;

    @Override
    public void assignAttackAndDodgeEnemyAI(Character enemy, World world, GameData gameData) {

        Entity closestTarget = getClosesTarget(enemy, world);
        if (distanceToEntity(enemy, closestTarget) > closestTarget.getWidth() / 2) {
            moveEnemyToTarget(enemy, closestTarget, gameData);
        }

        useAbility(enemy, world, closestTarget, gameData);
    }

    @Override
    public void rangedAI(Character enemy, World world, GameData gameData, int minShootDistance, int maxShootDistance) {

        enemyAbility = enemy.getAbilityContainer().getAbilites().get(0);

        Entity closestTarget = getClosesTarget(enemy, world);

        boolean tooCloseToTarget = distanceToEntity(enemy, closestTarget) < minShootDistance && !closestTarget.equals(enemy);

        //shoot
        if (withinShootingRange(enemy, closestTarget, minShootDistance, maxShootDistance)) { //TODO lav en range

            useAbility(enemy, world, closestTarget, gameData);

        } else {

            if (tooCloseToTarget) {
                increaseDistance(enemy, closestTarget, gameData);
                useAbility(enemy, world, closestTarget, gameData);

            }
            if (distanceToEntity(enemy, closestTarget) > maxShootDistance) {
                moveEnemyToTarget(enemy, closestTarget, gameData);
            }

        }
    }

    private void moveEnemyToTarget(Character enemy, Entity target, GameData gameData) {
        Random random = new Random();

        float targetX = target.getX();
        enemy.setIncombat(false);
        float horizontalPos = enemy.getX();

        if (enemy.getReactionTimer() == 0) {
            if (random.nextInt(10) == 5) {
                enemy.resetReactiontime();
            }

            if (enemy.getX() < targetX) {
                horizontalPos += enemy.getMoveSpeed() * gameData.getDelta();
                enemy.setDirection(false);

            } else if (enemy.getX() > targetX) {
                horizontalPos -= enemy.getMoveSpeed() * gameData.getDelta();
                enemy.setDirection(true);
            }
            enemy.setX(horizontalPos);

        } else {
            enemy.reduceReactiontime(1);
        }

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

    private void useAbility(Character enemy, World world, Entity closestTarget, GameData gameData) {

        if (enemy.isIncombat()) {
            enemy.getAbilityContainer().setCooldownOne(enemy.getAbilityContainer().getCooldownOne() - gameData.getDelta());

            if (enemy.getAbilityContainer().getCooldownOne() <= 0) {

                useABility(enemy, closestTarget, world);
                enemyAbility.setCoolDown(2);
                enemy.getAbilityContainer().setCooldownOne(enemyAbility.getCoolDown());
            }
        } else {
            useABility(enemy, closestTarget, world);
            enemy.setIncombat(true);
        }
    }

    private void useABility(Character enemy, Entity closestTarget, World world) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        setDirection(enemy, closestTarget);
        world.addEntity(abilityProvider.useAbility(enemy, 0, 0, enemy.getAbilityContainer().getAbilites().get(0)));
    }

    private void setDirection(Character enemy, Entity closestTarget) {

        if (enemy.getX() < closestTarget.getX()) {

            enemy.setDirection(false);
        } else if (enemy.getX() > closestTarget.getX()) {

            enemy.setDirection(true);
        }
    }

    private boolean withinShootingRange(Character enemy, Entity closestTarget, int minShootDistance, int maxShootDistance) {

        return distanceToEntity(enemy, closestTarget) > minShootDistance && distanceToEntity(enemy, closestTarget) < maxShootDistance;
    }

}
