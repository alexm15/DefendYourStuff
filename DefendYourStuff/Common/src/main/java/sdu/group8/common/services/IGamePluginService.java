/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.services;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;

/**
 *
 * @author Martin
 */
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
