package sdu.group8.commonabilitytypes;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.EffectContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
/**
 *
 * @author joach
 */
public class MeleeAbility extends Ability {
    public MeleeAbility(float moveSpeed, float weight, DamageRange damageRange, String imageURL, Dimension dimension, Position pos, CollisionType collisionType, String name, EffectContainer effectContainer) {
        super(moveSpeed, weight, damageRange, imageURL, dimension, pos, collisionType, name, effectContainer);
    }
    
    public MeleeAbility(Ability ability) {
        super(ability.getMoveSpeed(), ability.getWeight(), ability.getDamageRange(), ability.getImageURL(), ability.getDimension(), ability.getPosition(), ability.getCollisionType(), ability.getName(), ability.getEffects());
    }
    
}