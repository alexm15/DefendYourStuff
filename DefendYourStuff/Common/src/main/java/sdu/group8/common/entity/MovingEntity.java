/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.entity;

import java.util.ArrayList;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityContainer;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.CollisionContainer;

/**
 *
 * @author Martin
 */
public abstract class MovingEntity extends Entity {

    private float dx;
    private float dy;
    private AbilityContainer abilites;

    public MovingEntity(Dimension dimension, Position pos, CollisionContainer collision, Ability... ab) {
        super(dimension, pos, collision);
        this.abilites = new AbilityContainer(ab);
    }
        
    public ArrayList<Ability> getAbilities() {
        return abilites.getAbilites();
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void setDirection(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
