package sdu.group8.buildingentities;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.commonbuilding.data.Building;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonbuilding.services.IBuildingService;
import sdu.group8.commonplayer.IPlayerAction;

/**
 * Represents a destroyed building in the game.
 * 
 */
public class Rubble
        extends Building
        implements IBuildingService, IPlayerAction{

    public Rubble(Position pos) {
        super("Building/rubble.png", new Dimension(35, 20, 0), pos, CollisionType.BOX, false, 1, 1);
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
