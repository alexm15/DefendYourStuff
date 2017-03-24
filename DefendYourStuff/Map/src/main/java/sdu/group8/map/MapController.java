/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map;

import java.util.ArrayList;
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

    @Override
    public void start(GameData gameData, World world) {
        Chunk castleChunk = new CastleChunk(CASTLE_CHUNK);
        Chunk leftBaseChunk = new LeftBaseChunk(LEFT_BASE_CHUNK);
        Chunk rightBaseChunk = new RightBaseChunk(RIGHT_BASE_CHUNK);
        
        gameData.setWindowsxMiddle(new ArrayList<Integer>(8));
        gameData.setWindowsxLeft(new ArrayList<Integer>(8));
        gameData.setWindowsxRight(new ArrayList<Integer>(8));
        
        gameData.setWindowsY(new int[6]);
        for (int i = 1; i <= 6; i++) {
            gameData.getWindowsY()[i - 1] = i * (gameData.getDisplayHeight() / 6);

        }
        
        gameData.addLeftChunk(leftBaseChunk);
        int leftSize = gameData.getWindowsxLeft().size();
        for (int i = gameData.getWindowsxLeft().size()+1; i <= leftSize+8; i++) {
                gameData.getWindowsxLeft().add(i-1, (-i * (gameData.getDisplayWidth() / 8)));
            }
        
        gameData.addRigtChunk(rightBaseChunk);
        int rightSize = gameData.getWindowsxRight().size();
        for (int i = gameData.getWindowsxRight().size()+1; i <= rightSize + 8; i++) {
            gameData.getWindowsxRight().add(i - 1, (i * (gameData.getDisplayWidth() / 8)));
        }
        
        gameData.addMiddleChunk(castleChunk);
        int middleSize = gameData.getWindowsxMiddle().size();
        for (int i = 1; i <= middleSize+8; i++) {
            gameData.getWindowsxMiddle().add(i - 1, (i * (gameData.getDisplayWidth() / 8)));
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
