
package sdu.group8.commonitem;

import java.util.Collection;
import java.util.UUID;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonitem.data.Item;


public interface ItemSPI {
    /**
     * Creates a new Item and returns it to the caller
     * @return a subtype of Item
     */
    <I extends Item> I createItem(Entity e, GameData gameData);
    /**
     * Creates a collection of Items
     * @return a collection of items
     */
    <I extends Item> Collection getItems();
    /**
     * @return a specific item using an ID      
     */
    <I extends Item> I getItem(World world, UUID id);
    
    /**
     * Spawns an item
     */
    <I extends Item> I spawnItem();
    
    /**
     * Spawns an item at a specific positioning
     * @param pos 
     */
    void spawnItem(Position pos);
}
