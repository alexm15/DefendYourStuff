
package sdu.group8.commonai;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;


public interface AI_Service {
    /**
     * 
     * Is used to give an enemy AI behavior.  
     * 
     * @param enemy the enemy.
     * @param world the world that the enemy exist in.
     * @param gameData gamedata is used for delta time.
     */
    void assignAttackAndDodgeEnemyAI(Character enemy, World world,
            GameData gameData);
    /**
     * 
     * @param enemy the enemy.
     * @param world the world that the enemy exist in.
     * @param gameData gamedata is used for delta time.
     * @param minShootDistance the minmum shooting distance for the enemy to 
     * stand still and shoot.
     * @param maxShootDistance the maximun shooting distance for the enemy to 
     * stand still and shoot.
     */
    void rangedAI(Character enemy, World world, GameData gameData, 
            int minShootDistance, int maxShootDistance);
}
