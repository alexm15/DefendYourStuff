
package sdu.group8.ai;

import java.util.Random;
import sdu.group8.common.data.GameData;
import sdu.group8.common.entity.Entity;
import sdu.group8.commoncharacter.Character;

public class MoveTo {

    public void moveEnemyToTarget(Character character, Entity target, GameData gameData) {
        Random random = new Random();
        float targetX = target.getX();
        float horizontalPos = character.getX();
        if (character.getReactionTimer() == 0) {
            if (random.nextInt(10) == 5) {
                character.resetReactiontime();
            }
            if (character.getX() < targetX) {
                horizontalPos += character.getMoveSpeed() * gameData.getDelta();
                character.setDirection(false);
            } else if (character.getX() > targetX) {
                horizontalPos -= character.getMoveSpeed() * gameData.getDelta();
                character.setDirection(true);
            }
            character.setX(horizontalPos);
        } else {
            character.reduceReactiontime(1);
        }
    }
    
}
