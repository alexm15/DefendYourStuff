package sdu.group8.test.buildings;


import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.openide.util.Lookup;
import sdu.group8.buildingcontroller.BuildingControlSystem;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.commonbuilding.services.Buildable;


public class BuildingTest {

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
        BuildingControlSystem buildingController = new BuildingControlSystem();
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
    public void testCreateRubbleBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createRubbleBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    @Ignore // TODO: Create methodbody first
    public void testCreateBlacksmithBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createBlacksmithBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testCreateCastleBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createCastleBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testCreateDestroyedCastleBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createDestroyedCastleBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    @Ignore // TODO: Create methodbody first
    public void testFarmBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createFarmBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    public void testPortalBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createPortalBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }
    
    @Test
    @Ignore // TODO: Create methodbody first
    public void testTowerBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createTowerBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }
   
    @Test
    @Ignore // TODO: Create methodbody first
    public void testWallBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createWallBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }

    @Test
    @Ignore // TODO: Create methodbody first
    public void testWellBuilding() {
        Buildable buildingProvider = lookup.lookup(Buildable.class);

        assertEquals(0, world.getEntities().size());

        buildingProvider.createWellBuilding(world, new Position(0, 0));

        assertEquals(1, world.getEntities().size());
    }
}
