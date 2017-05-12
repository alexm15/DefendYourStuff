/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.ai;

import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author Alexander
 */
public class DummyAttackable extends Character implements IEnemyAction
{

    public DummyAttackable(float moveSpeed, Dimension dimension, Direction direction, Position pos) {
        super(moveSpeed, 1f, 500, "", dimension, direction, pos, CollisionType.BOX, new AbilityData[0]);
    }






    @Override
    public void collision(Entity otherEntity) {
    }

    @Override
    public void enemyAction(Entity enemy) {
    }

}
