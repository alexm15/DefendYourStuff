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
import org.junit.Ignore;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Direction;
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
    private Character enemy; 
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
    
    /**
     * Enemy needs dummy ability for any of the tests to work.
     */
    @Before
    public void setUp() {
        aiControl = new AI_ControlSystem();
        world = new World();
        gameData = new GameData();
        enemy = new Character(0, 0, 0, "", new Dimension(0, 0, 0), new Direction(true), new Position(0, 0), CollisionType.CIRCLE, new AbilityData[0]) {
            @Override
            public void collision(Entity otherEntity) {
            }
        };
        enemy.setMoveSpeed(10);
        //dummyAttackable = new DummyAttackable("", null, new Position(10, 0), CollisionType.CIRCLE, new Ability[0]);
        
        gameData.setDelta(0.5f);
        world.addEntity(enemy);
        
    }
    
    @After
    public void tearDown() {
        world.removeEntity(enemy);
        aiControl = null;
        enemy = null;
        world = null;
        dummyAttackable = null;
        gameData = null;
        
    }
    
    @Test
    @Ignore
    public void testEnemyMovesToClosestTargetLeftFromEnemy() {
        dummyAttackable = new DummyAttackable(0, new Dimension(0, 0, 0), new Direction(true), new Position(100, 0));
        float previousX = enemy.getX();
        float dummyX = dummyAttackable.getX();
        float initialDistance = Math.abs(dummyX-previousX);
        
        System.out.println(previousX);
        world.addEntity(dummyAttackable);
        
        
        aiControl.assignAttackAndDodgeEnemyAI(enemy, world, gameData);
        System.out.println("new x: " + enemy.getX());
        float newDistance = Math.abs(dummyAttackable.getX()-enemy.getX());
        assertTrue(newDistance < initialDistance);          
        
                
    }
    
    @Test
    @Ignore
    public void testNewEnemyIsCloser() {
        dummyAttackable = new DummyAttackable(0, new Dimension(0, 0, 0), new Direction(true) ,new Position(100, 0));
        //dummyAttackable = new DummyAttackable(0, null, new Direction(true), new Position(10, 0));
        world.addEntity(dummyAttackable);
        
        
        
        DummyAttackable newDummy = new DummyAttackable(0, new Dimension(0, 0, 0), new Direction(true) ,new Position(-90, 0));
        world.addEntity(newDummy);
        
        float initialNewDummyDist = Math.abs(newDummy.getX()-enemy.getX());
        float initialOldDummyDist = Math.abs(dummyAttackable.getX()-enemy.getX());
        
        aiControl.assignAttackAndDodgeEnemyAI(enemy, world, gameData);
        
        
        float updatedNewDummyDist = Math.abs(newDummy.getX()-enemy.getX());
        float updatedOldDummyDist = Math.abs(dummyAttackable.getX()-enemy.getX());
        
        System.out.println("new x value: "+ enemy.getX());
        assertTrue(updatedNewDummyDist < initialNewDummyDist);
        assertTrue(updatedOldDummyDist > initialOldDummyDist);
        
    }
    
    @Test
    @Ignore
    public void testRangedAI_EnemyMovesAwayFromEntity(){
        dummyAttackable = new DummyAttackable(0, new Dimension(0, 0, 0), new Direction(true) ,new Position(5, 0));
        world.addEntity(dummyAttackable);
  
        
        float initialEnemyToEntityDistance = Math.abs(dummyAttackable.getX()-enemy.getX());
        int minShootDistance = 10;
        aiControl.rangedAI(enemy, world, gameData, minShootDistance, 15);
        
        float updatedEnemyToEntityDistance = Math.abs(dummyAttackable.getX()-enemy.getX());        
        
                
        assertTrue(updatedEnemyToEntityDistance >  initialEnemyToEntityDistance);
    }
    
    
    @Test 
    @Ignore
    public void testEnemyShootsAtTarget() {
        dummyAttackable = new DummyAttackable(0, new Dimension(0, 0, 0), new Direction(true), new Position(100, 0));
        int minShootDistance = 10;
        
        aiControl.rangedAI(enemy, world, gameData, minShootDistance, 15);
        float distanceToPlayer = Math.abs(dummyAttackable.getX()-enemy.getX());
        assertTrue(distanceToPlayer <= minShootDistance);
        
        assertFalse(world.getEntities(Ability.class).isEmpty());
        
    }
    
}
