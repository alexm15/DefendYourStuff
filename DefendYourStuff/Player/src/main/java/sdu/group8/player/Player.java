/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.CollisionContainer;
import sdu.group8.common.entity.Character;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.weapon.Weapon;

/**
 *
 * @author joach
 */
public class Player extends Character {
    
    private Weapon weapon;
    private float moveSpeed;
    private float weight;
    private Position aimPoint;
    private final float JUMP_FORCE = 350;

    public Player(float moveSpeed, float weight, float health, Dimension dimension, Position pos, CollisionContainer collision, Ability... ab) {
        super(health, dimension, pos, collision, ab);
        this.moveSpeed = moveSpeed;
        this.weight = weight;
    }
    
    public Weapon getWeapon() {
        return weapon;
    }
        
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;        
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Position getAimPoint() {
        return aimPoint;
    }

    public void setAimPoint(Position aimPoint) {
        this.aimPoint = aimPoint;
    }

    public float getWeight() {
        return weight;
    }

    public float getJUMP_FORCE() {
        return JUMP_FORCE;
    }
       
}