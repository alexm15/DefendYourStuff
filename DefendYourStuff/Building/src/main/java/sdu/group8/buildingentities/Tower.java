package sdu.group8.buildingentities;

import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;

/**
 * Represents a building used to defending the castle and attacking enemies.
 */
public class Tower extends DefensiveBuilding {

    public Tower(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, BuildingType buildingType, boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType, buildingType, isAttackable, upgradeLevel, health, ab);
    }

}
