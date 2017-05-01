/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.ai;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonenemy.IEnemyAction;

/**
 *
 * @author Alexander
 */
public class DummyAttackable extends Entity implements IEnemyAction
{

    public DummyAttackable(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, Ability... ab) {
        super(imageURL, dimension, pos, collisionType, ab);
    }

    @Override
    public void collision(Entity otherEntity) {
    }

    @Override
    public void enemyAction(Entity enemy) {
    }

}
