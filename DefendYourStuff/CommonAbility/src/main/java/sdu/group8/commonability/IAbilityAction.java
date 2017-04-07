/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonability;

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
    void AbilityAction(Ability ab);
    
}
