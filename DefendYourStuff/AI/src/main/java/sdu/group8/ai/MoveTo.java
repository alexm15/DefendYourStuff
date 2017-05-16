/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import java.util.Random;
import sdu.group8.common.data.GameData;
import sdu.group8.common.entity.Entity;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author karim møller
 */
public class MoveTo {

    public void moveEnemyToTarget(Character enemy, Entity target, GameData gameData) {
        Random random = new Random();
        float targetX = target.getX();
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
    
}
