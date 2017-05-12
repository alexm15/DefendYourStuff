/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonability.data;

import org.openide.util.Lookup;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.services.AbilitySPI;

/**
 *
 * @author joach
 */
public class AbilityData {

    private Class<? extends Ability> type;
    private float maxCooldown;
    private float currentCooldown;
    private String name;
    private boolean aimable;
    private boolean onCooldown = false;
    private boolean isActive = false;

    public AbilityData(Class<? extends Ability> type, float maxCooldown, String name, boolean aimable) {
        this.type = type;
        this.maxCooldown = maxCooldown;
        this.currentCooldown = maxCooldown;
        this.name = name;
        this.aimable = aimable;
    }

    /**
     * Sets the max cooldown of the abilityData. It does not update the cooldown.
     *
     * @param cooldown
     */
    public void setMaxCooldown(float cooldown) {
        this.maxCooldown = cooldown;
        if (currentCooldown > cooldown) {
            this.currentCooldown = cooldown;
        }
    }

    public Class<? extends Ability> getType() {
        return type;
    }

    /**
     * Reduces the current cooldown of the abilityData if the ability is active.
     * (if a call to useAbility with this abilityData has been made)
     * If the cooldown is less than equal 0, then reset the cooldown.
     * @param deltaTime
     */
    public void reduceCooldown(float deltaTime) {
        if (isActive) {
            this.currentCooldown -= deltaTime;
            if (currentCooldown <= 0) {
                this.onCooldown = false;
                this.isActive = false;
                currentCooldown = maxCooldown;
            } else {
                this.onCooldown = true;
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAimable() {
        return aimable;
    }

    /**
     * Returns true if on cooldown, false if not.
     * @return boolean true if on cooldown, false if not.
     */
    public boolean isOnCooldown() {
        return this.onCooldown;
    }

    /**
     * Uses Lookup to get the implementation of AbilitySPI, so an ability can be
     * created/used.
     *
     * @param owner The Entity that owns the abilitycontainer which contains
     * this AbilityData
     */
    public void useAbility(Entity owner, World world) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        abilityProvider.useAbility(owner, this, world);
        this.isActive = true;
    }

}
