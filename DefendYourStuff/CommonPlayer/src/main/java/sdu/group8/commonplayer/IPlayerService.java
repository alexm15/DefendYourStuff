/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonplayer;

import sdu.group8.common.data.World;
import sdu.group8.common.entity.Character;
import sdu.group8.common.weapon.Weapon;

/**
 *
 * @author Alexander
 */
public interface IPlayerService {
    <C extends Character> C getPlayer(World world);
     <C extends Character> C getPlayerClass();
     void setWeapon(Weapon weapon);
}
