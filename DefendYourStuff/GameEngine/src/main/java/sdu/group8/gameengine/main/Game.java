package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.HealthSystem;
import sdu.group8.common.data.Image;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.MovingEntity;
import sdu.group8.common.entity.Tile;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.commonmap.IMapUpdate;
import sdu.group8.commonplayer.IPlayer;
import sdu.group8.gameengine.managers.GameInputProcessor;

/**
 *
 * @author Group8
 */
public class Game
        implements ApplicationListener {

    public static Game getInstance() {
        if (instance == null) {
            return new Game();
        }
        return instance;
    }

    private Game() {

    }
    private static OrthographicCamera CAM;
    private ShapeRenderer sr;
    private SpriteBatch batch;
    private BitmapFont font;
    private AssetManager assetManager;

    private final GameData gameData = new GameData();
    private World world = new World();
    private Lookup lookup = Lookup.getDefault();
    private List<IGameProcessingService> gameProcesses = new ArrayList<>();
    private List<IGamePluginService> gamePlugins = new ArrayList<>();
    private List<IGamePostProcessingService> postProcesses = new ArrayList<>();
    private static Game instance = null;

    private Image firstBackgroundImage = new Image("World/world_hills01_bg.png", false);
    private Image secondBackgroundImage = new Image("World/world_mountains01_bg.png", false); //TODO: Change to mountains image
    private int firstBackgroundImageScrollX = 0;
    private int secondBackgroundImageScrollX = 0;

    private float FONT_SCALE = 1.5f;

    private float HUD_POS_OFFSET_X = 30f;
    private float HUD_POS_OFFSET_Y = 25f;

    public Collection<? extends IGameProcessingService> getGameProcesses() {
        return lookup.lookupAll(IGameProcessingService.class);
    }

    public Collection<? extends IGamePluginService> getGamePlugins() {
        return lookup.lookupAll(IGamePluginService.class);
    }

    public Collection<? extends IGamePostProcessingService> getPostProcesses() {
        return lookup.lookupAll(IGamePostProcessingService.class);
    }

    public Collection<? extends IMapUpdate> getIMapUpdate() {
        return lookup.lookupAll(IMapUpdate.class);
    }

    private Collection<? extends IPreStartPluginService> getPreGamePlugins() {
        return lookup.lookupAll(IPreStartPluginService.class);
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        CAM = new OrthographicCamera();
        CAM.setToOrtho(false, 800, 600);

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        batch = new SpriteBatch();
        font = new BitmapFont();

        for (IPreStartPluginService preGamePlugin : getPreGamePlugins()) {
            preGamePlugin.preStart(gameData);
        }

        for (IGamePluginService gamePlugin : getGamePlugins()) {
            gamePlugin.start(gameData, world);
        }

        assetManager.load("Chunks/chunk_base_bg01.png", Texture.class);
        assetManager.load("Chunks/chunk_forest01_bg01.png", Texture.class);
        assetManager.load("Chunks/chunk_forest01_bg02.png", Texture.class);
        assetManager.load("Chunks/chunk_grassland01_bg01.png", Texture.class);
        assetManager.load("Chunks/chunk_portal01_bg01.png", Texture.class);

        assetManager.load("World/world_hills01_bg.png", Texture.class);
        assetManager.load("World/world_mountains01_bg.png", Texture.class);

        assetManager.load("defaultImage.png", Texture.class);
        assetManager.load("defaultBackground.png", Texture.class);

        assetManager.load("Player/defaultPlayer.png", Texture.class);

        assetManager.load("Enemy/EnemyBow.png", Texture.class);
        assetManager.load("Enemy/EnemySword.png", Texture.class);

        assetManager.load("abilities/fireball.png", Texture.class);
        assetManager.load("abilities/slash.png", Texture.class);

        assetManager.load("Tiles/tile_dirt.png", Texture.class);
        assetManager.load("Tiles/tile_brickWall.png", Texture.class);
        assetManager.load("Tiles/tile_air.png", Texture.class);

        assetManager.load("Building/castle.png", Texture.class);
        assetManager.load("Building/rubble.png", Texture.class);
        assetManager.load("Building/portal.png", Texture.class);

    }

    @Override
    public void render() {

        // clear screen to white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // If asset manager is done loading assets.
        if (assetManager.update()) {
            CAM.update();
            batch.setProjectionMatrix(CAM.combined);
            gameData.setDelta(Gdx.graphics.getDeltaTime());

            update();

            gameData.getKeys().update();
            draw();
        }
    }

    private void update() {

        for (IGameProcessingService gameProcess : getGameProcesses()) {
            gameProcess.process(gameData, world);
        }
        for (IGamePostProcessingService postProcess : getPostProcesses()) {
            postProcess.process(gameData, world);
        }

        updateCamera();
        checkMapBoundary();
    }

    /**
     * Update the map with a new chunk, if the camera is near the last chunk on
     * either side of the map.
     */
    private void checkMapBoundary() {
        float camPositionX = CAM.position.x;

        // If the player is on the left side in the world.
        if (camPositionX < 0) {

            Chunk secondLastChunk = world.getChunksLeft().get(world.getChunksLeft().size() - 2);
            float secondLastOffsetX = secondLastChunk.getPositionOffset();

            // Update Map if camera is near the end at the right side
            if (camPositionX < secondLastOffsetX) {
                for (IMapUpdate service : getIMapUpdate()) {
                    service.update(world, true);
                }
            }

        } else {
            Chunk secondLastChunk = world.getChunksRight().get(world.getChunksRight().size() - 2);
            float secondLastOffsetX = secondLastChunk.getPositionOffset();

            // Update Map if camera is near the end at the left side
            if (camPositionX > secondLastOffsetX) {
                for (IMapUpdate service : getIMapUpdate()) {
                    service.update(world, false);
                }
            }
        }
    }

    private void draw() {
        batch.begin();

        drawBackgroundImageForWorld();  // Draw backgrounds for the world;
        drawMap();                      // Draw chunks
        drawEntities();                 // Draw entities
        drawHUD();

        batch.end();
    }

    /**
     * Loops through all chunks in the world, and calls a render method for each
     * chunk.
     */
    private void drawMap() {

        for (Chunk chunk : world.getChunksRight()) {
            renderChunk(chunk);
        }

        for (Chunk chunk : world.getChunksLeft()) {
            renderChunk(chunk);
        }
    }

    /**
     * Handles rendering of a chunk and its tiles and background images.
     *
     * @param chunk The chunk to be handled.
     */
    private void renderChunk(Chunk chunk) {
        float posX = 0;
        float posY = 0;
        float positionOffset = chunk.getPositionOffset();
        Tile[][] chunkTileMatrix = chunk.getTileMatrix();

        drawSecondBackgroundForChunk(chunk, positionOffset);
        drawTextureFromAsset(chunk.getFirstBackgroundImage(), positionOffset, chunk.getTILE_SIZE());

        for (Tile[] tileRow : chunkTileMatrix) {
            for (Tile tile : tileRow) {
                drawTextureFromAsset(tile.getImage(), positionOffset + (posX * chunk.getTILE_SIZE()), posY * chunk.getTILE_SIZE());
                posY++;
            }
            posX++;
            posY = 0;
        }
    }

    /**
     * Handles how the second background image for a chunk should be drawn.
     *
     * @param chunk The Chunk that contains the background image.
     * @param positionOffset The position offset of the chunk in the game world.
     * The offset start at pos(0,0) and is moved on the x-axis based on the
     * amount of tiles until the current chunk.
     */
    private void drawSecondBackgroundForChunk(Chunk chunk, float positionOffset) {
        float scrollScale = chunk.getBackgroundScrollScale();
        float startScrollPos = positionOffset - chunk.getDimension().getWidth();
        float endScrollPos = positionOffset + chunk.getDimension().getWidth() * 2;
        float minPosX = positionOffset - chunk.getDimension().getWidth() / scrollScale;
        float maxPosX = positionOffset + (chunk.getDimension().getWidth() * 2) / scrollScale;

        float playerPosX = gameData.getPlayerPosition().getX();
        float imagePosX = maxPosX;

        if (playerPosX < startScrollPos) {
            imagePosX = maxPosX;
        } else if (playerPosX > endScrollPos) {
            imagePosX = minPosX;
        } else {
            imagePosX = maxPosX - Math.abs(startScrollPos - playerPosX) / scrollScale;
        }

        drawTextureFromAsset(chunk.getSecondBackgroundImage(), imagePosX, chunk.getTILE_SIZE());
    }

    private void drawEntities() {
        Collection<Entity> movingEntities = new ArrayList<>();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof MovingEntity) {
                movingEntities.add(entity);
            } else {
                drawTextureFromAsset(entity.getImage(), entity.getX() - (entity.getWidth() / 2), entity.getY() - entity.getHeight() / 2);
            }
        }
        
        if(!movingEntities.isEmpty()) {
            for(Entity entity : movingEntities) {
                drawTextureFromAsset(entity.getImage(), entity.getX() - (entity.getWidth() / 2), entity.getY() - entity.getHeight() / 2);
            }
        }
    }

    /**
     * Updates the camera position and how far the world's background images
     * should scroll.
     */
    private void updateCamera() {
        Vector3 camPos = CAM.position.cpy();
        CAM.position.set(gameData.getPlayerPosition().getX(), camPos.y, camPos.z);

//        float camPosX = camPos.x;
//        float moveSpeed = 200;
//
//        if (gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
//            camPosX -= moveSpeed * gameData.getDelta();
//        }
//        if (gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
//            camPosX += moveSpeed * gameData.getDelta();
//        }
//        CAM.translate(camPosX - camPos.x, 0);
        firstBackgroundImageScrollX = (int) (camPos.x / 4);    // the first background image scrolls 1/4 the speed of the cam
        secondBackgroundImageScrollX = firstBackgroundImageScrollX / 4;   // the second background image scrolls 1/4 of the first background image.

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    /**
     * Handles rendering of a single texture.
     *
     * @param image Image object that contains a string to the correct path.
     * @param x float position x of the image
     * @param y float position y of the image
     */
    private void drawTextureFromAsset(Image image, float x, float y) {
        Texture tex = assetManager.get(image.getImageURL(), Texture.class);
        Sprite sprite = new Sprite(tex);
        sprite.flip(image.isReversed(), false);
        batch.draw(sprite, x, y);
    }

    /**
     * Handles rendering of both scrollable background images for the world.
     */
    private void drawBackgroundImageForWorld() {
        Texture firstTex = assetManager.get(firstBackgroundImage.getImageURL(), Texture.class);
        Texture secondTex = assetManager.get(secondBackgroundImage.getImageURL(), Texture.class);
        firstTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        secondTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.draw(secondTex, CAM.position.x - CAM.viewportWidth / 2, 0, secondBackgroundImageScrollX, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        batch.draw(firstTex, CAM.position.x - CAM.viewportWidth / 2, 0, firstBackgroundImageScrollX, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());
    }

    /**
     * Handles randering of the Heads Up Display (HUD) of the player. The HUD
     * displayes the health and gold amount of the player.
     */
    private void drawHUD() {
        HealthSystem healthSystem = null;
        try {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof IPlayer) {
                    healthSystem = ((IPlayer) entity).getHealthSystem();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //TODO: catch nullPointError
        float screenHeight = CAM.viewportHeight;
        float screenWidth = CAM.viewportWidth;
        float posX = CAM.position.x - screenWidth / 2;

        drawPlayerHealth(healthSystem, posX + HUD_POS_OFFSET_X, screenHeight - HUD_POS_OFFSET_Y);
        drawPlayerGold(posX + HUD_POS_OFFSET_X, screenHeight - HUD_POS_OFFSET_Y * 2);
    }

    private void drawPlayerHealth(HealthSystem healthSystem, float posX, float posY) {
        font.setColor(Color.BLACK);
        font.setScale(FONT_SCALE);
        font.draw(batch, "Health: " + healthSystem.getHealth() + " / " + healthSystem.getMaxHealth(), posX, posY);
    }

    private void drawPlayerGold(float posX, float posY) {
        font.setColor(Color.BLACK);
        font.setScale(FONT_SCALE);
        font.draw(batch, "Gold: " + gameData.getPlayerGold(), posX, posY);
    }

}
