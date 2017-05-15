/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonbuilding.services;

import sdu.group8.common.data.GameData;
import sdu.group8.commonweapon.data.Weapon;

/**
 * IShop is used for communication between other entities and buildings implementing
 * the IShop interface. 
 * @author Group 8
 */
public interface IShop extends IBuildingService {
    /**
     * Buys a weapon with chosen abilities for the weapon. 
     * 
     * Precondition: The player is interacting with a building implementing this
     * interface. 
     * 
     * Postcondition: The players weapon object is replaced with the bought weapon.
     * 
     * @param goldCost the cost of the weapon in the shop.
     * @return a weapon with chosen abilities.
     */
    Weapon buyWeapon(GameData goldCost);
    
    /**
     * Unlocks the next weapon in some list of locked weapons with improved
     * stats.
     * 
     * Precondition: Player has collided with an item and the item has called item
     * action on the building containing items to be unlocked
     * 
     * Postcondtion: the next locked item in the shop is unlocked. 
     */
    void unlockWeapon();
    
}
