
package sdu.group8.common.data;

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
