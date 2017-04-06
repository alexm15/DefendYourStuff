/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;


import sdu.group8.common.entity.Building;
import sdu.group8.common.entity.Item;
import sdu.group8.common.entity.Projectile;
import sdu.group8.common.entity.EntityType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import sdu.group8.common.entity.BlockTypes;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;

/**
 *
 * @author Martin
 */
public class World<E extends Entity, P extends Projectile, B extends Building, CHUNK extends Chunk> {

    private Map<UUID, E> entitíes = new ConcurrentHashMap<>();
    private Map<UUID, P> projectiles = new ConcurrentHashMap<>();
    private Map<UUID, B> buildings = new ConcurrentHashMap<>();
    private Map<UUID, Item> items = new ConcurrentHashMap<>();

    //For MovingEntities
      
    
    
    
    
//    

//    public Chunk getChunk(ChunkTypes chunkType) {
//        for (CHUNK chunk : getGameMap()) {
//            
//                if (chunk.getType().equals(chunkType)) {
//                    return chunk;
//                }
//            }
//        
//        return null;
//    }
    // For Entities
    public Collection<E> getEntities() {
        return entitíes.values();
    }

    public Collection<E> getEntity(ArrayList<EntityType> entityTypes) {
        Collection<E> r = new ArrayList<>();
        for (E entity : getEntities()) {
            for (EntityType entityType : entityTypes) {
                if (entity.getEntityType().equals(entityType)) {
                    r.add(entity);
                }
            }
        }

        return r;
    }

    public void addEntity(E entity) {
        entitíes.put(entity.getID(), entity);

    }

    public void removeEntity(UUID entityID) {
        entitíes.remove(entityID);
    }

    public void removeEntity(E entity) {
        entitíes.remove(entity.getID());
    }
  


    //For Buildings
    public Collection<B> getBuildings() {
        return buildings.values();
    }

    public Collection<B> getBuildings(ArrayList<EntityType> entityTypes) {
        Collection<B> r = new ArrayList<>();
        for (B building : getBuildings()) {
            for (EntityType entityType : entityTypes) {
                if (building.getEntityType().equals(entityType)) {
                    r.add(building);
                }
            }
        }

        return r;
    }

    public void addBuilding(B entity) {
        buildings.put(entity.getID(), entity);

    }

    public void removeBuilding(UUID entityID) {
        buildings.remove(entityID);
    }

    public void removeBuilding(B entity) {
        buildings.remove(entity.getID());
    }

    //For items
    public Collection<Item> getItems() {
        return items.values();
    }

    public void addItem(Item item) {
        items.put(item.getID(), item);

    }

    public void removeItem(UUID entityID) {
        items.remove(entityID);
    }

    public void removeItem(Item entity) {
        items.remove(entity.getID());
    }

    //For projectiles
    public Collection<P> getProjectiles() {
        return projectiles.values();
    }

    public Collection<P> getProjectiles(ArrayList<EntityType> entityTypes) {
        Collection<P> r = new ArrayList<>();
        for (P projectile : getProjectiles()) {
            for (EntityType entityType : entityTypes) {
                if (projectile.getEntityType().equals(entityType)) {
                    r.add(projectile);
                }
            }
        }

        return r;
    }
    
    public void addProjectile(P projectile) {
        projectiles.put(projectile.getID(), projectile);

    }

    public void removeProjectile(UUID entityID) {
        projectiles.remove(entityID);
    }

    public void removeProjectile(P entity) {
        items.remove(entity.getID());
    }

    
}
