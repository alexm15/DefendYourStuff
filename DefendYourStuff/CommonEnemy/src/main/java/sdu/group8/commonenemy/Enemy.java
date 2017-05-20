
package sdu.group8.commonenemy;

import sdu.group8.commonability.data.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.commoncharacter.Character;


public abstract class Enemy extends Character {
    
    public Enemy(float moveSpeed, float weight, float health, String imageURL, Dimension dimension, Direction direction, Position pos, CollisionType collisionType, AbilityData... ab) {
        super(moveSpeed, weight, health, imageURL, dimension, direction, pos, collisionType, ab);
    }
    
}
