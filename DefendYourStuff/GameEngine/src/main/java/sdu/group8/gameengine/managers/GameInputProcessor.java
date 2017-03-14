package sdu.group8.gameengine.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.GameKeys;
import sdu.group8.gameengine.main.Game;

public class GameInputProcessor extends InputAdapter {

    private final GameData gameData;
    
    private Vector3 tp = new Vector3();

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }
    
        public boolean mouseMoved(int screenX, int screenY) {
            
            return true;
        }
        
        public boolean touchDown(int screenX,int screenY, int pointer, int button) {
            if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
                //Game.CAM.unproject(tp.set(screenX, screenY, 0));
                //gameData.getMouse().setMouse(GameMouse.LEFT, true);
            }
            if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
                //gameData.getMouse().setMouse(GameMouse.RIGHT, true);
            }
            return true;
        }
        
        public boolean touchUp(int screenX,int screenY, int pointer, int button) {
            
            return false;
        }
        
	public boolean keyDown(int k) {
		if(k == Keys.UP) {
                    gameData.getKeys().setKey(GameKeys.UP, true);
		}
		if(k == Keys.LEFT) {
                    gameData.getKeys().setKey(GameKeys.LEFT, true);
		}
		if(k == Keys.DOWN) {
                    gameData.getKeys().setKey(GameKeys.DOWN, true);
		}
		if(k == Keys.RIGHT) {
                    gameData.getKeys().setKey(GameKeys.RIGHT, true);
		}
		if(k == Keys.ENTER) {
                    gameData.getKeys().setKey(GameKeys.ENTER, true);
		}
		if(k == Keys.ESCAPE) {
                    gameData.getKeys().setKey(GameKeys.ESCAPE, true);
		}
		if(k == Keys.SPACE) {
                    gameData.getKeys().setKey(GameKeys.SPACE, true);
		}
                if(k == Keys.W) {
                    gameData.getKeys().setKey(GameKeys.W, true);
		}
                if(k == Keys.A) {
                    gameData.getKeys().setKey(GameKeys.A, true);
		}
                if(k == Keys.D) {
                    gameData.getKeys().setKey(GameKeys.D, true);
		}
                if(k == Keys.S) {
                    gameData.getKeys().setKey(GameKeys.S, true);
		}
                if(k == Keys.E) {
                    gameData.getKeys().setKey(GameKeys.E, true);
		}
                if(k == Keys.NUM_1) {
                    gameData.getKeys().setKey(GameKeys.NUM_1, true);
		}
                if(k == Keys.NUM_2) {
                    gameData.getKeys().setKey(GameKeys.NUM_2, true);
		}
                if(k == Keys.NUM_3) {
                    gameData.getKeys().setKey(GameKeys.NUM_3, true);
		}
                if(k == Keys.NUM_4) {
                    gameData.getKeys().setKey(GameKeys.NUM_4, true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.UP) {
                    gameData.getKeys().setKey(GameKeys.UP, false);
		}
		if(k == Keys.LEFT) {
                    gameData.getKeys().setKey(GameKeys.LEFT, false);
		}
		if(k == Keys.DOWN) {
                    gameData.getKeys().setKey(GameKeys.DOWN, false);
		}
		if(k == Keys.RIGHT) {
                    gameData.getKeys().setKey(GameKeys.RIGHT, false);
		}
		if(k == Keys.ENTER) {
                    gameData.getKeys().setKey(GameKeys.ENTER, false);
		}
		if(k == Keys.ESCAPE) {
                    gameData.getKeys().setKey(GameKeys.ESCAPE, false);
		}
		if(k == Keys.SPACE) {
                    gameData.getKeys().setKey(GameKeys.SPACE, false);
		}
		if(k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
                    gameData.getKeys().setKey(GameKeys.SHIFT, false);
		}
                if(k == Keys.W) {
                    gameData.getKeys().setKey(GameKeys.W, false);
		}
                if(k == Keys.A) {
                    gameData.getKeys().setKey(GameKeys.A, false);
		}
                if(k == Keys.D) {
                    gameData.getKeys().setKey(GameKeys.D, false);
		}
                if(k == Keys.S) {
                    gameData.getKeys().setKey(GameKeys.S, false);
		}
                if(k == Keys.E) {
                    gameData.getKeys().setKey(GameKeys.E, false);
		}
                if(k == Keys.NUM_1) {
                    gameData.getKeys().setKey(GameKeys.NUM_1, false);
		}
                if(k == Keys.NUM_2) {
                    gameData.getKeys().setKey(GameKeys.NUM_2, false);
		}
                if(k == Keys.NUM_3) {
                    gameData.getKeys().setKey(GameKeys.NUM_3, false);
		}
                if(k == Keys.NUM_4) {
                    gameData.getKeys().setKey(GameKeys.NUM_4, false);
		}
		return true;
	}
	
}








