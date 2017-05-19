
package sdu.group8.common.services;

import sdu.group8.common.data.GameData;

public interface IPreStartPluginService {
    /**
     * This metode is call before the normal start metode and is used for componets that
     * need to setup before the normal components.
     * @param gameData Contains Game data such as window size, Delta Time etc.
     */
    void preStart(GameData gameData);
}
