
package sdu.group8.common.data;

import java.util.concurrent.ThreadLocalRandom;


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
