/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import java.util.Collection;
import sdu.group8.common.events.Event;
import java.util.List;
import sdu.group8.common.events.CollisionEvent;

/**
 *
 * @author Martin
 */
public class GameData {

    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys;
    private final ArrayList<CollisionEvent> collisionEvents = new ArrayList<>();
    private int playerGold;

    public GameData() {
        this.keys = new GameKeys();
    }

    public int getPlayerGold() {
        return playerGold;
    }

    public void setPlayerGold(int playerGold) {
        this.playerGold = playerGold;
    }

    public void addPlayerGold(int gold) {
        this.playerGold += gold;
    }

    public void removePlayerGold(int gold) {
        this.playerGold -= gold;
    }

    public void addCollisionEvent(CollisionEvent e) {
        collisionEvents.add(e);
    }

    public void removeCollisionEvent(CollisionEvent e) {
        collisionEvents.remove(e);
    }

    public ArrayList<CollisionEvent> getCollisionEvents() {
        return collisionEvents;
    }
    
    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
        expireCollisionEvents();
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

    private void expireCollisionEvents() {
        //TODO: update method to use the wildcards generic collection
        for (CollisionEvent event : collisionEvents) {
            event.reduceExpiration(delta);
            if (event.isIsExpired()) {
                removeCollisionEvent(event);
            }
        }
    }
}
