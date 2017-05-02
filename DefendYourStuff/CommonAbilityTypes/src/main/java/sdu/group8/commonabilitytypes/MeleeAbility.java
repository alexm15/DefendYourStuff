package sdu.group8.commonabilitytypes;

import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;

/**
 *
 * @author joach
 */
public class MeleeAbility extends Ability {

    public MeleeAbility(float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, EffectContainer effectContainer) {
        super(moveSpeed, weight, damageRange, imageURL, dimension, direction, pos, collisionType, effectContainer);
    }

    public MeleeAbility(Ability ability) {
        super(ability.getMoveSpeed(), ability.getWeight(), ability.getDamageRange(), ability.getImage().getImageURL(), ability.getDimension(), ability.getDirection(), ability.getPosition(), ability.getCollisionType(), ability.getEffects());
    }
}
