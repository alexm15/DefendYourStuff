/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.services;

import sdu.group8.common.data.GameData;

/**
 *
 * @author joach
 */
public interface IPreStartPluginService {
    /**
     * This metode is call before the normal start metode and is used for componets that
     * need to setup before the normal components.
     * @param gameData Contains Game data such as window size, Delta Time etc.
     */
    void preStart(GameData gameData);
}
