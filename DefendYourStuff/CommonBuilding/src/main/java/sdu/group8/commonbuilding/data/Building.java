/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonbuilding.data;

import sdu.group8.commonability.data.AbilityContainer;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;

/**
 *
 * @author Martin
 */
public abstract class Building extends Entity {


    protected boolean isAttackable;
    protected AbilityContainer abilities;
    protected int upgradeLevel;
    protected HealthSystem health;

    /**
     * Position.x should be half of dimension width, but the Position.y should
     * be at the bottom of the dimension
     *
     * @param imageURL
     * @param dimension
     * @param pos Position containing float x and y of the building
     * @param collisionType
     * @param isAttackable
     * @param upgradeLevel
     * @param health
     * @param ab
     * @param upgradeLevel cannot be negative value.
     * @param health cannot be negative value.
     */
    public Building(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType);
        if (upgradeLevel < 0) {
            throw new IllegalArgumentException("UpgradeLvl cannot be negative");
        }

        this.isAttackable = isAttackable;
        this.upgradeLevel = upgradeLevel;
        this.health = new HealthSystem(health);
        this.abilities = new AbilityContainer(ab);
        this.pos.setY(this.getY() + this.dimension.getHeight() / 2);
    }

    public AbilityContainer getAbilityContainer() {
        return abilities;
    }

    public boolean isAttackable() {
        return isAttackable;
    }

    public void setIsAttackable(boolean isAttackable) {
        this.isAttackable = isAttackable;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public float getHealth() {
        return health.getHealth();
    }

    public void increaseHealth(float health) {
        this.health.increaseHealth(health);
    }

    public void reduceHealth(float dmg) {
        this.health.reduceHealth(dmg);
    }
}
