/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.UUID;
import sdu.group8.common.enums.CollisionType;

/**
 *
 * @author Martin
 */
public class Entity {
    private UUID id;
    private CollisionType collisionType;
    private float width;
    private float height;
    private float radius;
    private Position pos;
}
