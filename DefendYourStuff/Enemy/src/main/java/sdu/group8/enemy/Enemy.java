/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonenemy.IEnemy;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author Martin
 */
public class Enemy extends Character implements IEnemy{
    
    public Enemy(float health, String imageURL, Dimension dimension, Position pos, CollisionType collisionType, Ability... ab) {
        super(health, imageURL, dimension, pos, collisionType, ab);
    }
    
    @Override
    public void collision(Entity otherEntity) {
        
    }
}
