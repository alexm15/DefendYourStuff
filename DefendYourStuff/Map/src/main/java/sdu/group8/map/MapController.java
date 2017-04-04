/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import static sdu.group8.common.entity.ChunkTypes.*;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.map.chunks.CastleChunk;
import sdu.group8.map.chunks.LeftBaseChunk;
import sdu.group8.map.chunks.RightBaseChunk;

/**
 *
 * @author Alexander
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IGameProcessingService.class)
    , 
    @ServiceProvider(service = IGamePluginService.class)}
)
public class MapController
        implements IGamePluginService, IGameProcessingService {

    private int columnsInGrid = 8;
    private int rowsInGrid = 6;
    
    @Override
    public void start(GameData gameData, World world) {
        //Three initial chunks for testing game
        Chunk castleChunk = new CastleChunk(CASTLE_CHUNK);
        Chunk leftBaseChunk = new LeftBaseChunk(LEFT_BASE_CHUNK);
        Chunk rightBaseChunk = new RightBaseChunk(RIGHT_BASE_CHUNK);
        
        //Sets windowsY array to 6 spaces
        gameData.setWindowsY(new int[rowsInGrid]);
        for (int i = 1; i <= rowsInGrid; i++) {
            gameData.getWindowsY()[i - 1] = i * (gameData.getDisplayHeight() / rowsInGrid);

        }
        
        
        addChunkToLeftGameList(gameData, leftBaseChunk);
        
        //Initializes middle chunk list and middle window list
        //Nothing is added to these lists after this. 
        gameData.addMiddleChunk(castleChunk);
        for (int i = 1; i <= columnsInGrid; i++) {
            gameData.getWindowsxMiddle().add(i - 1, (i * (gameData.getDisplayWidth() / columnsInGrid)));
        }
        
        addChunkRightGameList(gameData, rightBaseChunk);

    }
    /**
     * Adds chunk to the right side of the gameMap
     * @param gameData for retrieving the lists needed
     * @param chunkToAdd the chunk to be added to the game.
     */
    public void addChunkRightGameList(GameData gameData, Chunk chunkToAdd) {
        gameData.addRigtChunk(chunkToAdd);
        int rightSize = gameData.getWindowsxRight().size();
        int middleGridSize = gameData.getWindowsxMiddle().size() * 100;
        for (int i = gameData.getWindowsxRight().size()+1; i <= rightSize + columnsInGrid; i++) {
            gameData.getWindowsxRight().add(i - 1, (i * (gameData.getDisplayWidth() / columnsInGrid)) + (middleGridSize));
        }
    }

    /**
     * Adds chunk to the left side of the gameMap
     * @param gameData for retrieving the lists needed
     * @param chunkToAdd the chunk to be added to the game.
     */
    public void addChunkToLeftGameList(GameData gameData, Chunk chunkToAdd) {
        gameData.addLeftChunk(chunkToAdd);
        int leftSize = gameData.getWindowsxLeft().size();
        for (int i = gameData.getWindowsxLeft().size()+1; i <= leftSize+columnsInGrid; i++) {
            gameData.getWindowsxLeft().add(i-1, (-i * (gameData.getDisplayWidth() / columnsInGrid)));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        gameData.removeAllChunks();
    }

    @Override
    public void process(GameData gameData, World world) {

    }

}