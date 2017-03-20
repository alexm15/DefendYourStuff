/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.weapon;

import java.util.ArrayList;
import java.util.UUID;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.weapon.WeaponType;

/**
 *
 * @author Martin
 */
public class Weapon {
    private UUID ID;
    private AbilityContainer abilities;
    private WeaponType weaponType;
    private float damageMultiplier;
    private float rangeMultiplier;

    public Weapon(UUID ID, WeaponType weaponType, float damageMultiplier, float rangeMultiplier, Ability... ab) {
        this.ID = ID;
        this.abilities = new AbilityContainer(ab);
        this.weaponType = weaponType;
        this.damageMultiplier = damageMultiplier;
        this.rangeMultiplier = rangeMultiplier;
    }
    
    public String getID() {
        return ID.toString();
    }

    public ArrayList<Ability> getAbilities() {
        return abilities.getAbilites();
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public float getRangeMultiplier() {
        return rangeMultiplier;
    }

    public void setRangeMultiplier(float rangeMultiplier) {
        this.rangeMultiplier = rangeMultiplier;
    }
    
    
}
