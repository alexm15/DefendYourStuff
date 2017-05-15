package sdu.group8.common.data;

/**
 * The system used for controlling health of entities.
 * @author Group 8
 */
public class HealthSystem {

    private float health;
    private float maxHealth;

    /**
     * Creates an instance of the HealthSystem for a specific entity
     * @param maxHealth specified the entity's maximum health.
     */
    public HealthSystem(float maxHealth) {
        if (maxHealth < 0) {
            throw new IllegalArgumentException("health cannot be negative value");
        }
        this.health = maxHealth;
        this.maxHealth = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    /**
     * Changes the maximum health available for the entity, health cannot 
     * be negative.
     * @param maxHealth the new maxHealth value.
     */
    public void setMaxHealth(float maxHealth) {
        if (maxHealth < 0) {
            throw new IllegalArgumentException("health cannot be negative value");
        }
        this.maxHealth = maxHealth;
    }

    /**
     * Reduces the health of the entity based on the damage recieved from other
     * entity
     * @param dmgTaken the damage recieved by the other entity. 
     */
    public void reduceHealth(float dmgTaken) {
        if (dmgTaken < 0) {
            throw new IllegalArgumentException("Damage taken cannot be negative");
        }
        this.health -= dmgTaken;
        if(this.health < 0) {
            this.health = 0;
        }
    }
    
    /**
     * Increases the health of the entity, The entity's health cannot be greater
     * than the specified maxHealth value for the entity
     * @param health the value that the entity's health is increased by.
     */
    public void increaseHealth(float health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health recieved cannot be negative");
        }
        this.health += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

}
