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
        //TODO: Look at, or change it
//        //Three initial chunks for testing game
//        Chunk castleChunk = new CastleChunk(CASTLE_CHUNK);
//        Chunk leftBaseChunk = new LeftBaseChunk(LEFT_BASE_CHUNK);
//        Chunk rightBaseChunk = new RightBaseChunk(RIGHT_BASE_CHUNK);
//
//        //Sets windowsY array to 6 spaces
//        gameData.setWindowsY(new int[rowsInGrid]);
//        for (int i = 1; i <= rowsInGrid; i++) {
//            gameData.getWindowsY()[i - 1] = i * (gameData.getDisplayHeight() / rowsInGrid);
//
//        }
//
//        addChunkToLeftGameList(gameData, leftBaseChunk);
//
//        //Initializes middle chunk list and middle window list
//        //Nothing is added to these lists after this. 
//        gameData.addMiddleChunk(castleChunk);
//        for (int i = 1; i <= columnsInGrid; i++) {
//            gameData.getWindowsxMiddle().add(i - 1, (i * (gameData.getDisplayWidth() / columnsInGrid)));
//        }
//
//        addChunkRightGameList(gameData, rightBaseChunk);


        Chunk chunkMiddle = new Chunk_Base();
        world.setChunksMiddle(chunkMiddle);

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

    private Chunk generateChunk(Chunk lastChunk) {
        Chunk newChunk;

        // TODO: change into abstract factory for each chunk type (Forrest, Grassland etc.)
        switch (randomIntRange(0, 3)) {
            case 0:
                newChunk = new Chunk_Forrest01();
                break;
            case 1:
                newChunk = new Chunk_Forrest02();
                break;
            case 2:
                newChunk = new Chunk_Grassland01();
                break;
            case 3:
                newChunk = new Chunk_Grassland02();
                break;
            default:
                newChunk = new Chunk_Grassland01();
                break;
        }
        
        return newChunk;
    }

    private int randomIntRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
