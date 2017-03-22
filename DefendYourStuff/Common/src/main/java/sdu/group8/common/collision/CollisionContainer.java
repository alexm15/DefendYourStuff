/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.collision;

import java.util.ArrayList;
import sdu.group8.common.entity.EntityType;

/**
 *
 * @author Martin
 */
public class CollisionContainer {

    private EntityType entityType;
    private ArrayList<EntityType> typesToIgnore;

    public CollisionContainer(EntityType entityType, EntityType... et) {
        this.entityType = entityType;
        this.typesToIgnore = new ArrayList<>();

        for (EntityType eType : et) {
            typesToIgnore.add(eType);
        }
    }

    public ArrayList<EntityType> getTypesToIgnore() {
        return typesToIgnore;
    }

    public void addTypeToIgnore(EntityType et) {
        typesToIgnore.add(et);
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }
    
    public ArrayList<EntityType> getCollidableTypes() {
        ArrayList<EntityType> collidableTypes = new ArrayList<>();
        for(EntityType enumList : EntityType.values()) {
            for(EntityType ignoreList : typesToIgnore) {
                if(enumList != ignoreList) {
                    collidableTypes.add(enumList);
                }
            }
        }
        return collidableTypes;
    }

}
