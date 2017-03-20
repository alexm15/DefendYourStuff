/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.map.chunks;

import static sdu.group8.map.chunks.BlockTypes.AIR;
import static sdu.group8.map.chunks.BlockTypes.CASTLE;
import static sdu.group8.map.chunks.BlockTypes.EARTH;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;

/**
 *
 * @author Alexander
 */
public class CastleChunk extends Chunk
{
    public BlockTypes[][] castleChunck = new BlockTypes[][] {
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            CASTLE,         AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        
    };

    public CastleChunk(ChunkTypes type) {
        super(type);
    }
    
    public BlockTypes[][] getCastleChunk() {
        return castleChunck;
    }
    
}
