/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonenemy;
import java.util.UUID;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Character;
import sdu.group8.common.entity.Entity;
/**
 *
 * @author Alexander
 */
public interface IEnemyService {
    <C extends Character> C getEnemy(UUID id, World world);
    <C extends Character> C getEnemyClass();
    <C extends Character> C createEnemy(Entity creator);
    
}
