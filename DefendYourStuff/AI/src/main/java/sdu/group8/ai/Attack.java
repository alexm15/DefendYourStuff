/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author karim møller
 */
public class Attack {

    public void useAbility(Character enemy, World world, Entity closestTarget, AbilityData abilityData) {
        setDirection(enemy, closestTarget);
        abilityData.useAbility(enemy, world);
    }

    private void setDirection(Character enemy, Entity closestTarget) {
        if (enemy.getX() < closestTarget.getX()) {
            enemy.setDirection(false);
        } else if (enemy.getX() > closestTarget.getX()) {
            enemy.setDirection(true);
        }
    }
    
}
