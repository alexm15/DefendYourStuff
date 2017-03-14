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
import sdu.group8.common.data.CollisionType;

/**
 *
 * @author Martin
 */
public abstract class Building extends Entity{
    private BuildingType buildingType;
    private AbilityContainer abilities;
    private boolean isAttackable;
    private int upgradeLevel;
    private HealthSystem health;

    public Building(BuildingType buildingType, boolean isAttackable, int upgradeLevel, float health, Dimension dimension, Position pos,  Ability... ab) {
        super(dimension, pos);
        this.buildingType = buildingType;
        this.abilities = new AbilityContainer(ab);
        this.isAttackable = isAttackable;
        this.upgradeLevel = upgradeLevel;
        this.health = new HealthSystem(health);
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities.getAbilites();
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
