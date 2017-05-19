package sdu.group8.enemy;

import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.services.IAbilityAction;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonplayer.IPlayerAction;

public class MediumEnemy
        extends Enemy
        implements IPlayerAction, IAbilityAction, IBuildingAction {

    public MediumEnemy(Position position, AbilityData... abilities) {
        super(0, 100, 10, 100, "Enemy/EnemyBow.png", new Dimension(40, 40, 20),
                new Direction(true), position, CollisionType.BOX, abilities);
    }

    @Override
    public void collision(Entity otherEntity) {
        if (otherEntity instanceof IEnemyAction) {
            ((IEnemyAction) otherEntity).enemyAction((Entity) this);
        }
    }

    @Override
    public void playerAction(Entity player) {
        //TODO: Enemy might in the future be pushed back when colliding with enemy.
    }

    @Override
    public void abilityAction(Ability ab) {
        if (!(ab.getOwner() instanceof Enemy)) {
            this.reduceHealth(ab.getDamage());
        }
    }

    @Override
    public void buildingAction(Entity building) {
        //TODO: Enemy might not be able to pass certain buildings such as walls
    }

}
