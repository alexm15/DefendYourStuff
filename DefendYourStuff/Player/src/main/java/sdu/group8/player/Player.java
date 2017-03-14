/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;

import sdu.group8.common.ability.Ability;
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

    public Player(float health, Dimension dimension, Position pos, Ability... ab) {
        super(health, dimension, pos, ab);
    }
    
    public Weapon getWeapon() {
        return weapon;
    }
        
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;        
    }
    
    
    
}