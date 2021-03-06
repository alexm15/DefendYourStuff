
package sdu.group8.common.services;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;

public interface IGameProcessingService {
    /**
     * process metode where every entity is moved.
     * @param gameData Contains Game data such as window size, Delta Time etc.
     * @param world Contains every Entity that is interactable or is draw.
     */
    void process(GameData gameData, World world);
}
