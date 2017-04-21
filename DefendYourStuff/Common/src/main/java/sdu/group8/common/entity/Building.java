/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import java.util.ArrayList;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.collision.CollisionContainer;

/**
 *
 * @author Martin
 */
public abstract class Building extends Entity{
    private BuildingType buildingType;
    private boolean isAttackable;
    private AbilityContainer abilities;
    private int upgradeLevel;
    private HealthSystem health;

    public Building(String imageURL, Dimension dimension, Position pos, CollisionType collisionType, BuildingType buildingType, boolean isAttackable, int upgradeLevel, float health, Ability... ab) {
        super(imageURL, dimension, pos, collisionType);
        this.buildingType = buildingType;
        this.isAttackable = isAttackable;
        this.upgradeLevel = upgradeLevel;
        this.health = new HealthSystem(health);
        this.abilities = new AbilityContainer(ab);
    }

    public AbilityContainer getAbilities() {
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
