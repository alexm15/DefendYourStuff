/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;

/**
 *
 * @author Alexander
 */
public abstract class Chunk 
{
    private Position position;
    private Dimension dimension;
    private ChunkTypes type;
    
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Chunk(ChunkTypes type) {
        this.type = type;
    }

    public ChunkTypes getType() {
        return type;
    }
    
    
    
            
}
