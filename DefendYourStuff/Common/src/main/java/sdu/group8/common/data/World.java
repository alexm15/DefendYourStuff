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
    private ArrayList<Chunk> chunksLeft = new ArrayList<>();
    private Chunk chunkMiddle = null;
    private ArrayList<Chunk> chunksRight = new ArrayList<>();

    // For Entities
    public Collection<Entity> getEntities() {
        return entitíes.values();
    }

    public <E extends Entity> Collection<Entity> getEntitiess(ArrayList<Class> entityTypes) {
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

    public <E extends Entity> Collection<Entity> getEntitie(Class<E>... entityTypes) {
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

    public ArrayList<Chunk> getChunksLeft() {
        return chunksLeft;
    }

    public void addChunkLeft(Chunk chunk) {
        this.chunksLeft.add(chunk);
    }

    public Chunk getChunkMiddle() {
        return chunkMiddle;
    }

    public void setChunksMiddle(Chunk chunkMiddle) {
        this.chunkMiddle = chunkMiddle;
    }

    public ArrayList<Chunk> getChunksRight() {
        return chunksRight;
    }

    public void addChunkRight(Chunk chunk) {
        this.chunksRight.add(chunk);
    }

    public void removeAllChunks() {
        this.chunksLeft.clear();
        this.chunksRight.clear();
        this.chunkMiddle = null;
    }

}
