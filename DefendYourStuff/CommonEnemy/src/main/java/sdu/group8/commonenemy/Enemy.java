/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonenemy;

import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;

/**
 *
 * @author karim møller
 */
public abstract class Enemy extends sdu.group8.commoncharacter.Character {
    
    private boolean incombat;
    
    public Enemy(float reactionTime,float moveSpeed, float weight, float health, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, AbilityData... ab) {
        super(moveSpeed, weight, health, imageURL, dimension, direction, pos, collisionType, ab);
        this.reactionTime = reactionTime;
        this.reactionTimer = reactionTime;
    }

    public boolean isIncombat() {
        return incombat;
    }

    public void setIncombat(boolean incombat) {
        this.incombat = incombat;
    }
    
    
    
}
