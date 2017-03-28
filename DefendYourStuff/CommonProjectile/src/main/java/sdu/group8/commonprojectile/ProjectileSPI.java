/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonprojectile;

import java.util.Collection;
import java.util.UUID;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Projectile;

/**
 *
 * @author joach
 */
public interface ProjectileSPI {
    
    /**
     * Creates a new Projectil and returns it to the caller
     * @return a subtype of Projectile
     */
    <P extends Projectile> P createProjectile(Entity e, GameData gameData);
    /**
     * @return a specific item using an ID
     */
    <P extends Projectile> P getProjectile(World world, UUID id);
    /**
     * @return Item.class 
     */
    <P extends Projectile> Class getClass();
    /**
     * Creates a collection of Projectiles
     * @return a collection of Projectiles
     */
    <P extends Projectile> Collection getProjectiles();
}
