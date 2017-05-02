/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.ability;

/**
 *
 * @author joach
 */
public class AbilityData {
    
    private Class<? extends Ability> type;
    private float coolDown;
    private String name;
    private boolean aimable;
    
    public AbilityData(Class<? extends Ability> type, float coolDown, String name, boolean aimable) {
        this.type = type;
        this.coolDown = coolDown;
        this.name = name;
        this.aimable = aimable;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    public Class<? extends Ability> getType() {
        return type;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public String getName() {
        return name;
    }

    public boolean isAimable() {
        return aimable;
    }
    
    
}
