
package sdu.group8.commonally;

import java.util.UUID;
import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;
import sdu.group8.common.entity.Entity;


public interface IAllyService {
    /**
     * Retrieves an Allied NPC object.
     * @param <C> a subtype of Character
     * @param id the id of the Allied NPC
     * @param world the collection of entities to search for the Allied NPC.
     * @return the Allied NPC object
     */
    <C extends Character> C getAlly(UUID id, World world);
    /**
     * Retrieves the class of the Allied NPC object
     * @param <C> a subtype of Character
     * @return Allied NPC class that extends Character.
     */
    <C extends Character> C getAllyClass();
    /**
     * Creates an Allied NPC through another entity in the game.
     * @param <C> a subtype of Character
     * @param creator the entity who creates the Allied NPC.
     * @return the created Allied NPC object. 
     */
    <C extends Character> C createAlly(Entity creator);
}
