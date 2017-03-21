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
public class DamageEvent extends Event{
    private UUID ID;
    private EntityType type;
    private float damage;

    public DamageEvent(UUID ID, EntityType type, float dmg) {
        this.ID = ID;
        this.type = type;
        this.damage = dmg;
    }
    
    public UUID getID() {
        return ID;
    }
    
    public EntityType getType() {
        return type;
    }    
    
    public float getDamage() {
        return damage;
    }

}
