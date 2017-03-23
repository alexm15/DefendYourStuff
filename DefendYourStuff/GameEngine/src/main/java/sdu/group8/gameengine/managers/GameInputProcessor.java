package sdu.group8.gameengine.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.GameKeys;
import sdu.group8.common.data.Position;
import sdu.group8.gameengine.main.Game;

public class GameInputProcessor extends InputAdapter {

    private final GameData gameData;

    private Vector3 tp = new Vector3();

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Position cursorPosition = new Position(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
        gameData.setCursorPosition(cursorPosition);
        return true;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            gameData.getKeys().setKey(button, true);
        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            gameData.getKeys().setKey(button, false);
        return false;
    }

    //Searches the list of all used keys, and returns true if that key is in that list
    public boolean keyDown(int k) {
        gameData.getKeys().setKey(k, true);
        return true;
    }

    public boolean keyUp(int k) {
        gameData.getKeys().setKey(k, false);
        return true;
    }
}
