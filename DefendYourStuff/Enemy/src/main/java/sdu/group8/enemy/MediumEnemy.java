/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import sdu.group8.commonenemy.Enemy;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.ability.IAbilityAction;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonplayer.IPlayerAction;

/**
 *
 * @author Martin
 */
public class MediumEnemy extends Enemy implements IPlayerAction, IAbilityAction, IBuildingAction {

    public MediumEnemy(Position position, AbilityData... abilities) {
        super(5, 100, 10, 100, "Enemy/EnemyBow.png", new Dimension(50, 50, 50/2), 
                new Direction(true), position, CollisionType.BOX, abilities);
    }
    
  
    
    
    @Override
    public void collision(Entity otherEntity) {
        if(otherEntity instanceof IEnemyAction) {
            ((IEnemyAction) otherEntity).enemyAction((Entity)this);
        }
    }

    @Override
    public void playerAction(Entity player) {
        //TODO: create ability in world
        
    }

    @Override
    public void abilityAction(Ability ab) {
        this.reduceHealth(ab.getDamage());
    }

    @Override
    public void execute() {
        //TODO implement metode: execute
        throw new UnsupportedOperationException("Methode: 'execute' Not supported yet in class: 'MediumEnemy'");
    }
}
