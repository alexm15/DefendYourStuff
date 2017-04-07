/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonbuilding.services;

import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Building;

/**
 *
 * @author Alexander
 */
public interface Buildable {
    
    /**
     * Creates a castle building.
     * 
     * @param world for adding the created entity to the game world. 
     * @param position buttom center point for buildings placement on the map. 
     * Meaning that buildings dimensions will be positive and negative x coordinates
     * (in relation to this position point)
     * for left and right side of rectangle that defines the building.
     */
    void createCastleBuilding(World world, Position position);
    
    /**
     * Creates a Tower building.
     * 
     * @param world for adding the created entity to the game world. 
     * @param position buttom center point for buildings placement on the map. 
     * Meaning that buildings dimensions will be positive and negative x coordinates
     * (in relation to this position point)
     * for left and right side of rectangle that defines the building.
     */
    void createTowerBuilding(World world, Position position);
    
    /**
     * Creates a Wall building.
     * 
     * @param world for adding the created entity to the game world. 
     * @param position buttom center point for buildings placement on the map. 
     * Meaning that buildings dimensions will be positive and negative x coordinates
     * (in relation to this position point)
     * for left and right side of rectangle that defines the building.
     */
    void createWallBuilding(World world, Position position);
    
    /**
     * Creates a farm building.
     * 
     * @param world for adding the created entity to the game world. 
     * @param position buttom center point for buildings placement on the map. 
     * Meaning that buildings dimensions will be positive and negative x coordinates
     * (in relation to this position point)
     * for left and right side of rectangle that defines the building.
     */
    void createFarmBuilding(World world, Position position);
    
    /**
     * Creates a Blacksmith building.
     * 
     * @param world for adding the created entity to the game world. 
     * @param position buttom center point for buildings placement on the map. 
     * Meaning that buildings dimensions will be positive and negative x coordinates
     * (in relation to this position point)
     * for left and right side of rectangle that defines the building.
     */
    void createBlacksmithBuilding(World world, Position position);
    
    /**
     * Creates a well building.
     * 
     * @param world for adding the created entity to the game world. 
     * @param position buttom center point for buildings placement on the map. 
     * Meaning that buildings dimensions will be positive and negative x coordinates
     * (in relation to this position point)
     * for left and right side of rectangle that defines the building.
     */
    void createWellBuilding(World world, Position position);

}
