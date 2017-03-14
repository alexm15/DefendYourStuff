/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import sdu.group8.common.enums.CollisionType;
import sdu.group8.common.enums.QualityType;

/**
 *
 * @author Martin
 */
public class Item extends Entity{
    private float expirationTime;
    private AbilityContainer abilities;
    private QualityType qualityType;

    public Item(float expirationTime, QualityType qualityType, Dimension dimension, Position position, Ability... ab) {
        super(dimension, position);
        this.expirationTime = expirationTime;
        this.abilities = new AbilityContainer(ab);
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

    public ArrayList<Ability> getAbilities() {
        return abilities.getAbilites();
    }

    public QualityType getQualityType() {
        return qualityType;
    }

    public void setQualityType(QualityType qualityType) {
        this.qualityType = qualityType;
    }
    
}
