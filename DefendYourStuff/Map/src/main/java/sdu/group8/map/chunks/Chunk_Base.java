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
import sdu.group8.map.tiles.*;

/**
 *
 * @author Martin
 */
public class Chunk_Base extends Chunk {

    private Lookup lookup = Lookup.getDefault();

    private Tile air = new Tile_Air();
    private Tile d01 = new Tile_Dirt();
    private Tile w01 = new Tile_WoodenFence();

    public final Tile[][] BG_BASE = new Tile[][]{
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air},
        {d01, w01, air, air, air, air}
    };

    public Chunk_Base(int tileOffsetX) {
        super(tileOffsetX);
        setTileMatrix(BG_BASE);
    }

    @Override
    public void createEntities(World world) {

        Position wallLeft = new Position(this.getTileOffsetX() * 100, 100);
        Position wallRight = new Position((getDimension().getWidth() + this.getTileOffsetX()) * 100, 100);
        Position CastleDoor = new Position(((getDimension().getWidth() / 2) + this.getTileOffsetX()) * 100, 100);

        for (Buildable buildable : lookup.lookupAll(Buildable.class)) {
            buildable.createWallBuilding(world, wallLeft);
            buildable.createCastleBuilding(world, CastleDoor);
            buildable.createWallBuilding(world, wallRight);
        }
    }

    @Override
    public String getBackgroundImageURL() {
        return "Chunks/chunk_bg_base.PNG";
    }

}