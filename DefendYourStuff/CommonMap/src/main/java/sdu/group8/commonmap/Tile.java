/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonmap;

import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;

/**
 *
 * @author Martin
 */
public abstract class Tile{
    
    public abstract void createEntity(World world, Position position);
    
}
