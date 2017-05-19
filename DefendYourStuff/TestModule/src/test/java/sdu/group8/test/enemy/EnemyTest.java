package sdu.group8.test.enemy;

import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openide.util.Lookup;
import sdu.group8.ability.controller.AbilityController;
import sdu.group8.ability.controller.AbilityPlugin;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Entity;
import sdu.group8.commonenemy.Enemy;
import sdu.group8.commonenemy.IEnemyService;
import sdu.group8.enemy.BigMeleeEnemy;
import sdu.group8.enemy.EnemyController;
import sdu.group8.enemy.MediumEnemy;

public class EnemyTest {

    private GameData gameData;
    private World world;
    private Lookup lookup;
    private AbilityPlugin abilityPlugin;
    private EnemyController enemyController;
    private AbilityController abilityController;

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
        enemyController = new EnemyController();
        abilityPlugin = new AbilityPlugin();
        abilityPlugin.preStart(gameData);
        abilityController = new AbilityController();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(800);
    }

    @After
    public void tearDown() throws Exception {
        lookup = null;
        gameData = null;
        world = null;
        enemyController = null;
        abilityPlugin = null;
        abilityController = null;

    }

    @Test
    public void testBigMeleeEnemy() {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);
        Enemy testEnemy = new BigMeleeEnemy(new Position(0, 0));

        assertEquals(0, world.getEntities().size());
        enemyProvider.createBigEnemy(world, gameData, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
        for (Enemy enemy : world.getCastedEntities(Enemy.class)) {
            assertEquals(enemy.getClass(), testEnemy.getClass());
        }

        enemyProvider.removeAllEnemies(world);
        assertEquals(0, world.getEntities().size());
    }

    @Test
    public void testMediumEnemy() {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);
        Enemy testEnemy = new MediumEnemy(new Position(0, 0));

        assertEquals(0, world.getEntities().size());
        enemyProvider.createMediumEnemy(world, gameData, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
        for (Enemy enemy : world.getCastedEntities(Enemy.class)) {
            assertEquals(enemy.getClass(), testEnemy.getClass());
        }

        enemyProvider.removeAllEnemies(world);
        assertEquals(0, world.getEntities().size());
    }

    @Test
    public void testRemoveEnemy() {
        IEnemyService enemyProvider = lookup.lookup(IEnemyService.class);

        assertEquals(0, world.getEntities().size());

        world.addEntity(new BigMeleeEnemy(new Position(0, 0)));
        world.addEntity(new BigMeleeEnemy(new Position(0, 0)));
        world.addEntity(new MediumEnemy(new Position(0, 0)));
        world.addEntity(new MediumEnemy(new Position(0, 0)));

        assertEquals(4, world.getEntities().size());
        enemyProvider.createBigEnemy(world, gameData, new Position(0, 0));

        enemyProvider.removeAllEnemies(world);
        assertEquals(0, world.getEntities().size());
    }
}
