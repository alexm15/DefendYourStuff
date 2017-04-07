/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abilityTypes;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;

/**
 *
 * @author joach
 */
public class SummoningAbility extends Ability {

    public SummoningAbility(DamageRange damageRange, Dimension dimension, Position pos, CollisionType collisionType, EffectContainer effectContainer, Ability... ab) {
        super(damageRange, dimension, pos, collisionType, effectContainer, ab);
    }
    
}
