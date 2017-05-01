/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonmap;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;

/**
 * 
 * @author Martin
 */
public interface IMapUpdate {
    /**
     * Updates either the left side or right side of the chunk arrays with a new chunk.
     * @param world An instance of World
     * @param addToLeftSide True if the map should update the left side, false for the right side.
     */
    void update(World world, boolean addToLeftSide);
}
