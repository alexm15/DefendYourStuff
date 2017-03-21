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
public class MapController implements IGamePluginService, IGameProcessingService
{

    @Override
    public void start(GameData gameData, World world) {
        Chunk castleChunk = new CastleChunk(CASTLE_CHUNK);
        Chunk leftBaseChunk = new LeftBaseChunk(LEFT_BASE_CHUNK);
        Chunk rightBaseChunk = new RightBaseChunk(RIGHT_BASE_CHUNK);
        
        world.addChunk(leftBaseChunk);
        world.addChunk(rightBaseChunk);
        world.addChunk(castleChunk);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeAllChunks();
    }

    @Override
    public void process(GameData gameData, World world) {
        
    }

}
