/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.player;

import sdu.group8.commonability.data.AbilityData;
import sdu.group8.commoncharacter.Character;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Position;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonweapon.data.Weapon;
import sdu.group8.commonenemy.IEnemyAction;
import sdu.group8.commonbuilding.services.IBuildingAction;
import sdu.group8.commonplayer.IPlayer;
import sdu.group8.commonplayer.IPlayerAction;

/**
 *
 * @author joach
 */
public class Player extends Character implements IPlayer, IEnemyAction, IBuildingAction {

    private Weapon weapon;
    private Position aimPoint;
    private final float JUMP_FORCE = 350;

    public Player(Position position, AbilityData... ab) {
        super(200, 1.25f, 100, "Player/defaultPlayer.png", new Dimension(50, 50, 25), new Direction(true), position, CollisionType.BOX, ab);
    }

    /**
     * Gets the player jump force.
     *
     * @return The jump force for the player.
     */
    public float getVerticalForce() {
        float verticalVelocity = JUMP_FORCE - getWeight();
        return verticalVelocity;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Position getAimPoint() {
        return aimPoint;
    }

    public void setAimPoint(Position aimPoint) {
        this.aimPoint = aimPoint;
    }

    public float getJUMP_FORCE() {
        return JUMP_FORCE;
    }

    @Override
    public float getPlayerMoveSpeed() {
        return getMoveSpeed();
    }

    @Override
    public void collision(Entity otherEntity) {
        if (otherEntity instanceof IPlayerAction) {
            ((IPlayerAction) otherEntity).playerAction((Entity) this);
        }
    }

    
    @Override
    public void enemyAction(Entity enemy) {
        //TODO: implement enemy action for player
    }
    
    @Override
    public HealthSystem getHealthSystem() {
        return this.health;
    }

    @Override
    public void buildingAction(Entity building) {
    }

}
