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
public interface IGamePostProcessingService {
    void process(GameData gameData, World world);
}