/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonitem.data;

import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityContainer;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.QualityType;

/**
 *
 * @author Martin
 */
public class Item extends Entity{
    private float expirationTime;
    private QualityType qualityType;
    private AbilityContainer abilities;

    public Item(float expirationTime, QualityType qualityType, String imageURL, Dimension dimension, Position position, CollisionType collisionType, AbilityData... ab) {
        super(imageURL, dimension, position, collisionType);
        this.expirationTime = expirationTime;
        this.qualityType = qualityType;
        this.abilities = new AbilityContainer(ab);
    }

    public AbilityContainer getAbilityContainer() {
        return abilities;
    }

    public float getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(float expirationTime) {
        this.expirationTime = expirationTime;
    }
    
    public void reduceExpirationTime(float dt) {
        this.expirationTime -= dt;
        if(this.expirationTime < 0) {
            this.expirationTime = 0;
        }
    }

    public QualityType getQualityType() {
        return qualityType;
    }

    public void setQualityType(QualityType qualityType) {
        this.qualityType = qualityType;
    }

    @Override
    public void collision(Entity otherEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
