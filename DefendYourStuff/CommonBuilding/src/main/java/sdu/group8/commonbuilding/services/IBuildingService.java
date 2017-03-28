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
    ArrayList<Building> getBuildings(World world);
    Building getBuilding(World world, UUID ID);
    <B extends Building> Class getClass(B building);
    <B extends Building> void createBuilding(B building);
    <B extends Building> void upgradeBuilding(B building);
    ArrayList<? extends Building> getAvailableUpgrades();
    ArrayList<? extends Weapon> getAvailableWeapons();
    <W extends Weapon> void buyWeapon(W weapon);
}
