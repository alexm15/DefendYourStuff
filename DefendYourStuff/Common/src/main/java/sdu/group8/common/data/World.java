/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Martin
 */
public class World {
    private Map<UUID, MovingEntity> movingEntities;
    private Map<UUID, Building> buildings;
    private Map<UUID, Item> items;
}
