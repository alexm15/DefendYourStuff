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
    
    //Set Mouse input to true when mouse button is pressed
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            gameData.getKeys().setKey(button, true);
        return true;
    }
    
    //Set mouse input to false when mouse button is relased
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            gameData.getKeys().setKey(button, false);
        return false;
    }

    //Set key to true when pressed
    @Override
    public boolean keyDown(int k) {
        gameData.getKeys().setKey(k, true);
        return true;
    }
    //Set key to false when released
    @Override
    public boolean keyUp(int k) {
        gameData.getKeys().setKey(k, false);
        return true;
    }
}
