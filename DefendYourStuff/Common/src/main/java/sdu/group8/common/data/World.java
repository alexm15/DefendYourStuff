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
import sdu.group8.common.entity.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Martin
 */
public class World<C extends Character, P extends Projectile, B extends Building> {

    private Map<UUID, C> characters;
    private Map<UUID, P> projectiles;
    private Map<UUID, B> buildings;
    private Map<UUID, Item> items;

    //For MovingEntities
    public Collection<C> getCharacters() {
        return characters.values();
    }

    public Collection<C> getCharacters(ArrayList<EntityType> entityTypes) {
        Collection<C> r = new ArrayList<>();
        for (C character : getCharacters()) {
            for (EntityType entityType : entityTypes) {
                if (character.getEntityType().equals(entityType)) {
                    r.add(character);
                }
            }
        }

        return r;
    }

    public void addCharacters(C entity) {
        characters.put(entity.getID(), entity);
    }

    public void removeCharacters(String entityID) {
        characters.remove(entityID);
    }

    public void removeCharacters(C entity) {
        characters.remove(entity.getID());
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

    public void removeBuilding(String entityID) {
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

    public void removeItem(String entityID) {
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

    public void removeProjectile(String entityID) {
        projectiles.remove(entityID);
    }

    public void removeProjectile(P entity) {
        items.remove(entity.getID());
    }
}
