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
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;
import sdu.group8.common.entity.Entity;
import sdu.group8.commoncharacter.Character;
import sdu.group8.commonenemy.IEnemyAction;

/**
 *
 * @author Alexander
 */
public class AI_ControlSystemTest {
    
    private AI_ControlSystem aiControl;
    private Character someEnemy; 
    private World world;
    private GameData gameData;
    private DummyAttackable dummyAttackable;
    
    
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
        aiControl = new AI_ControlSystem();
        someEnemy = new Character(0, 0, 0, null, null, new Position(0, 0), CollisionType.CIRLCE, new Ability[0]) {
            @Override
            public void collision(Entity otherEntity) {
            }
        }; 
        someEnemy.setMoveSpeed(10);
        dummyAttackable = new DummyAttackable("", null, new Position(10, 0), CollisionType.CIRLCE, new Ability[0]);
        world = new World();
        gameData = new GameData();
        gameData.setDelta(0.5f);
        
    }
    
    @After
    public void tearDown() {
        aiControl = null;
        someEnemy = null;
        world = null;
        dummyAttackable = null;
        gameData = null;
    }
    
    @Test
    public void testEnemyMovesToClosestTargetLeftFromEnemy() {
        float previousX = someEnemy.getX();
        float dummyX = dummyAttackable.getX();
        float initialDistance = Math.abs(dummyX-previousX);
        
        System.out.println(previousX);
        world.addEntity(dummyAttackable);
        
        
        aiControl.assignAttackAndDodgeEnemyAI(someEnemy, world, gameData);
        
        float newDistance = Math.abs(dummyAttackable.getX()-someEnemy.getX());
        assertTrue(newDistance < initialDistance);          
        
                
    }
    
    @Test
    public void testNewEnemyIsCloser() {
        world.addEntity(dummyAttackable);
        DummyAttackable newDummy = new DummyAttackable("", null, new Position(-9, 0), CollisionType.CIRLCE, new Ability[0]);
        world.addEntity(newDummy);
        
        float initialNewDummyDist = Math.abs(newDummy.getX()-someEnemy.getX());
        float initialOldDummyDist = Math.abs(dummyAttackable.getX()-someEnemy.getX());
        
        aiControl.assignAttackAndDodgeEnemyAI(someEnemy, world, gameData);
        
        
        float updatedNewDummyDist = Math.abs(newDummy.getX()-someEnemy.getX());
        float updatedOldDummyDist = Math.abs(dummyAttackable.getX()-someEnemy.getX());
        
        System.out.println("new x value: "+ someEnemy.getX());
        assertTrue(updatedNewDummyDist < initialNewDummyDist);
        assertTrue(updatedOldDummyDist > initialOldDummyDist);
        
    }
    
}
