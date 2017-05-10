/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonai;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;
import sdu.group8.commonenemy.Enemy;

/**
 *
 * @author Alexander
 */
public interface AI_Service {
    void assignAttackAndDodgeEnemyAI(Enemy enemy, World world, GameData gameData);
    void rangedAI(Enemy enemy, World world, GameData gameData, int minShootDistance, int maxShootDistance);
}
