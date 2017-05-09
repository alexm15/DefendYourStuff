/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Martin
 */
public class DamageRange {
    private float minDamage;
    private float maxDamage;

    public DamageRange(float minDamage, float maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
    
    public float getDamage() {
        return (float) ThreadLocalRandom.current().nextDouble(minDamage, maxDamage);
    }

    public float getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(float minDamage) {
        this.minDamage = minDamage;
    }

    public float getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(float maxDamage) {
        this.maxDamage = maxDamage;
    }
    
    
}
