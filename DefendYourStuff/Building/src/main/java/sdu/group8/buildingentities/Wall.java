/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.ability.IAbilityAction;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonbuilding.services.IDefBuilding;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonplayer.IPlayer;
import sdu.group8.commonplayer.IPlayerAction;

/**
 *
 * @author Alexander
 */
public class Wall extends DefensiveBuilding {

    public Wall(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, BuildingType buildingType,
            boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType, buildingType, isAttackable, upgradeLevel, health, ab);
    }
}
