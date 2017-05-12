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
public class Slash extends Ability {
    
    public Slash(Entity owner, float x, float y, boolean directionLeft) {
        super(0.25f, 0f, 0f, new DamageRange(10,20), "abilities/slash.png", new Dimension(25,50,25), new Direction(directionLeft), new Position(x,y), CollisionType.CIRCLE, new EffectContainer(), owner);
    }
    
}
