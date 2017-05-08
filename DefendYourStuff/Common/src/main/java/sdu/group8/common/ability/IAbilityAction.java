package sdu.group8.common.ability;

import sdu.group8.common.ability.Ability;

/**
 *
 * @author joach
 */
public interface IAbilityAction {
    
    /**
     * Performs the appropiate action when colliding or using an ability
     * @param ab The ability that is used or collided with
     */
    void abilityAction(Ability ab);
    
}