/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.group8.ai;

import sdu.group8.commonai.AI_Service;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author Alexander
 */
public class AI_ControlSystem implements AI_Service
{

    @Override
    public Character assignAttackAndDodgeEnemyAI(Character enemy) {
        enemy.setX(1);
        return enemy;
    }

}
