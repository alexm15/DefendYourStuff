package sdu.group8.buildingentities;

import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.commonability.data.Ability;
import sdu.group8.commonenemy.Enemy;


/**
 * Represents a destroyed castle which is displayed when the game has ended.
 */
public class DestroyedCastle extends DefensiveBuilding {

    public DestroyedCastle(Position pos) {
        super("Building/destroyedcastle.png", new Dimension(200, 100, 0), pos, CollisionType.BOX, false, 0, 100);
    }
    
    /**
     * Can't be destroyed since its hp is reduced by 0
     * Special case for DestroyedCastle
     * @param ab 
     */
    @Override
    public void abilityAction(Ability ab) {
        if ((ab.getOwner() instanceof Enemy)) {
            //A destroyed building should not lose health
            this.reduceHealth(0);
        }
    }
    
    

}
