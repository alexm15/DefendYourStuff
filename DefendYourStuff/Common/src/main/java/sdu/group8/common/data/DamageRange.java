/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

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
        //TODO: implement random damage range
        throw new NoSuchMethodError();
    }
}
