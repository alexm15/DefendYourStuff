package sdu.group8.test.weapon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sdu.group8.test.enemy.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.openide.util.Lookup;
import sdu.group8.ability.controller.AbilityController;
import sdu.group8.ability.controller.AbilityPlugin;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.weapon.Weapon;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyService;
import sdu.group8.commonweapon.services.IWeaponService;
import sdu.group8.enemy.BigMeleeEnemy;
import sdu.group8.enemy.EnemyController;
import sdu.group8.enemy.MediumEnemy;

/**
 *
 * @author joach
 */

public class WeaponTest {

    private GameData gameData;
    private World world;
    private Lookup lookup;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        lookup = Lookup.getDefault();
        gameData = new GameData();
        world = new World();
        AbilityPlugin abilityPlugin = new AbilityPlugin();
        abilityPlugin.preStart(gameData);
        AbilityController abilityController = new AbilityController();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(800);
    }

    @After
    public void tearDown() throws Exception {
        lookup = null;
        gameData = null;
        world = null;
        
    }
    
    @Test
    public void createRangedTest() {
        IWeaponService weaponProvider = lookup.lookup(IWeaponService.class);
        Weapon weapon = weaponProvider.createRanged();
        
        assertNotNull(weapon);
        assertEquals(weapon.getClass(), Weapon.class);
    }
    
    @Test
    public void createMeeleTest() {
        IWeaponService weaponProvider = lookup.lookup(IWeaponService.class);
        Weapon weapon = weaponProvider.createMelee();
        assertNotNull(weapon);
        assertEquals(weapon.getClass(), Weapon.class);
    }
    
    
}
