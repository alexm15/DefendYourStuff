/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonweapon.services;

import java.util.ArrayList;
import sdu.group8.common.weapon.Weapon;

/**
 *
 * @author Martin
 */
public interface IWeaponService {
    
    /**
     * Generates every available weapon that ca be bought 
     * @return  Returns every available weapon in the game that can be bought. 
     */
    ArrayList<? extends Weapon> generateWeapons();
    
    /**
     * Generates weapons from a specific class  
     * @param <W>
     * @param weaponClass
     * @return returns every type of the specific weapon
     */
    <W extends Weapon> ArrayList<W> generateWeapons(Class<W> weaponClass);
    
    /**
     *  returns the class of weapon 
     * @param <W>
     * @param weapon
     * @return The class weapon
     */
    <W extends Weapon> Class<W> getClass(W weapon);
}
