/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ability.spellbook;

import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.commonabilitytypes.MeleeAbility;

/**
 *
 * @author Martin
 */
public class Slash extends MeleeAbility {
    
    public Slash() {
        super(0.25f, 0f, 0f, new DamageRange(10,20), "abilities/slash.png", new Dimension(25,50,25), new Direction(true), new Position(0,0), CollisionType.CIRCLE, new EffectContainer());
    }
    
}
