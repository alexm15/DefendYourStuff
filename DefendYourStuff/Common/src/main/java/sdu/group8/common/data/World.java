/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Chunk;

/**
 *
 * @author Martin
 */
public class World {

    private Map<UUID, Entity> entitíes = new ConcurrentHashMap<>();
    private ArrayList<Chunk> chunkLeft = new ArrayList<>();
    private Chunk chunkMiddle = null; //TODO: Set chunk middle to base chunk;
    private ArrayList<Chunk> chunkRight = new ArrayList<>();

    // For Entities
    public Collection<Entity> getEntities() {
        return entitíes.values();
    }

    public <E extends Entity> Collection<Entity> getEntities(Class<E>... entityTypes) {
        Collection<Entity> r = new ArrayList<>();
        for (Entity entity : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(entityType)) {
                    r.add(entity);
                }
            }
        }

        return r;
    }

    public void addEntity(Entity entity) {
        entitíes.put(entity.getID(), entity);

    }

    public void removeEntity(UUID entityID) {
        entitíes.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entitíes.remove(entity.getID());
    }
    
    public Entity getEntity(UUID entityID) {
        return entitíes.get(entityID);
    }

    public ArrayList<Chunk> getChunkLeft() {
        return chunkLeft;
    }

    public void addChunkLeft(Chunk chunk) {
        this.chunkLeft.add(chunk);
    }

    public Chunk getChunkMiddle() {
        return chunkMiddle;
    }

    public void setChunkMiddle(Chunk chunkMiddle) {
        this.chunkMiddle = chunkMiddle;
    }

    public ArrayList<Chunk> getChunkRight() {
        return chunkRight;
    }

    public void addChunkRight(Chunk chunkRight) {
        this.chunkRight.add(chunkRight);
    }
    
    
}
