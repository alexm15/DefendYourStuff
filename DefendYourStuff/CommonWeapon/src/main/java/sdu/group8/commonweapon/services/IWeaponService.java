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
    ArrayList<? extends Weapon> generateWeapons();
    <W extends Weapon> ArrayList<W> generateWeapons(Class<W> weaponClass);
    <W extends Weapon> Class<W> getClass(W weapon);
}
