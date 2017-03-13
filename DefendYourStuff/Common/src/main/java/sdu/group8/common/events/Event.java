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
    private String creatorId;
    private EventType eventType;

    public Event(String id, EventType eventType) {
        this.creatorId = id;
        this.eventType = eventType;
    }
    
}
