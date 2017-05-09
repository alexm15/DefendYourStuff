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
import sdu.group8.map.chunks.Chunk_Forest01;
import sdu.group8.map.chunks.Chunk_Forest02;
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
        addToWorld(world, chunkMiddle, false);
        world.addChunkRight(chunkMiddle);
        world.setChunksMiddle(chunkMiddle);

        int portalOffsetLeft = randomIntRange(5, 7);
        int portalOffsetRight = randomIntRange(5, 7);

        generateChunksUntilPortal(world, chunkMiddle, portalOffsetLeft, true);
        generateChunksUntilPortal(world, chunkMiddle, portalOffsetRight, false);
    }

    /**
     * Generate chunks until a portal should be generated
     * @param world
     * @param lastChunk
     * @param portalOffset
     * @param addToLeftSide 
     */
    private void generateChunksUntilPortal(World world, Chunk startChunk, int portalOffset, boolean addToLeftSide) {
        Chunk lastChunk = startChunk;
        for (int i = 0; i < portalOffset; i++) {
            lastChunk = generateChunk(lastChunk, addToLeftSide);
            addToWorld(world, lastChunk, addToLeftSide);
        }
        addToWorld(world, generatePortalChunk(lastChunk, addToLeftSide), addToLeftSide);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAllChunks();
    }

    @Override
    public void update(World world, boolean addToLeftSide) {
        Chunk lastChunk = getLastChunk(world, addToLeftSide);
        Chunk newChunk = generateChunk(lastChunk, addToLeftSide);
        addToWorld(world, newChunk, addToLeftSide);
    }

    private void addToWorld(World world, Chunk chunk, boolean addToLeftSide) {
        chunk.createEntities(world);
        if (addToLeftSide) {
            world.addChunkLeft(chunk);
        } else {
            world.addChunkRight(chunk);
        }
    }

    private Chunk getLastChunk(World world, boolean addToLeftSide) {
        Chunk lastChunk = null;
        if (addToLeftSide) {
            lastChunk = world.getChunksLeft().get(world.getChunksLeft().size() - 1);
        } else {
            lastChunk = world.getChunksRight().get(world.getChunksRight().size() - 1);
        }
        return lastChunk;

    }

    /**
     *
     * Returns a random chunk from a random biome, such as forrst or grassland.
     *
     * @param lastChunk The last chunk in the world arraylist for chunks.
     * @return
     */
    private Chunk generateChunk(Chunk lastChunk, boolean addToLeftSide) {
        Chunk newChunk;
        float positionOffset = lastChunk.getPositionOffset();

        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = generateGrasslandChunk(positionOffset);
                break;
            case 1:
                newChunk = generateForrestChunk(positionOffset);
                break;
            default:
                newChunk = generateGrasslandChunk(positionOffset);
                break;
        }

        if (addToLeftSide) {
            newChunk.setPositionOffset(positionOffset - newChunk.getDimension().getWidth());
        } else {
            newChunk.setPositionOffset(positionOffset + lastChunk.getDimension().getWidth());
        }

        return newChunk;
    }

    /**
     * Return a random grassland chunk.
     *
     * @param tileOffsetX The number of tiles to offset the new chunk.
     * @return
     */
    private Chunk generateGrasslandChunk(float positionOffset) {
        Chunk newChunk;

        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = new Chunk_Grassland01(positionOffset);
                break;
            case 1:
                newChunk = new Chunk_Grassland02(positionOffset);
                break;
            default:
                newChunk = new Chunk_Grassland01(positionOffset);
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
    Chunk generateForrestChunk(float positionOffset) {
        Chunk newChunk;

        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = new Chunk_Forest01(positionOffset);
                break;
            case 1:
                newChunk = new Chunk_Forest02(positionOffset);
                break;
            default:
                newChunk = new Chunk_Forest01(positionOffset);
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
    Chunk generatePortalChunk(Chunk lastChunk, boolean addToLeftSide) {
        float positionOffset = lastChunk.getPositionOffset();
        Chunk portal = new Chunk_Portal01(positionOffset);
        
        if (addToLeftSide) {
            portal.setPositionOffset(positionOffset - portal.getDimension().getWidth());
        } else {
            portal.setPositionOffset(positionOffset + lastChunk.getDimension().getWidth());
        }
        
        return portal;
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
