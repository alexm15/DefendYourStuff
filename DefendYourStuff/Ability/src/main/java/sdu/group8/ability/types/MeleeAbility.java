
package sdu.group8.ability.types;

import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonability.data.EffectContainer;

public class MeleeAbility extends Ability{

    public MeleeAbility(float expiration, float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, EffectContainer effectContainer, Entity owner, boolean aimable) {
        super(expiration, moveSpeed, weight, damageRange, imageURL, dimension, direction, pos, collisionType, effectContainer, owner, aimable);
    }

    @Override
    public Ability getNewInstance(Entity owner, float x, float y, boolean directionLeft) {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
