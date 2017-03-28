/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.map.chunks;

import sdu.group8.common.entity.BlockTypes;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;
import static sdu.group8.common.entity.BlockTypes.AIR;
import static sdu.group8.common.entity.BlockTypes.EARTH;
import static sdu.group8.common.entity.BlockTypes.WALLTOWER;
import static sdu.group8.common.entity.BlockTypes.WELL;

/**
 *
 * @author Alexander
 */
public class RightBaseChunk extends Chunk
{
    public final BlockTypes[][] RIGHT_BASE_CHUNK = new BlockTypes[][] {
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           WELL,           AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        {EARTH,           AIR,            WALLTOWER,      AIR,            AIR,            AIR},
        {EARTH,           AIR,            AIR,            AIR,            AIR,            AIR},
        
    };

    public RightBaseChunk(ChunkTypes type) {
        super(type);
        setChunkMatrix(RIGHT_BASE_CHUNK);
    }
}
