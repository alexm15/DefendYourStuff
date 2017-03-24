/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.collision;

import java.util.UUID;
import sdu.group8.common.entity.EntityType;
import sdu.group8.common.events.Event;

/**
 *
 * @author Martin
 */
public class CollisionEvent extends Event{
    private UUID creatorID;
    private UUID colliderID;    

    public CollisionEvent(UUID creatorID, UUID colliderID) {
        this.creatorID = creatorID;
        this.colliderID = colliderID;
    }
    
    public UUID getCreatorID() {
        return this.creatorID;
    }  
    
    public UUID getColliderID() {
        return this.colliderID;
    }  

}
