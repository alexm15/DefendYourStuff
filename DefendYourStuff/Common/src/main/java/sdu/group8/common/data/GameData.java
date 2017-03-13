/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import sdu.group8.common.events.Event;
import java.util.List;
import sdu.group8.common.events.EventType;

/**
 *
 * @author Martin
 */
public class GameData {

    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys;
    private final List<Event> events;
    private int playerGold;

    public GameData() {
        this.keys = new GameKeys();
        this.events = new ArrayList<Event>();
    }

    // TODO: Copy paste methods from asteroids
    public int getPlayerGold() {
        return playerGold;
    }

    public void setPlayerGold(int playerGold) {
        this.playerGold = playerGold;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public void removeEvent(Event e) {
        events.remove(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getEvents(EventType eventType, String id) {
        List<Event> r = new ArrayList<>();

        for (Event event : events) {
            if (event.getEventType().equals(eventType) && event.getCreatorID().equals(id)) {
                r.add(event);
            }
        }
        return r;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public GameKeys getKeys() {
        return keys;
    }
}
