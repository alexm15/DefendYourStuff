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
    
    <P extends Projectile> Projectile createProjectile(Entity e, GameData gameData);
    <P extends Projectile> Projectile getProjectile(World world, UUID id);
    <P extends Projectile> Class getClass();
    <P extends Projectile> Collection getProjectiles();
    
    
}
