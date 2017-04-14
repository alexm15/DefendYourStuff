/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.ArrayList;
import sdu.group8.common.entity.BlockTypes;
import sdu.group8.common.entity.Chunk;
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
    private int playerGold;
    private Position cursorPosition;

    private final int GROUND_HEIGHT = 50;
    private final float GRAVITY = 9.82f;
    private final GameKeys keys;
    private final ArrayList<CollisionEvent> collisionEvents = new ArrayList<>();
    private final ArrayList<DamageEvent> damageEvents = new ArrayList<>();
    private ArrayList<BlockTypes[][]> chunksMiddle = new ArrayList<>();
    private ArrayList<BlockTypes[][]> chunksLeft = new ArrayList<>();
    private ArrayList<BlockTypes[][]> chunksRight = new ArrayList<>();
    private int[] windowsY;
    private ArrayList<Integer> windowsxRight = new ArrayList<>();
    private ArrayList<Integer> windowsxLeft = new ArrayList<>();
    private ArrayList<Integer> windowsxMiddle = new ArrayList<>();

    public GameData() {
        this.keys = new GameKeys();
    }

    public void addLeftChunk(Chunk chunk) {
        chunksLeft.add(chunk.getChunkMatrix());

    }

    public void addRigtChunk(Chunk rightBaseChunk) {
        chunksRight.add(rightBaseChunk.getChunkMatrix());
    }

    public void addMiddleChunk(Chunk castleChunk) {
        chunksMiddle.add(castleChunk.getChunkMatrix());
    }

    public int[] getWindowsY() {
        return windowsY;
    }

    public void setWindowsY(int[] windowsY) {
        this.windowsY = windowsY;
    }

    public ArrayList<Integer> getWindowsxRight() {
        return windowsxRight;
    }

    public void setWindowsxRight(ArrayList<Integer> windowsxRight) {
        this.windowsxRight = windowsxRight;
    }

    public ArrayList<Integer> getWindowsxLeft() {
        return windowsxLeft;
    }

    public void setWindowsxLeft(ArrayList<Integer> windowsxLeft) {
        this.windowsxLeft = windowsxLeft;
    }

    public ArrayList<Integer> getWindowsxMiddle() {
        return windowsxMiddle;
    }

    public void setWindowsxMiddle(ArrayList<Integer> windowsxMiddle) {
        this.windowsxMiddle = windowsxMiddle;
    }

    
    public ArrayList<BlockTypes[][]> getChunksMiddle() {
        return chunksMiddle;
    }

    public ArrayList<BlockTypes[][]> getChunksLeft() {
        return chunksLeft;
    }

    public ArrayList<BlockTypes[][]> getChunksRight() {
        return chunksRight;
    }

    public void removeAllChunks() {
        for (BlockTypes[][] chunk : chunksMiddle) {
            chunksMiddle.remove(chunk);
        }
        for (BlockTypes[][] chunk : chunksRight) {
            chunksRight.remove(chunk);
        }
        for (BlockTypes[][] chunk : chunksLeft) {
            chunksLeft.remove(chunk);
        }
    }

    public int getGROUND_HEIGHT() {
        return GROUND_HEIGHT;
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
