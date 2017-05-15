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

/**
 *
 * @author Martin
 */
public class Chunk_Grassland01 extends Chunk {

    private Lookup lookup = Lookup.getDefault();

    private Tile air = new Tile_Air();
    private Tile d01 = new Tile_Dirt();

    public final Tile[][] BG_GRASSLAND01 = new Tile[][]{
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},
        {d01, air, air, air, air, air},};

    public Chunk_Grassland01(float positionOffset) {
        super(new Image("Chunks/chunk_grassland01_bg01.png", false), new Image("defaultBackground.png", false), positionOffset);
        setTileMatrix(BG_GRASSLAND01);
    }

    @Override
    public void createEntities(World world) {

        Position farm = new Position(((getDimension().getWidth() / 4) + this.getPositionOffset()), TILE_SIZE);
        Position rubble = new Position(((getDimension().getWidth() - getDimension().getWidth() / 4) + this.getPositionOffset()), TILE_SIZE);

        Buildable buildable = lookup.lookup(Buildable.class);
        if (buildable != null) {
            buildable.createRubbleBuilding(world, rubble);
            buildable.createFarmBuilding(world, farm);
        }
    }

}
