package sdu.group8.test.player;

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
import sdu.group8.common.data.World;
import sdu.group8.commonplayer.IPlayerService;
import sdu.group8.player.PlayerController;
import sdu.group8.player.Player;
import sdu.group8.weapon.WeaponGenerator;

public class PlayerTest {

    private GameData gameData;
    private World world;
    private Lookup lookup;
    private AbilityPlugin abilityPlugin;
    private PlayerController playerController;
    private AbilityController abilityController;
    private WeaponGenerator weaponGenerator;

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
        abilityPlugin = new AbilityPlugin();
        abilityPlugin.preStart(gameData);
        abilityController = new AbilityController();
        weaponGenerator = new WeaponGenerator();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(800);
        playerController = new PlayerController();
        playerController.start(gameData, world);
    }

    @After
    public void tearDown() throws Exception {
        lookup = null;
        gameData = null;
        world = null;
        playerController = null;
        abilityPlugin = null;
        abilityController = null;
        weaponGenerator = null;
    }

    @Test
    public void testGetMovementSpeed() {
        IPlayerService playerService = lookup.lookup(IPlayerService.class);

        assertEquals(1, world.getEntities().size());

        float movementSpeed = playerService.getPlayerMoveSpeed(world);

        for (Player player : world.getCastedEntities(Player.class)) {
            assertEquals(player.getMovespeed(), movementSpeed, 0);
        }
    }
}
