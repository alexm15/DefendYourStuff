/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.chunks;

import org.openide.util.Lookup;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.Tile;
import sdu.group8.commonbuilding.services.Buildable;
import sdu.group8.map.tiles.Tile_Air;
import sdu.group8.map.tiles.Tile_Dirt;

/**
 *
 * @author Martin
 */
public class Chunk_Grassland02 extends Chunk {

    private Lookup lookup = Lookup.getDefault();

    private Tile air = new Tile_Air();
    private Tile d01 = new Tile_Dirt();

    private final Tile[][] BG_GRASSLAND02 = new Tile[][]{
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air},
        {d01, air, air, air, air}
    };

    public Chunk_Grassland02(int tileOffsetX) {
        super(tileOffsetX);
        setTileMatrix(BG_GRASSLAND02);
    }

    @Override
    public void createEntities(World world, int tileOffsetX) {

        Position farm = new Position(((BG_GRASSLAND02[0].length / 4) + tileOffsetX) * 100, 100);
        Position rubble = new Position(((BG_GRASSLAND02[0].length / 2) + tileOffsetX) * 100, 100);

        for (Buildable buildable : lookup.lookupAll(Buildable.class)) {
            buildable.createRubbleBuilding(world, rubble);
            buildable.createFarmBuilding(world, farm);
        }
    }

    @Override
    public String getBackgroundImageURL() {
        // TODO: set background castle image
        return "";
    }

}
