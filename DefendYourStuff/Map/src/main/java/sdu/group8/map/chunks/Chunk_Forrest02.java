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
import sdu.group8.map.tiles.Tile_WoodenFence;

/**
 *
 * @author Martin
 */
public class Chunk_Forrest02 extends Chunk {
    private Lookup lookup = Lookup.getDefault();

    private Tile air = new Tile_Air();
    private Tile d01 = new Tile_Dirt();
    
    public final Tile[][] BG_FORREST02 = new Tile[][] {
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
    };

    public Chunk_Forrest02(int tileOffsetX) {
        super(tileOffsetX);
        setTileMatrix(BG_FORREST02);
    }

    @Override
    public void createEntities(World world) {
        
        Position rubble1 = new Position(((getDimension().getWidth() / 4) + this.getTileOffsetX()) * 100, 100);
        Position rubble2 = new Position(((getDimension().getWidth() - getDimension().getWidth() / 4) + this.getTileOffsetX()) * 100, 100);

        for (Buildable buildable : lookup.lookupAll(Buildable.class)) {
            buildable.createRubbleBuilding(world, rubble1);
            buildable.createRubbleBuilding(world, rubble2);
        }
    }

    @Override
    public String getBackgroundImageURL() {
        return "Chunks/chunk_bg_forrest02.PNG";
    }

}
