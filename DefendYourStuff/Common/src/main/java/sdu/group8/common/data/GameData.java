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
    private final GameKeys keys;
    private final ArrayList<CollisionEvent> collisionEvents = new ArrayList<>();
    private final ArrayList<DamageEvent> damageEvents = new ArrayList<>();
    private int playerGold;
    private Position cursorPosition;
    private final float GRAVITY = 9.82f;
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
        for (int i = 1; i <= 8; i++) {
                windowsxMiddle.set(i-1, (i * (getDisplayWidth() / 8)));
            }
    }
    
    //TODO: Create single method for this with different parameters
//   public void addChunk (BlockTypes[][] chunk) {
//       
//       if(chunk.equals(CASTLE_CHUNK)) {
//            gameData.getChunksMiddle().add(chunk);
//            for (int i = 1; i <= 8; i++) {
//                windowsxMiddle.set(i-1, (i * (gameData.getDisplayWidth() / 8)));
//            }
//       } else if (chunk.equals(LEFT_BASE_CHUNK)) {
//            chunksLeft.add(chunk);
//            for (int i = windowsxRight.size(); i <= windowsxRight.size()+8; i++) {
//                windowsxRight.set(i-1, (i * (gameData.getDisplayWidth() / 8)));
//            }    
//       } else if (chunk.equals(RIGHT_BASE_CHUNK)) {
//            chunksRight.add(chunk);
//            for (int i = windowsxLeft.size(); i <= windowsxLeft.size()+8; i++) {
//                windowsxLeft.set(i-1, (-i * (gameData.getDisplayWidth() / 8)));
//            }
//       }
//   } 
    
    //TODO: Clean up this method.
    public void initGridForBlocks() {
        //windowsX[0-7] == left of screen [-800 ; 0]
        //windowsX[8-16] == screen [0 ; 800]
        //windowsX[17-24] == right of screen [800 ; 1600]
        
//        if(MIDDLE) {
//        for (int i = 1; i <= 8; i++) {
//            windowsxMiddle.set(i-1, (i * (gameData.getDisplayWidth() / 8)));
//        }
//        }
//        if(RIGHT)
//        for (int i = windowsxRight.size(); i <= windowsxRight.size()+8; i++) {
//            windowsxRight.set(i-1, (i * (gameData.getDisplayWidth() / 8)));
//        }
//        if(LEFT) {
//        for (int i = windowsxLeft.size(); i <= windowsxLeft.size()+8; i++) {
//                windowsxLeft.set(i-1, (-i * (gameData.getDisplayWidth() / 8)));
//            }
//        }
        
        windowsY = new int[6];
        for (int i = 1; i <= 6; i++) {
            windowsY[i - 1] = i * (getDisplayHeight() / 6);

        }

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
