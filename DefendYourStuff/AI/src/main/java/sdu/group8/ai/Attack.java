
package sdu.group8.ai;

import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonability.data.AbilityData;
import sdu.group8.commoncharacter.Character;


public class Attack {
    
    private static Attack instance;
    
    private Attack() {
    }
    
    public static Attack getInstance() {
        if (instance == null) {
            return new Attack();
        }
        return instance;
    }

    public void useAbility(Character character, World world, Entity closestTarget, AbilityData abilityData) {
        setDirection(character, closestTarget);
        abilityData.useAbility(character, world);
    }

    private void setDirection(Character character, Entity closestTarget) {
        if (character.getX() < closestTarget.getX()) {
            character.setDirection(false);
        } else if (character.getX() > closestTarget.getX()) {
            character.setDirection(true);
        }
    }
}
