
package sdu.group8.common.services;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;

public interface IGamePostProcessingService {
    /**
     * Is call after the normal process metode.
     * should be used by ex collision where the different Entitys is need without changes
     * @param gameData Contains Game data such as window size, Delta Time etc.
     * @param world Contains every Entity that is interactable or is draw. 
     */
    void process(GameData gameData, World world);
}
