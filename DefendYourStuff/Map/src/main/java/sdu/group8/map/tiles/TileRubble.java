/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.tiles;

import org.openide.util.Lookup;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.commonbuilding.services.Buildable;
import sdu.group8.commonmap.Tile;

/**
 *
 * @author Martin
 */
public class TileRubble extends Tile {

    private Lookup lookup = Lookup.getDefault();

    @Override
    public void createEntity(World world, Position position) {
        for(Buildable buildable : lookup.lookupAll(Buildable.class)) {
            buildable.createRubbleBuilding(world, position);
        }
    }

}
