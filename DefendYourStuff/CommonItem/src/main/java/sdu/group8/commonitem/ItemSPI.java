/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonitem;

import java.util.Collection;
import java.util.UUID;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Item;

/**
 *
 * @author joach
 */
public interface ItemSPI {
    /**
     * Creates a new Item and returns it to the caller
     * @return a subtype of Item
     */
    <I extends Item> I createItem(Entity e, GameData gameData);
    /**
     * @return Item.class 
     */
    <I extends Item> Class getClass();
    /**
     * Creates a collection of Items
     * @return a collection of items
     */
    <I extends Item> Collection getItems();
    /**
     * @return a specific item using an ID
     */
    <I extends Item> I getItem(World world, UUID id);
}
