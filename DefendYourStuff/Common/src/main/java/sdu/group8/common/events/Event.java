/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.events;

/**
 *
 * @author Martin
 */
public abstract class Event {
    private float expirationTime;
    private boolean isExpired;
    
    public void reduceExpiration(float dt) {
        this.expirationTime -= dt;
        if(expirationTime < 0) {
            this.isExpired = true;
        }
    }

    public boolean isIsExpired() {
        return isExpired;
    }
}
