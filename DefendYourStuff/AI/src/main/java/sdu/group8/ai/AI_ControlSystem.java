package sdu.group8.ai;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commoncharacter.Character;

@ServiceProviders(value = {
    @ServiceProvider(service = AI_Service.class)
    ,
    @ServiceProvider(service = IGamePluginService.class)}
)
public class AI_ControlSystem implements AI_Service, IGamePluginService {

    @Override
    public void assignAttackAndDodgeEnemyAI(Character enemy, World world, GameData gameData) {

        Entity closestTarget = AcquireTarget.getInstance().getClosesTarget(enemy, world);

        if (distanceToEntity(enemy, closestTarget) > closestTarget.getWidth() / 2) {
            MoveTo.getInstance().moveEnemyToTarget(enemy, closestTarget, gameData);
        } else if (!enemy.getAbility(0).isOnCooldown()) {
            try {
                Attack.getInstance().useAbility(enemy, world, closestTarget, enemy.getAbility(0));
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void rangedAI(Character enemy, World world, GameData gameData, int minShootDistance, int maxShootDistance) {
        Entity closestTarget = AcquireTarget.getInstance().getClosesTarget(enemy, world);

        //shooting
        if (withinShootingRange(enemy, closestTarget, minShootDistance, maxShootDistance)) {

            if (!enemy.getAbility(0).isOnCooldown()) {
                try {
                    Attack.getInstance().useAbility(enemy, world, closestTarget, enemy.getAbility(0));
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(e);
                }
            }
        } else {
            if (isTooCloseToTarget(enemy, closestTarget, minShootDistance)) {
                MoveAway.getInstance().increaseDistance(enemy, closestTarget, gameData);
                if (!enemy.getAbility(0).isOnCooldown()) {
                    try {
                        Attack.getInstance().useAbility(enemy, world, closestTarget, enemy.getAbility(0));
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println(e);
                    }
                }
            }
            if (distanceToEntity(enemy, closestTarget) > maxShootDistance) {
                MoveTo.getInstance().moveEnemyToTarget(enemy, closestTarget, gameData);
            }
        }
    }

    private boolean isTooCloseToTarget(Character enemy, Entity closestTarget, int minShootDistance) {
        boolean tooCloseToTarget = distanceToEntity(enemy, closestTarget) < minShootDistance && !closestTarget.equals(enemy);
        return tooCloseToTarget;
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
    }

    @Override
    public void stop(GameData gameData, World world) {
    }

}
