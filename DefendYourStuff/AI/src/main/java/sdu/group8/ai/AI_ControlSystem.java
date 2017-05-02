/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
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

    @Override
    public void assignAttackAndDodgeEnemyAI(Character enemy, World world, GameData gameData) {
        Position enemyPos = enemy.getPosition();
        Entity closestTarget = getClosesTarget(enemy, world);
        moveEnemyToTarget(enemy, closestTarget, gameData);
    }

    private void moveEnemyToTarget(Character enemy, Entity target, GameData gameData) {
        float targetX = target.getX();
        float horizontalPos = enemy.getX();

        if (enemy.getX() < targetX) {
            horizontalPos += enemy.getMoveSpeed() * gameData.getDelta();
        }
        else if (enemy.getX() > targetX) {
            horizontalPos -= enemy.getMoveSpeed() * gameData.getDelta();
        }
        enemy.setX(horizontalPos);
    }

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

}
