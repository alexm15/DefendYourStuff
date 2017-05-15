package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.ability.IAbilityAction;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonbuilding.services.IBuildingService;
import sdu.group8.commonbuilding.services.IDefBuilding;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonplayer.IPlayerAction;

/**
 * 
 * 
 */
public class DefensiveBuilding extends Building implements IDefBuilding, IEnemyAction, IAbilityAction, IBuildingService, IPlayerAction {

    public DefensiveBuilding(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, BuildingType buildingType, boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType, buildingType, isAttackable, upgradeLevel, health, ab);
    }
     
    @Override
    public void collision(Entity otherEntity) {
        if (otherEntity instanceof IBuildingAction) {
            ((IBuildingAction) otherEntity).buildingAction((Entity) this);
        }
    }
    
    @Override
    public void abilityAction(Ability ab) {
        if ((ab.getOwner() instanceof Enemy)) {
            this.reduceHealth(ab.getDamage());
            //TODO: Life display needs to be reduced
        }
    }

    @Override
    public void repair(GameData goldCost) {
        throw new UnsupportedOperationException("Feature not implemented yet");
    }

    @Override
    public void upgradeBuilding() {
        throw new UnsupportedOperationException("Feature not implemented yet");

    }

    @Override
    public void enemyAction(Entity enemy) {
        //TODO: Might alert player that this building is being attacked.        
    }

    @Override
    public void playerAction(Entity player) {
        //TODO: Add interaction menu for player to perform actions on the building
    }
    
}
