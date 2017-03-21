/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.events;

import java.util.HashMap;
import java.util.Map;
import sdu.group8.common.entity.EntityType;

/**
 *
 * @author Martin
 */
public class CollisionEvent extends Event{
    private Map<String, EntityType> colliders;

    public CollisionEvent(String id1, String id2, EntityType type1, EntityType type2) {
        colliders = new HashMap<>();
        colliders.put(id1, type1);
        colliders.put(id2, type2);
    }
    
    public String getID(EntityType type) {
        return colliders.get(type).toString();
    }
    
    public EntityType getType(String ID) {
        return colliders.get(ID);
    }    
   
    public boolean containsType(EntityType type) {
        return colliders.containsValue(type);
    }
   
    public boolean containsID(String ID) {
        return colliders.containsKey(ID);
    }
}
