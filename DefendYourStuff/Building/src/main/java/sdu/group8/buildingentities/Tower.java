package sdu.group8.buildingentities;

import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.commonbuilding.data.Building;
import sdu.group8.common.entity.CollisionType;

/**
 * Represents a building used to defending the castle and attacking enemies.
 */
public class Tower extends DefensiveBuilding {

    public Tower(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType, isAttackable, upgradeLevel, health, ab);
    }

}
