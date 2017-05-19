
package sdu.group8.commonai;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;


public interface AI_Service {
    void assignAttackAndDodgeEnemyAI(Character enemy, World world, GameData gameData);
    void rangedAI(Character enemy, World world, GameData gameData, int minShootDistance, int maxShootDistance);
}
