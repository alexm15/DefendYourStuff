/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map;

import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.commonmap.IMapUpdate;
import sdu.group8.map.chunks.Chunk_Base;
import sdu.group8.map.chunks.Chunk_Forrest01;
import sdu.group8.map.chunks.Chunk_Forrest02;
import sdu.group8.map.chunks.Chunk_Grassland01;
import sdu.group8.map.chunks.Chunk_Grassland02;
import sdu.group8.map.chunks.Chunk_Portal01;

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IMapUpdate.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class MapController implements IGamePluginService, IMapUpdate {

    @Override
    public void start(GameData gameData, World world) {
        Chunk chunkMiddle = new Chunk_Base(0);
        world.setChunksMiddle(chunkMiddle);
        
        //Generate chunks on the left side of base, until it a portal is created.
        int leftSidePortal = randomIntRange(7, 10);
        Chunk lastChunkLeftSide = chunkMiddle;

        for (int i = 0; i < leftSidePortal; i++) {
            lastChunkLeftSide = generateChunk(lastChunkLeftSide);
            world.addChunkLeft(lastChunkLeftSide);
        }
        world.addChunkLeft(generatePortalChunk(lastChunkLeftSide.getTileOffsetX()));

        //Generate chunks on the right side of base, until it a portal is created.
        int rightSidePortal = randomIntRange(7, 10);
        Chunk lastChunkRightSide = chunkMiddle;

        for (int i = 0; i < rightSidePortal; i++) {
            lastChunkRightSide = generateChunk(lastChunkRightSide);
            world.addChunkRight(lastChunkRightSide);
        }
        world.addChunkRight(generatePortalChunk(lastChunkRightSide.getTileOffsetX()));

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAllChunks();
    }

    @Override
    public void update(World world, boolean addToLeftSide) {
        if (addToLeftSide) {
            Chunk lastChunk = world.getChunksLeft().get(world.getChunksLeft().size());
            world.addChunkLeft(generateChunk(lastChunk));
        } else {
            Chunk lastChunk = world.getChunksRight().get(world.getChunksRight().size());
            world.addChunkRight(generateChunk(lastChunk));
        }
    }

    /**
     *
     * Returns a random chunk from a random biome, such as forrst or grassland.
     *
     * @param lastChunk The last chunk in the world arraylist for chunks.
     * @return
     */
    private Chunk generateChunk(Chunk lastChunk) {
        Chunk newChunk;
        int tileOffsetX = (int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth());

        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = generateGrasslandChunk(tileOffsetX);
                break;
            case 1:
                newChunk = generateForrestChunk(tileOffsetX);
                break;
            default:
                newChunk = generateGrasslandChunk(tileOffsetX);
                break;
        }

        return newChunk;
    }

    /**
     * Return a random grassland chunk.
     *
     * @param tileOffsetX The number of tiles to offset the new chunk.
     * @return
     */
    private Chunk generateGrasslandChunk(int tileOffsetX) {
        Chunk newChunk;

        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = new Chunk_Grassland01(tileOffsetX);
                break;
            case 1:
                newChunk = new Chunk_Grassland02(tileOffsetX);
                break;
            default:
                newChunk = new Chunk_Grassland01(tileOffsetX);
                break;
        }

        return newChunk;
    }

    /**
     * Return a random forrest chunk.
     *
     * @param tileOffsetX The number of tiles to offset the new chunk.
     * @return
     */
    Chunk generateForrestChunk(int tileOffsetX) {
        Chunk newChunk;
        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = new Chunk_Forrest01(tileOffsetX);
                break;
            case 1:
                newChunk = new Chunk_Forrest02(tileOffsetX);
                break;
            default:
                newChunk = new Chunk_Forrest01(tileOffsetX);
                break;
        }

        return newChunk;
    }

    /**
     * Return a portal chunk.
     *
     * @param tileOffsetX The number of tiles to offset the new chunk.
     * @return
     */
    Chunk generatePortalChunk(int tileOffsetX) {
        return new Chunk_Portal01(tileOffsetX);
    }

    /**
     * Return random integer between min and max, inclusive.
     *
     * @param min integer minimum value
     * @param max integer maximum value
     * @return random int between min and max.
     */
    private int randomIntRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
