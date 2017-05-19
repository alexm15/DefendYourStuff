package sdu.group8.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Chunk;

public class World {

    private Map<UUID, Entity> entitíes = new ConcurrentHashMap<>();
    private ArrayList<Chunk> chunksLeft = new ArrayList<>();
    private Chunk chunkMiddle = null;
    private ArrayList<Chunk> chunksRight = new ArrayList<>();
    public Collection<Entity> getEntities() {
        return entitíes.values();
    }

    public <E extends Entity> Collection<Entity> getEntities(Class<E> entityType) {
        Collection<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (entityType.isInstance(e)) {
                r.add(e);
            }
        }
        return r;
    }

    public <E extends Entity> Collection<E> getCastedEntities(Class<E> entityType) {
        Collection<E> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (entityType.isInstance(e)) {
                r.add((E) e);
            }
        }
        return r;
    }

    public <E extends Entity> void removeEntities(Class<E> entityType) {
        for (Entity entity : getEntities()) {
            if (entityType.isInstance(entity)) {
                removeEntity(entity);
            }
        }
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
