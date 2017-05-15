/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.spellbook;

import sdu.group8.ability.types.RangedAbility;
import sdu.group8.commonability.data.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;

/**
 *
 * @author Martin
 */
public class Arrow extends RangedAbility {
    
    public Arrow(Entity owner, float x, float y, boolean directionLeft) {
        super(1f, 250f, 5f, new DamageRange(10,20), "abilities/fireball.png", new Dimension(30,30,15), new Direction(directionLeft), new Position(x,y), CollisionType.CIRCLE, new EffectContainer(), owner, true);
    }

    @Override
    public Arrow getNewInstance(Entity owner, float x, float y, boolean directionLeft) {
        return new Arrow(owner, x, y, directionLeft);
    }

}
