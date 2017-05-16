/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.commonability.data.Ability;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = AI_Service.class),
@ServiceProvider(service = IGamePluginService.class)}
)
public class AI_ControlSystem implements AI_Service, IGamePluginService {
    
    private Attack attack;
    private AcquireTarget acquireTarget;
    private MoveAway moveAway;
    private MoveTo moveTo;

    @Override
    public void assignAttackAndDodgeEnemyAI(Character enemy, World world, GameData gameData) {

        Entity closestTarget = acquireTarget.getClosesTarget(enemy, world);

        if (distanceToEntity(enemy, closestTarget) > closestTarget.getWidth() / 2) {
            moveTo.moveEnemyToTarget(enemy, closestTarget, gameData);
        } else if (!enemy.getAbility(0).isOnCooldown()) {
            try {
                attack.useAbility(enemy, world, closestTarget, enemy.getAbility(0));
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void rangedAI(Character enemy, World world, GameData gameData, int minShootDistance, int maxShootDistance) {
        Entity closestTarget = acquireTarget.getClosesTarget(enemy, world);
        boolean tooCloseToTarget = distanceToEntity(enemy, closestTarget) < minShootDistance && !closestTarget.equals(enemy);
        
        //shoot
        if (withinShootingRange(enemy, closestTarget, minShootDistance, maxShootDistance)) { //TODO lav en range

            if (!enemy.getAbility(0).isOnCooldown()) {
                try {
                    attack.useAbility(enemy, world, closestTarget, enemy.getAbility(0));
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(e);
                }
            }
        } else {
            if (tooCloseToTarget) {
                moveAway.increaseDistance(enemy, closestTarget, gameData);
                if (!enemy.getAbility(0).isOnCooldown()) {
                    try {
                        attack.useAbility(enemy, world, closestTarget, enemy.getAbility(0));
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println(e);
                    }
                }
            }
            if (distanceToEntity(enemy, closestTarget) > maxShootDistance) {
                moveTo.moveEnemyToTarget(enemy, closestTarget, gameData);
            }
        }
    }



    private float distanceToEntity(Character enemy, Entity closestTarget) {
        return Math.abs(enemy.getX() - closestTarget.getX());
    }

    private boolean withinShootingRange(Character enemy, Entity closestTarget, int minShootDistance, int maxShootDistance) {
        return distanceToEntity(enemy, closestTarget) > minShootDistance
                && distanceToEntity(enemy, closestTarget) < maxShootDistance;
    }

    @Override
    public void start(GameData gameData, World world) {
        attack = new Attack();
        acquireTarget = new AcquireTarget();
        moveAway = new MoveAway();
        moveTo = new MoveTo();
    }

    @Override
    public void stop(GameData gameData, World world) {
        attack = null;
        acquireTarget = null;
        moveAway = null;
        moveTo = null;
    }

}
