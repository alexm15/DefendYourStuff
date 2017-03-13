/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Martin
 */
public class World {

    private Map<UUID, MovingEntity> movingEntities;
    private Map<UUID, Building> buildings;
    private Map<UUID, Item> items;

    //For MovingEntities
    public Collection<MovingEntity> getMovingEntities() {
        return movingEntities.values();
    }

    public void addMovingEntity(MovingEntity entity) {
        movingEntities.put(entity.getID(), entity);
    }

    public void removeMovingEntity(String entityID) {
        movingEntities.remove(entityID);
    }

    public void removeMovingEntity(MovingEntity entity) {
        movingEntities.remove(entity.getID());
    }

    //For Buildings
    public Collection<Building> buildings() {
        return buildings.values();
    }

    public void addBuilding(Building entity) {
        buildings.put(entity.getID(), entity);

    }

    public void removeBuilding(String entityID) {
        buildings.remove(entityID);
    }

    public void removeBuilding(Building entity) {
        buildings.remove(entity.getID());
    }

    //For items
    public Collection<Item> items() {
        return items.values();
    }

    public void addItem(Item item) {
        items.put(item.getID(), item);

    }

    public void removeItem(String entityID) {
        items.remove(entityID);
    }

    public void removeItem(Item entity) {
        items.remove(entity.getID());
    }
}
