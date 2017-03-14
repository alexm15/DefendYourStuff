/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

/**
 *
 * @author Martin
 */
public class HealthSystem {

    private float health;
    private float maxHealth;

    public HealthSystem(float maxHealth) {
        this.health = maxHealth;
        this.maxHealth = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void reduceHealth(float dmgTaken) {
        this.health -= dmgTaken;
        if(this.health < 0) {
            this.health = 0;
        }
    }

    public void increaseHealth(float health) {
        this.health += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

}
