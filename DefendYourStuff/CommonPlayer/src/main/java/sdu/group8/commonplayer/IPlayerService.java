/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonplayer;

import sdu.group8.common.data.World;

public interface IPlayerService {

    /**
     * gets the player movespeed.
     *
     * @return player movespeed.
     */
    float getPlayerMoveSpeed(World world);
}
