/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.ability;

import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Position;
import java.util.UUID;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.MovingEntity;

/**
 *
 * @author Martin
 */
public class Ability extends MovingEntity{
    
    private DamageRange damageRange;
    private boolean isHit = false;
    private boolean isActive = false;

    public Ability(DamageRange damageRange, Dimension dimension, Position pos, CollisionType collisionType, Ability... ab) {
        super(dimension, pos, collisionType, ab);
        this.damageRange = damageRange;
    }

    public float getDamage() {
        return damageRange.getDamage();
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public boolean isActive(){
        return isActive;
    } 
    
    public void setIsActive(){
        this.isActive = isActive;
    }

    @Override
    public void collision(Entity otherEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
