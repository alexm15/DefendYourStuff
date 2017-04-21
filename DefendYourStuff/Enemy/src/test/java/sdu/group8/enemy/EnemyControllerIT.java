/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.enemy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.CollisionType;

/**
 *
 * @author karim møller
 */
public class EnemyControllerIT {
    
    World w = null;
    
    public EnemyControllerIT() {
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

    /**
     * Test of createMediumEnemy method, of class EnemyController.
     */
    @Test
    public void testCreateMediumEnemy() {
        System.out.println("createMediumEnemy");
        World world = new World();
        GameData gameData = new GameData();
        Position position = new Position(0, 0);
        EnemyController instance = new EnemyController();
        instance.createMediumEnemy(world, gameData, position);
        assert(!world.getEntities().isEmpty());
        
        
    }
}
