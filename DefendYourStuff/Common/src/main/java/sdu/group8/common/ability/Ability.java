/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.ability;

import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Position;
import java.util.UUID;

/**
 *
 * @author Martin
 */
public class Ability {

    private UUID ID;
    private Position position;
    private float AOE;
    private DamageRange damageRange;
    private boolean isHit = false;
    private boolean isActive = false;

    public Ability(Position position, float AOE, DamageRange damageRange) {
        this.ID = UUID.randomUUID();
        this.position = position;
        this.AOE = AOE;
        this.damageRange = damageRange;
    }

    public String getID() {
        return ID.toString();
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }

    public void setPosition(float x, float y) {
        this.position.setPosition(x, y);
    }

    public float getAOE() {
        return AOE;
    }

    public void setAOE(float AOE) {
        this.AOE = AOE;
    }

    public float getDamage() {
        return damageRange.getDamage();
    }

    public Position getPosition() {
        return this.position;
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
}
