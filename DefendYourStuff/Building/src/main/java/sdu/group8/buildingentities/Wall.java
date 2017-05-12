/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.commonbuilding.data.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.IDefBuilding;

/**
 *
 * @author Alexander
 */
public class Wall
        extends Building
        implements IDefBuilding {

    public Wall(String imageURL, Dimension dimension, Position pos, 
            CollisionType collisionType, BuildingType buildingType, 
            boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        
        super(imageURL, dimension, pos, collisionType, 
                buildingType, isAttackable, upgradeLevel, health, ab);
    }

    @Override
    public void collision(Entity otherEntity) {
    }

    @Override
    public void repair(GameData goldCost) {
    }

    @Override
    public void upgradeBuilding() {
    }

}
