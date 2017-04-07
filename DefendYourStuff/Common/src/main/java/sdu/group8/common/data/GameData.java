/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

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

    private float groundHeight;
    private final float GRAVITY = 9.82f;
    private final GameKeys keys;

    public GameData() {
        this.keys = new GameKeys();
    }

    public float getGroundHeight() {
        return groundHeight;
    }

    public void setGroundHeight(float groundHeight) {
        this.groundHeight = groundHeight;
    }

    public float getGroundPosX() {
        return this.groundHeight;
    }
//    
//    public void addLeftChunk(Chunk chunk) {
//        chunksLeft.add(chunk.getChunkMatrix());
//
//    }
//
//    public void addRigtChunk(Chunk rightBaseChunk) {
//        chunksRight.add(rightBaseChunk.getChunkMatrix());
//    }
//
//    public void addMiddleChunk(Chunk castleChunk) {
//        chunksMiddle.add(castleChunk.getChunkMatrix());
//    }
//
//    public int[] getWindowsY() {
//        return windowsY;
//    }
//
//    public void setWindowsY(int[] windowsY) {
//        this.windowsY = windowsY;
//    }
//
//    public ArrayList<Integer> getWindowsxRight() {
//        return windowsxRight;
//    }
//
//    public void setWindowsxRight(ArrayList<Integer> windowsxRight) {
//        this.windowsxRight = windowsxRight;
//    }
//
//    public ArrayList<Integer> getWindowsxLeft() {
//        return windowsxLeft;
//    }
//
//    public void setWindowsxLeft(ArrayList<Integer> windowsxLeft) {
//        this.windowsxLeft = windowsxLeft;
//    }
//
//    public ArrayList<Integer> getWindowsxMiddle() {
//        return windowsxMiddle;
//    }
//
//    public void setWindowsxMiddle(ArrayList<Integer> windowsxMiddle) {
//        this.windowsxMiddle = windowsxMiddle;
//    }
//
//    
//    public ArrayList<BlockTypes[][]> getChunksMiddle() {
//        return chunksMiddle;
//    }
//
//    public ArrayList<BlockTypes[][]> getChunksLeft() {
//        return chunksLeft;
//    }
//
//    public ArrayList<BlockTypes[][]> getChunksRight() {
//        return chunksRight;
//    }
//
//    public void removeAllChunks() {
//        for (BlockTypes[][] chunk : chunksMiddle) {
//            chunksMiddle.remove(chunk);
//        }
//        for (BlockTypes[][] chunk : chunksRight) {
//            chunksRight.remove(chunk);
//        }
//        for (BlockTypes[][] chunk : chunksLeft) {
//            chunksLeft.remove(chunk);
//        }
//    }

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
