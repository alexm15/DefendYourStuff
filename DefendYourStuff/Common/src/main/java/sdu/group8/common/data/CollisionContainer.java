/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import java.util.List;
import sdu.group8.common.data.CollisionType;
import sdu.group8.common.enums.EntityType;

/**
 *
 * @author Martin
 */
public class CollisionContainer {

    private CollisionType collisionType;
    private EntityType entityType;
    private ArrayList<EntityType> typesToIgnore;

    public CollisionContainer(CollisionType collisionType, EntityType entityType, EntityType... et) {
        this.collisionType = collisionType;
        this.entityType = entityType;
        this.typesToIgnore = new ArrayList<>();

        for (EntityType eType : et) {
            typesToIgnore.add(eType);
        }
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }

    public void setCollisionType(CollisionType collisionType) {
        this.collisionType = collisionType;
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
