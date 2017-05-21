
package sdu.group8.commonbuilding.services;

import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;

public interface Buildable {

    /**
     * Creates a castle building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createCastleBuilding(World world, Position position);

    /**
     * Creates a castle building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createDestroyedCastleBuilding(World world, Position position);

    /**
     * Creates a Tower building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createTowerBuilding(World world, Position position);

    /**
     * Creates a Wall building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createWallBuilding(World world, Position position);

    /**
     * Creates a farm building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createFarmBuilding(World world, Position position);

    /**
     * Creates a Blacksmith building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createBlacksmithBuilding(World world, Position position);

    /**
     * Creates a well building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createWellBuilding(World world, Position position);

    /**
     * Creates a rubble building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createRubbleBuilding(World world, Position position);

    /**
     * Creates a rubble building.
     *
     * @param world for adding the created entity to the game world.
     * @param position buttom center point for buildings placement on the map.
     * Meaning that buildings dimensions will be positive and negative x
     * coordinates (in relation to this position point) for left and right side
     * of rectangle that defines the building.
     */
    void createPortalBuilding(World world, Position position);
}
