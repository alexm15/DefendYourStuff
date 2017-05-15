package sdu.group8.commonabilitytypes;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonplayer.IPlayer;
import sdu.group8.commonplayer.IPlayerAction;

/**
 *
 * @author joach
 */
public class PositioningAbility extends Ability implements IPlayerAction, IEnemyAction, IBuildingAction {

    public PositioningAbility(float expiration, float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, EffectContainer effectContainer) {
        super(expiration, moveSpeed, weight, damageRange, imageURL, dimension, direction, pos, collisionType, effectContainer);
    }

    public PositioningAbility(Ability ability) {
        super(ability.getExpiration(), ability.getMoveSpeed(), ability.getWeight(), ability.getDamageRange(), ability.getImage().getImageURL(), ability.getDimension(), ability.getDirection(), ability.getPosition(), ability.getCollisionType(), ability.getEffects());
    }
    
     @Override
    public void playerAction(Entity player) {
        setExpiration(0);
    }

    /**
     * Makes ability sure ability only disapears if the ability was casted by
     * another entity than of the enemy class.
     * @param enemy the enemy that the ability has collided with.
     */
    @Override
    public void enemyAction(Entity enemy) {
        if (!(this.getOwner() instanceof Enemy)) {
            setExpiration(0);
        }
    }

    @Override
    public void buildingAction(Entity building) {
        if (!(this.getOwner() instanceof IPlayer)) {
            setExpiration(0);
        }
    }

}
