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
public class Chunk_Portal01 extends Chunk {

    private Lookup lookup = Lookup.getDefault();

    private Tile air = new Tile_Air();
    private Tile d01 = new Tile_Dirt();

    private final Tile[][] BG_PORTAL01 = new Tile[][]{
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},};

    public Chunk_Portal01(float positionOffset) {
        super(positionOffset);
        setTileMatrix(BG_PORTAL01);
    }

    @Override
    public void createEntities(World world) {

        Position portal = new Position(((getDimension().getWidth() / 2) + this.getPositionOffset()) * TILE_SIZE, TILE_SIZE);

        for (Buildable buildable : lookup.lookupAll(Buildable.class)) {
            //TODO: create portal
        }
    }

    @Override
    public String getFirstBackgroundImageURL() {
        return "Chunks/chunk_portal01_bg01.png";
    }

    @Override
    public String getSecondBackgroundImageURL() {
        return "Chunks/chunk_portal01_bg02.png";
    }

}
