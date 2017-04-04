/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonbuilding.services;

import java.util.ArrayList;
import java.util.UUID;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;
import sdu.group8.common.weapon.Weapon;

/**
 *
 * @author Martin
 */
public interface IBuildingService {
  /**
   * get every instance of buildings
   * @param world
   * @return every thing that extens buliding.
   */
    ArrayList<? extends Building> getBuildings(World world);
    
    /**
     *  
     * @param world instance of World
     * @param ID the ID of the building to return
     * @return an instance of a specific Building
     */
    <B extends Building> B getBuilding(World world, UUID ID);
    
    // TODO: Discuss if 
    <B extends Building> Class<B> getClass(B building);
    
    /**
     * 
     * @param building 
     */
    <B extends Building> void createBuilding(World world, B building);
    
    /**
     * 
     * @param building the specific building that needs to be upgraded.
     */
    <B extends Building> void upgradeBuilding(B building);
    
    /**
     * 
     * @param world instance of World
     * @param ID the ID of the Building to upgrade
     * @return an ArrayList of Buildings a building can upgrade to.
     */
    ArrayList<? extends Building> getAvailableUpgrades(World world, UUID ID);
    
    /**
     * 
     * @return an ArrayList of generated Weapons the caller can use.
     */
    ArrayList<? extends Weapon> getAvailableWeapons();
    
    /**
     * 
     * @param weapon the specific weapon the caller would like to buy.
     */
    <W extends Weapon> void buyWeapon(W weapon);
}
