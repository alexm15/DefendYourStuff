/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abilityTypes;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Position;

/**
 *
 * @author joach
 */
public class SummoningAbility extends Ability {
    
    public SummoningAbility(Position position, float AOE, DamageRange damageRange) {
        super(position, AOE, damageRange);
    }
    
}
