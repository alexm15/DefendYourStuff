/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.ai;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commoncharacter.Character;

/**
 *
 * @author Alexander
 */
public class AI_ControlSystemTest {

    public AI_ControlSystemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    @org.junit.Test
    public void testSomeMethod() {

    }

    /**
     * Test of assignAttackAndDodgeEnemyAI method, of class AI_ControlSystem.
     */
    @Test
    public void testAssignAttackAndDodgeEnemyAI() {
        AI_ControlSystem aiControl = new AI_ControlSystem();
        World w = new World();
        Character someEnemy = new Character(0, 0, 0, null, null, new Position(0, 0), CollisionType.CIRLCE, new Ability[0]) {
            @Override
            public void collision(Entity otherEntity) {
            }
        };
        float previousX = someEnemy.getX();
        aiControl.assignAttackAndDodgeEnemyAI(w,someEnemy);

        assertNotEquals(previousX, someEnemy.getX(), 0);
    }

}
