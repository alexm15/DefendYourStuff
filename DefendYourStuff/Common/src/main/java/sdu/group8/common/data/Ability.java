/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.UUID;

/**
 *
 * @author Martin
 */
public class Ability {
    private UUID ID;
    private Position pos;
    private float AOE;
    private DamageRange damageR;

    public Ability(float x, float y, float AOE, float minDamage, float maxDamage) {
        this.ID = UUID.randomUUID();
        this.pos = new Position(x, y);
        this.AOE = AOE;
        this.damageR = new DamageRange(minDamage, maxDamage);
    }

    public UUID getID() {
        return ID;
    }

    public float getX() {
        return pos.getX();
    }

    public float getY() {
        return pos.getY();
    }

    public void setPosition(float x, float y) {
        this.pos.setPosition(x, y);
    }

    public float getAOE() {
        return AOE;
    }

    public void setAOE(float AOE) {
        this.AOE = AOE;
    }

    public float getDamage() {
        return damageR.getDamage();
    }
    
}
