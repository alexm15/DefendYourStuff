/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.IAbilityAction;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.commonbuilding.data.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonbuilding.services.IDefBuilding;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonplayer.IPlayer;

/**
 *
 * @author Alexander
 */
public class Castle extends Building implements IDefBuilding, IEnemyAction, IAbilityAction{

    public Castle(Position pos) {
        super("Building/castle.png", new Dimension(200, 100, 0), pos, CollisionType.BOX, BuildingType.DEFENCE, true, 0, 100);
    }

    @Override
    public void collision(Entity otherEntity) {
        if (otherEntity instanceof IBuildingAction) {
            ((IBuildingAction) otherEntity).buildingAction((Entity) this);
        }
    }

    @Override
    public void repair(GameData goldCost) {
    }

    @Override
    public void upgradeBuilding() {
    }

    @Override
    public void enemyAction(Entity enemy) {
    }

    @Override
    public void abilityAction(Ability ab) {
        if (!(ab.getOwner() instanceof IPlayer)) {
            this.reduceHealth(ab.getDamage());
            //TODO: Life display needs to be reduced
        }
        
    }
    
    

}
