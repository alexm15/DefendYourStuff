/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.commonenemy.Enemy;

/**
 *
 * @author Alexander
 */
public class DestroyedCastle extends DefensiveBuilding {

    public DestroyedCastle(Position pos) {
        super("Building/destroyedcastle.png", new Dimension(200, 100, 0), pos, CollisionType.BOX, BuildingType.DEFENCE, false, 0, 100);
    }
    
    @Override
    public void abilityAction(Ability ab) {
        if ((ab.getOwner() instanceof Enemy)) {
            //A destroyed building should not lose health
            this.reduceHealth(0);
        }
        
    }
    
    

}
