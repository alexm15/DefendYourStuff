
package sdu.group8.commonitem;

import sdu.group8.commonitem.data.Item;


public interface IItemAction {
    /**
     * Performs the appropiate action when colliding or using an item
     * @param item the item the entity is using 
     */
    void ItemAction(Item item);
    
}
