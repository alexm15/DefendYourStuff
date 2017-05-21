/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.chunks;

import org.openide.util.Lookup;
import sdu.group8.common.data.Image;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.Tile;
import sdu.group8.commonbuilding.services.Buildable;
import sdu.group8.map.tiles.Tile_Air;
import sdu.group8.map.tiles.Tile_Dirt;

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
        super(new Image("defaultBackground.png", false), new Image("defaultBackground.png", false), positionOffset);
        setTileMatrix(BG_PORTAL01);
    }

    @Override
    public void createEntities(World world) {
        Position portalPos = new Position(((getDimension().getWidth() / 2) + this.getPositionOffset()), TILE_SIZE);

        lookup.lookup(Buildable.class).createPortalBuilding(world, portalPos);
    }

}
