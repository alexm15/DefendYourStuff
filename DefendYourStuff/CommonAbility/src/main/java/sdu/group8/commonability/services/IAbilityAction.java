package sdu.group8.commonability.services;

import sdu.group8.commonability.data.Ability;

public interface IAbilityAction {
    
    /**
     * Performs the appropiate action when colliding or using an ability
     * @param ab The ability that is used or collided with
     */
    void abilityAction(Ability ab);
    
}