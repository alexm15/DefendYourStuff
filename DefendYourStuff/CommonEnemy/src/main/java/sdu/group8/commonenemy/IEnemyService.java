
package sdu.group8.commonenemy;

import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;

public interface IEnemyService {
    void createMediumEnemy(World world, GameData gameData, Position position);
    void createBigEnemy(World world, GameData gameData, Position position);
    void removeAllEnemies(World world);
}
