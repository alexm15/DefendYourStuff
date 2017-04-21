/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonability;

import java.util.Collection;
import java.util.UUID;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.weapon.Weapon;

/**
 *
 * @author joach
 */
public interface AbilitySPI {

    /**
     * Creates a new Ability and returns it to the caller
     * @param caller The entity who creates the ability
     * @param ab The ability to be created
     * @return a subtype of Ability
     */
    Ability createAbility(Entity caller, Ability ab, GameData gameData);
    
    /**
     * Creates a new Ability and returns it to the caller
     * @param caller The entity who creates the ability
     * @param ab The ability to be created
     * @param weapon The callers ability - used for multipliers
     * @return a subtype of Ability
     */
    Ability createPlayerAbility(Entity caller, Ability ab, Weapon weapon, GameData gameData);

    /**
     * Creates a new Ability and returns it to the caller
     * @param caller The entity who creates the ability
     * @param ab The name of the ability to be created
     * @return a subtype of Ability
     */
    Ability createAbility(Entity caller, String ab, GameData gameData);
    
    /**
     * Creates a new Ability and returns it to the caller
     * @param caller The entity who creates the ability
     * @param ab The name of the ability to be created
     * @param weapon The callers ability - used for multipliers
     * @return a subtype of Ability
     */
    Ability createPlayerAbility(Entity caller, String ab, Weapon weapon, GameData gameData);
    
    /**
     * @return a specific ability using an ID
     */
    Ability getAbility(World world, UUID id);
    
    /**
     * @return a specific ability using an string
     */
    Ability getAbility(World world, String name);
    
    /**
     * Creates a collection of Abilities
     * @return a collection of Abilities
     */
    <A extends Ability> Collection getAbilities();

    /**
     * Creates a collection of ranged Abilities
     * @return a collection of ranged Abilities
     */
    <A extends Ability> Collection getRangedAbilities();

    /**
     * Creates a collection of melee Abilities
     * @return a collection of melee Abilities
     */
    <A extends Ability> Collection getMeleeAbilities();
    
    /**
     * Creates a collection of positioning Abilities
     * @return a collection of positioning Abilities
     */
    <A extends Ability> Collection getPositioningAbilities();
    
    /**
     * Creates a collection of summoning Abilities
     * @return a collection of summoning Abilities
     */
    <A extends Ability> Collection getSummoningAbilities();
    
    Ability getAbility(Ability ability);
            
    Ability getAbility(String name);
}
