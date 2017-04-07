/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonplayer;

import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;
import sdu.group8.common.weapon.Weapon;

/**
 *
 * @author Alexander
 */
public interface IPlayerService {
    /**
     * Gets the player object. 
     * @param <C> a subtype of Character
     * @param world the collection of entities in which the player object is located.
     * @return the player object
     */
    <C extends Character> C getPlayer(World world);
    /**
     * Gets the class of the player object. 
     * @param <C> a subtype of Character
     * @return the Player subclass of Character.
     */
    <C extends Character> C getPlayerClass();
    /**
     * Changes the players weapon.
     * @param weapon the new weapon.
     */
    void setWeapon(Weapon weapon);
}
