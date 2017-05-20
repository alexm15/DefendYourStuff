
package sdu.group8.commoncharacter;

import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityContainer;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.MovingEntity;


public abstract class Character extends MovingEntity {

    protected HealthSystem health;
    protected AbilityContainer abilities;

    public Character(float moveSpeed, float weight, float health, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, AbilityData... ab) {
        super(moveSpeed, weight, imageURL, dimension, direction, pos, collisionType);
        this.health = new HealthSystem(health);
        this.abilities = new AbilityContainer(ab);
    }

    public AbilityContainer getAbilityContainer() {
        return abilities;
    }

    /**
     * Calls the useAbility method in the AbilityContainer.
     *
     * @param abilityContainerIndex The index in the abilitycontainer, to get
     * the actual AbilityData used.
     */
    public void useAbility(int abilityContainerIndex, World world) {
        this.abilities.useAbility(this, abilityContainerIndex, world);
    }

    public AbilityData getAbility(int abilityContainerIndex) {
        return this.abilities.getAbility(abilityContainerIndex);
    }

    protected HealthSystem getHealth() {
        return this.health;
    }

    public float getCurrentHealth() {
        return this.health.getHealth();
    }
    
    public float getMaxHealth() {
        return this.health.getMaxHealth();
    }
    
    public void reduceHealth(float health) {
        this.health.reduceHealth(health);
    }

    public void increaseHealth(float health) {
        this.health.increaseHealth(health);
    }
}
