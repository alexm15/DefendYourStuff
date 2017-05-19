
package sdu.group8.commonplayer;

import sdu.group8.common.data.World;

public interface IPlayerService {

    /**
     * gets the player movespeed.
     * 
     * NB: Returns null if there is no player.
     *
     * @return player movespeed.
     */
    float getPlayerMoveSpeed(World world);
}
