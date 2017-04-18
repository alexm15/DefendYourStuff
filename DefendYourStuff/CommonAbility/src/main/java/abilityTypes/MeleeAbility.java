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
public class MeleeAbility extends Ability {

    public MeleeAbility(float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Position pos, CollisionType collisionType, EffectContainer effectContainer) {
        super(moveSpeed, weight, damageRange, imageURL, dimension, pos, collisionType, effectContainer);
    }
    
}
