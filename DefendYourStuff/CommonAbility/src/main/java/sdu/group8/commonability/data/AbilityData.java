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

    private AbilityKey key;
    private float maxCooldown;
    private float currentCooldown;
    private String displayName;
    private boolean onCooldown = false;
    private boolean isActive = false;

    public AbilityData(float maxCooldown, String displayName, AbilityKey key) {
        this.maxCooldown = maxCooldown;
        this.currentCooldown = maxCooldown;
        this.displayName = displayName;
        this.key = key;
    }

    public AbilityData(AbilityData abilityData, AbilityKey abilityKey) {
        this.maxCooldown = abilityData.maxCooldown;
        this.currentCooldown = abilityData.maxCooldown;
        this.displayName = abilityData.displayName;
        this.key = abilityKey;
    }

    public AbilityKey getKey() {
        return key;
    }

    /**
     * Sets the max cooldown of the abilityData. It does not update the
     * cooldown.
     *
     * @param cooldown
     */
    public void setMaxCooldown(float cooldown) {
        this.maxCooldown = cooldown;
        if (currentCooldown > cooldown) {
            this.currentCooldown = cooldown;
        }
    }

    /**
     * Reduces the current cooldown of the abilityData if the ability is active.
     * (if a call to useAbility with this abilityData has been made) If the
     * cooldown is less than equal 0, then reset the cooldown.
     *
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
        return displayName;
    }

    /**
     * Returns true if on cooldown, false if not.
     *
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
        if (abilityProvider != null) {
            abilityProvider.useAbility(owner, this, world);
            this.isActive = true;
        }

    }

    public void useAbility(Entity owner, float aimX, float aimY, World world) {
        AbilitySPI abilityProvider = Lookup.getDefault().lookup(AbilitySPI.class);
        abilityProvider.useAbility(owner, this, aimX, aimY, world);
        this.isActive = true;
    }

}
