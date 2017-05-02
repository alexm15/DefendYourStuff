/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.abilities.Fireball;
import sdu.group8.commonbuilding.services.IBuildingService;

/**
 *
 * @author Alexander
 */
public class Rubble
        extends Building
        implements IBuildingService {

    public Rubble(Position pos) {
        super("Building/rubble.png",
                new Dimension(35, 20, 0),
                pos, CollisionType.BOX,
                BuildingType.DEFENCE,
                false, 1, 1, new Fireball());
    }

    @Override
    public void collision(Entity otherEntity) {
    }

    @Override
    public void upgradeBuilding() {
    }

}