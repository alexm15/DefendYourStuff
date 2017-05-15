/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.buildingentities;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.IAbilityAction;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.Building;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.abilities.FireballData;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonbuilding.services.IBuildingService;
import sdu.group8.commonplayer.IPlayerAction;

/**
 *
 * @author Alexander
 */
public class Rubble
        extends Building
        implements IBuildingService, IPlayerAction{

    public Rubble(Position pos) {
        super("Building/rubble.png",
                new Dimension(35, 20, 0),
                pos, CollisionType.BOX,
                BuildingType.DEFENCE,
                false, 1, 1, new FireballData());
    }

    @Override
    public void collision(Entity otherEntity) {
        boolean enemyCastedAbility = otherEntity instanceof Ability;
        
        if (otherEntity instanceof IBuildingAction && !enemyCastedAbility) {
            ((IBuildingAction) otherEntity).buildingAction((Entity) this);
        }
    }

    @Override
    public void upgradeBuilding() {
        throw new UnsupportedOperationException("Feature not implemented yet");
    }

    /**
     * Used for updating rubble to a specific building in later implementation
     * @param player the player interacting with the building
     */
    @Override
    public void playerAction(Entity player) {
        //TODO: Add interaction menu for player to perform actions on the building
    }

}
