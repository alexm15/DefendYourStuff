package sdu.group8.buildingentities;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.BuildingType;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.services.IAbilityAction;
import sdu.group8.commonbuilding.services.ICastle;

/**
 * Represents the main defensive building of the game. If the enemies destroys 
 * this building, then the game will end.
 */
public class Castle extends DefensiveBuilding implements IAbilityAction, ICastle {

    public Castle(Position pos) {
        super("Building/castle.png", new Dimension(200, 100, 0), pos, CollisionType.BOX, BuildingType.DEFENCE, true, 0, 10000);
    }

    @Override
    public void repair(GameData goldCost) {
    }

    @Override
    public void upgradeBuilding() {
    }

    @Override
    public void enemyAction(Entity enemy) {
    }

    @Override
    public HealthSystem getHealthSystem() {
        return this.health;
    }
}
