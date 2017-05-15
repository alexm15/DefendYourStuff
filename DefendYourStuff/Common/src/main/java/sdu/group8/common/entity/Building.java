/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;

/**
 * Represents the abstract building with the properties that all buildings
 * needs in the game.
 * @author Martin
 */
public abstract class Building extends Entity {

    protected BuildingType buildingType;
    protected boolean isAttackable;
    protected AbilityContainer abilities;
    protected int upgradeLevel;
    protected HealthSystem health;

    /**
     * Position.x should be half of dimension width, but the Position.y should be at the bottom of the dimension
     * @param imageURL
     * @param dimension
     * @param pos Position containing float x and y of the building
     * @param collisionType
     * @param buildingType
     * @param isAttackable
     * @param upgradeLevel cannot be negative value.
     * @param health cannot be negative value.
     * @param ab 
     */
    public Building(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, BuildingType buildingType, boolean isAttackable, int upgradeLevel, float health, AbilityData... ab) {
        super(imageURL, dimension, pos, collisionType);
        if (upgradeLevel < 0) {
            throw new IllegalArgumentException("UpgradeLvl cannot be negative");
        }
        this.buildingType = buildingType;
        this.isAttackable = isAttackable;
        this.upgradeLevel = upgradeLevel;
        this.health = new HealthSystem(health);
        this.abilities = new AbilityContainer(ab);
        this.pos.setY(this.getY() + this.dimension.getHeight() / 2);
        
    }

    public AbilityContainer getAbilityContainer() {
        return abilities;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
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
        if (upgradeLevel < 0) {
            throw new IllegalArgumentException("UpgradeLvl cannot be negative");
        }
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
