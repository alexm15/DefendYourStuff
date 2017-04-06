/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonitem;

import sdu.group8.common.entity.Item;

/**
 *
 * @author joach
 */
public interface IItemAction {
    /**
     * Performs the appropiate action when colliding or using an item
     * @param item the item the entity is using 
     */
    void ItemAction(Item item);
    
}
