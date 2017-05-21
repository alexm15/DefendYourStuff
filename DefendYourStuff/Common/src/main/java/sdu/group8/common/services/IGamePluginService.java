
package sdu.group8.common.services;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;

public interface IGamePluginService {
    /**
     * The first call when the componet is loaded. 
     * @param gameData Contains Game data such as window size, Delta Time etc.
     * @param world Contains every Entity that is interactable or is draw.
     */
    void start(GameData gameData, World world);
    /**
     * The Last call to he component before it is uninstalled.
     * This should remove all enetitys from the world. 
     * @param gameData Contains Game data such as window size, Delta Time etc.
     * @param world Contains every Entity that is interactable or is draw. 
     * NB: Remove the component entitys from here.
     */
    void stop(GameData gameData, World world);
}
