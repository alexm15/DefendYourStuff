/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.collision;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sdu.group8.common.ability.Ability;
import sdu.group8.common.collision.CollisionContainer;
import sdu.group8.common.data.DamageRange;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.commoncharacter.Character;
import sdu.group8.common.entity.EntityType;

/**
 *
 * @author Martin
 */
public class CollisionProcessTest {

    GameData gameData = new GameData();
    World world = new World();

    public CollisionProcessTest() {
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
        world.getBuildings().clear();
        //world.getCharacters().clear();
        world.getItems().clear();
        world.getProjectiles().clear();
    }

    /**
     * Test of adding a collision event to gameData.
     */
    @org.junit.Test
    public void createCollisionEvent() {
        System.out.println("createCollisionEvent");
        CollisionProcess instance = new CollisionProcess();

        // player
        Dimension playerDim = new Dimension(10, 10);
        Position playerPos = new Position(0, 0);
        DamageRange playerDamageRange = new DamageRange(5, 100);
        Ability playerAbility = new Ability(playerPos, 0, playerDamageRange);
        CollisionContainer playerCollision = new CollisionContainer(EntityType.PLAYER, EntityType.PLAYER);
        Character player = new Character(100, playerDim, playerPos, playerCollision, playerAbility);
        //world.addCharacter(player);

        boolean expectedResult = gameData.getCollisionEvents().isEmpty();

        // Enemy
        Dimension enemyDim = new Dimension(5, 5);
        Position enemyPos = new Position(5, 5);
        DamageRange enemyDamageRange = new DamageRange(5, 100);
        Ability enemyAbility = new Ability(enemyPos, 0, enemyDamageRange);
        CollisionContainer enemyCollision = new CollisionContainer(EntityType.ENEMY, EntityType.ENEMY);
        Character enemy = new Character(100, enemyDim, enemyPos, enemyCollision, enemyAbility);
        //world.addCharacter(enemy);

        instance.process(gameData, world);

        boolean result = gameData.getCollisionEvents().isEmpty();
        assertNotEquals(expectedResult, result);
    }
    

}
