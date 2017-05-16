/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import sdu.group8.common.data.GameData;
import sdu.group8.common.entity.Entity;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author karim møller
 */
public class MoveAway {

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
