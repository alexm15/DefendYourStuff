
package sdu.group8.commonbuilding.services;

import sdu.group8.common.entity.Entity;

/**
 * Interface used by other entities to make them perform building specific 
 * actions related to the building they have collided with.
 * @author Alexander
 */
public interface IBuildingAction {
    
    /**
     * 
     * Precondition: Collision have occured between an entity and a building.
     * Building calls buildingAction on that entity.
     * 
     * Postcondition: The other entity has performed it's specific building
     * action.
     * @param building the building collided with 
     */
    void buildingAction(Entity building);
}
