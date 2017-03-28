/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.map.chunks;

import sdu.group8.common.entity.BlockTypes;
import static sdu.group8.common.entity.BlockTypes.AIR;
import static sdu.group8.common.entity.BlockTypes.CASTLE;
import static sdu.group8.common.entity.BlockTypes.EARTH;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;

/**
 *
 * @author Alexander
 */
public class CastleChunk extends Chunk
{
    public final BlockTypes[][] CASTLE_CHUNK = new BlockTypes[][] {
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
        setChunkMatrix(CASTLE_CHUNK);
    } 
}
