
package sdu.group8.ai;

import sdu.group8.common.data.GameData;
import sdu.group8.common.entity.Entity;
import sdu.group8.commoncharacter.Character;

public class MoveAway {
    
    private static MoveAway instance;
    
    private MoveAway() {
    }
    
    public static MoveAway getInstance() {
        if (instance == null) {
            return new MoveAway();
        }
        return instance;
    }

    public void increaseDistance(Character character, Entity closestTarget, GameData gameData) {
        float horizontalPos = character.getX();
        if (character.getX() > closestTarget.getX()) {
            horizontalPos += character.getMoveSpeed() * gameData.getDelta();
            character.setDirection(false);
        } else if (character.getX() < closestTarget.getX()) {
            horizontalPos -= character.getMoveSpeed() * gameData.getDelta();
            character.setDirection(true);
        }
        character.setX(horizontalPos);
    }
    
}
