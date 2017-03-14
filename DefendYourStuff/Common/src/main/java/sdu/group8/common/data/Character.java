/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import sdu.group8.common.enums.CollisionType;

/**
 *
 * @author Martin
 */
public class Character extends MovingEntity{
    private AbilityContainer abilites;
    private HealthSystem health;

    public Character(float health, Dimension dimension, Position pos, Ability... ab) {
        super(dimension, pos);
        this.abilites = new AbilityContainer(ab);
        this.health = new HealthSystem(health);
    }
    
    public ArrayList<Ability> getAbilities() {
        return abilites.getAbilites();
    }
    
    public float getHealth() {
        return health.getHealth();
    }
    
    public void reduceHealth(float health) {
        this.health.reduceHealth(health);
    }
    
    public void increaseHealth(float health) {
        this.health.increaseHealth(health);
    }
}
