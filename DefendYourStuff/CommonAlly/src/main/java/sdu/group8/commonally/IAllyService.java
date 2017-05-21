
package sdu.group8.commonally;

import java.util.UUID;
import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;
import sdu.group8.common.entity.Entity;


public interface IAllyService {
    /**
     * Creates an Allied NPC through another entity in the game.
     * @param <C> a subtype of Character
     * @param creator the entity who creates the Allied NPC.
     * @return the created Allied NPC object. 
     */
    <C extends Character> C createAlly(Entity creator);
}
