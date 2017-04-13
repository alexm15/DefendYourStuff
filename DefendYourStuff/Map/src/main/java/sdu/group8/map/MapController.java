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

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IMapUpdate.class),
    @ServiceProvider(service = IGamePluginService.class)}
)
public class MapController implements IGamePluginService, IMapUpdate {

    private int columnsInGrid = 8;
    private int rowsInGrid = 6;

    @Override
    public void start(GameData gameData, World world) {
        Chunk chunkMiddle = new Chunk_Base(0);
        world.setChunksMiddle(chunkMiddle);

        //TODO: generate chunks for left and right side.
        Chunk chunkRight1 = new Chunk_Forrest01(12);
        Chunk chunkRight2 = new Chunk_Forrest02(12 + 12);
        Chunk chunkRight3 = new Chunk_Grassland01(12 + 12 + 12);
        Chunk chunkRight4 = new Chunk_Grassland02(12 + 12 + 12 + 12);
        world.addChunksRight(chunkRight1);
        world.addChunksRight(chunkRight2);
        world.addChunksRight(chunkRight3);
        world.addChunksRight(chunkRight4);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAllChunks();
    }

    @Override
    public void update(World world, boolean addToLeftSide) {
        if (addToLeftSide) {
            world.addChunkLeft(generateChunk(world.getChunksLeft().get(world.getChunksLeft().size())));
        } else {
            world.addChunkLeft(generateChunk(world.getChunksRight().get(world.getChunksRight().size())));
        }
    }

    /**
     * Returns a random chunk from a random biome, such as forrst or grassland.
     * @param lastChunk
     * @return 
     */
    private Chunk generateChunk(Chunk lastChunk) {
        Chunk newChunk;

        // TODO: Make switch for each chunk type (Forrest, Grassland etc.)
        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = generateGrasslandChunk(lastChunk);
                break;
            case 1:
                newChunk = generateForrestChunk(lastChunk);
                break;
            default:
                newChunk = generateGrasslandChunk(lastChunk);
                break;
        }

        return newChunk;
    }
    /**
     * Return a random grassland chunk.
     * @param lastChunk The last chunk in the arraylist from world. Used to set the position of the new chunk's tiles.
     * @return 
     */
    private Chunk generateGrasslandChunk(Chunk lastChunk) {
        Chunk newChunk;
        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = new Chunk_Grassland01((int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth()));
                break;
            case 1:
                newChunk = new Chunk_Grassland02((int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth()));
                break;
            default:
                newChunk = new Chunk_Grassland01((int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth()));
                break;
        }

        return newChunk;
    }

    /**
     * Return a random forrest chunk.
     * @param lastChunk The last chunk in the arraylist from world. Used to set the position of the new chunk's tiles.
     * @return 
     */
    private Chunk generateForrestChunk(Chunk lastChunk) {
        Chunk newChunk;
        switch (randomIntRange(0, 1)) {
            case 0:
                newChunk = new Chunk_Forrest01((int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth()));
                break;
            case 1:
                newChunk = new Chunk_Forrest02((int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth()));
                break;
            default:
                newChunk = new Chunk_Forrest01((int) (lastChunk.getTileOffsetX() + lastChunk.getDimension().getWidth()));
                break;
        }

        return newChunk;
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
