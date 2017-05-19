
package sdu.group8.commonmap;

import sdu.group8.common.data.World;

public interface IMapUpdate {
    /**
     * Updates either the left side or right side of the chunk arrays with a new chunk.
     * @param world An instance of World
     * @param addToLeftSide True if the map should update the left side, false for the right side.
     */
    void update(World world, boolean addToLeftSide);
}
