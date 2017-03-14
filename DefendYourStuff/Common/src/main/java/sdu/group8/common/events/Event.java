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
public class Event {
    private String creatorID;
    private EventType eventType;
    private float expirationTime;
    private boolean isExpired;

    public Event(String ID, EventType eventType) {
        this.creatorID = ID;
        this.eventType = eventType;
        this.expirationTime = 30;
        this.isExpired = false;
    }
    
    public void reduceExpiration(float dt) {
        this.expirationTime -= dt;
        if(expirationTime < 0) {
            this.isExpired = true;
        }
    }

    public boolean isIsExpired() {
        return isExpired;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public EventType getEventType() {
        return eventType;
    }
}
