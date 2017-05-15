package sdu.group8.buildingentities;

import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.commonbuilding.data.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;

/**
 * This building is used for surrounding the base and blocking out enemies from
 * the players base.
 */
public class Wall extends DefensiveBuilding {

    public Wall(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, BuildingType buildingType,
            boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType, buildingType, isAttackable, upgradeLevel, health, ab);
    }
}
