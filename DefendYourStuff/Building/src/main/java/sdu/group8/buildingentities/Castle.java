/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.IDefBuilding;

/**
 *
 * @author Alexander
 */
public class Castle
        extends Building
        implements IDefBuilding {

    public Castle(Position pos) {
        super("Building/castle.png", 
                new Dimension(200, 100, 0), 
                pos, CollisionType.BOX, BuildingType.DEFENCE, 
                true, 0, 100, new Ability[1]);

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
