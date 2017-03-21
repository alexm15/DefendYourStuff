/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import sdu.group8.common.collision.CollisionEvent;
import sdu.group8.common.collision.DamageEvent;

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
    private final ArrayList<DamageEvent> damageEvents = new ArrayList<>();
    private int playerGold;
    private Position cursorPosition;
    private final float GRAVITY = 9.82f;

    public GameData() {
        this.keys = new GameKeys();
    }

    public Position getCursorPosition() {
        return cursorPosition;
    }

    public float getGRAVITY() {
        return GRAVITY;
    }
    
    public void setCursorPosition(Position cursorPosition) {
        this.cursorPosition = cursorPosition;
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

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
        expireCollisionEvents();
        expireDamageEvents();
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

    // Colision Event
    public void addCollisionEvent(CollisionEvent e) {
        collisionEvents.add(e);
    }

    public void removeCollisionEvent(CollisionEvent e) {
        collisionEvents.remove(e);
    }

    public ArrayList<CollisionEvent> getCollisionEvents() {
        return collisionEvents;
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

    // DamageEvent
    public void addDamageEvent(DamageEvent e) {
        damageEvents.add(e);
    }

    public void removeDamageEvent(DamageEvent e) {
        damageEvents.remove(e);
    }

    public ArrayList<DamageEvent> getDamageEvents() {
        return damageEvents;
    }

    private void expireDamageEvents() {
        //TODO: update method to use the wildcards generic collection
        for (DamageEvent event : damageEvents) {
            event.reduceExpiration(delta);
            if (event.isIsExpired()) {
                removeDamageEvent(event);
            }
        }
    }
}
