/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.CollisionContainer;

/**
 *
 * @author Martin
 */
public class Item extends Entity{
    private float expirationTime;
    private QualityType qualityType;

    public Item(float expirationTime, QualityType qualityType, Dimension dimension, Position position, CollisionContainer collision) {
        super(dimension, position, collision);
        this.expirationTime = expirationTime;
        this.qualityType = qualityType;
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
    
}
