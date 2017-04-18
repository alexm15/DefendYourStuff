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
    private Position playerPosition;

    private float groundHeight = 100;
    private final int TILE_SIZE = 100;
    private final float GRAVITY = 9.82f;
    private final GameKeys keys;

    public GameData() {
        this.keys = new GameKeys();
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
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

    //TODO: Move to world or delete it
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
