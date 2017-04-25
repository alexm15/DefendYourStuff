/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.ai;

import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonai.AI_Service;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author Alexander
 */
public class AI_ControlSystem implements AI_Service
{

    @Override
    public void assignAttackAndDodgeEnemyAI(World world, Character enemy) {
        Position enemyPos = enemy.getPosition();
        for (Entity entity : world.getEntities()) {
            if(entity instanceof IEnemyAction){
                
            }
        }

    }

}
