/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.spellbook;

import sdu.group8.commonability.data.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.Ability;

/**
 *
 * @author Martin
 */
public class Fireball extends Ability {
    
    public Fireball(Entity owner, float x, float y, boolean directionLeft) {
        super(1f, 450f, 0f, new DamageRange(10,20), "abilities/fireball.png", new Dimension(30,30,15), new Direction(directionLeft), new Position(x,y), CollisionType.CIRCLE, new EffectContainer(), owner);
    }
    
}
