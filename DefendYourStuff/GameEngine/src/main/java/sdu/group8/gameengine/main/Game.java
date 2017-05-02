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
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Image;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Tile;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.commonmap.IMapUpdate;
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
    private Collection<Entity> entities;

    private Image firstBackgroundImage = new Image("World/world_hills01_bg.png", false);
    private Image secondBackgroundImage = new Image("World/world_mountains01_bg.png", false); //TODO: Change to mountains image
    private int firstBackgroundImageScrollX = 0;
    private int secondBackgroundImageScrollX = 0;

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
        font.setColor(Color.RED);
        
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

        assetManager.load("Enemy/dickbutt.gif", Texture.class);
        assetManager.load("abilities/fireball.png", Texture.class);

        assetManager.load("Tiles/tile_dirt.png", Texture.class);
        assetManager.load("Tiles/tile_brickWall.png", Texture.class);
        assetManager.load("Tiles/tile_air.png", Texture.class);
      
        assetManager.load("Building/castle.png", Texture.class);
        assetManager.load("Building/rubble.png", Texture.class);

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

        updateCamera();

        for (IGameProcessingService gameProcess : getGameProcesses()) {
            gameProcess.process(gameData, world);
        }
        for (IGamePostProcessingService postProcess : getPostProcesses()) {
            postProcess.process(gameData, world);
        }

        float camPositionX = CAM.position.x;

        Chunk secondLastChunk = world.getChunksRight().get(world.getChunksRight().size() - 2);
        float secondLastOffsetX = secondLastChunk.getPositionOffset();

        // Update Map if camera is near the end at the left side
        if (camPositionX > secondLastOffsetX) {
            for (IMapUpdate service : getIMapUpdate()) {
                service.update(world, false);
            }
        }

        secondLastChunk = world.getChunksLeft().get(world.getChunksLeft().size() - 2);
        secondLastOffsetX = secondLastChunk.getPositionOffset();

        // Update Map if camera is near the end at the right side
        if (camPositionX < secondLastOffsetX) {
            for (IMapUpdate service : getIMapUpdate()) {
                service.update(world, true);
            }
        }
    }

    private void draw() {
        batch.begin();

        //drawTextureFromAsset(secondBackgroundImage, CAM.position.x - CAM.viewportWidth / 2, gameData.getTILE_SIZE()); // Draw second background for the world;
        drawBackgroundImageForWorld(); //Draw backgrounds for the world;
        drawMap(); // Draw chunks
        drawEntities(); // Draw entities

        batch.end();
    }

    private void drawMap() {

        for (Chunk chunk : world.getChunksRight()) {
            renderChunk(chunk);
        }

        for (Chunk chunk : world.getChunksLeft()) {
            renderChunk(chunk);
        }
    }

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

    private void drawSecondBackgroundForChunk(Chunk chunk, float positionOffset) {
        float scrollScale = 50;
        float startScrollPos = positionOffset - chunk.getDimension().getWidth();
        float endScrollPos = positionOffset + chunk.getDimension().getWidth() * 2;
        float min = positionOffset - chunk.getDimension().getWidth() / scrollScale;
        float max = positionOffset + (chunk.getDimension().getWidth() * 2) / scrollScale;

        float playerPosX = gameData.getPlayerPosition().getX();
        float imagePosX = max;

        if (playerPosX < startScrollPos) {
            imagePosX = max;
        } else if (playerPosX > endScrollPos) {
            imagePosX = min;
        } else {
            imagePosX = max - Math.abs(startScrollPos - playerPosX) / scrollScale;
        }

        drawTextureFromAsset(chunk.getSecondBackgroundImage(), imagePosX, chunk.getTILE_SIZE());
    }

    private void drawEntities() {
        for (Entity entity : world.getEntities()) {
            drawTextureFromAsset(entity.getImage(), entity.getX() - (entity.getWidth() / 2), entity.getY() - entity.getHeight() / 2);
        }
    }

    private void updateCamera() {
        Vector3 camPos = CAM.position.cpy();
        CAM.position.set(gameData.getPlayerPosition().getX(), camPos.y, camPos.z);
        CAM.update();

        camPos = CAM.position.cpy();
        int firstScrollSpeed = (int) (camPos.x / 4);
        int secondScrollSpeed = firstScrollSpeed / 4;

        if (gameData.getKeys().isKeyDown(gameData.getKeys().A)) {
            firstBackgroundImageScrollX = firstScrollSpeed;
            secondBackgroundImageScrollX = secondScrollSpeed;
        }
        if (gameData.getKeys().isKeyDown(gameData.getKeys().D)) {
            firstBackgroundImageScrollX = firstScrollSpeed;
            secondBackgroundImageScrollX = secondScrollSpeed;

        }
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

    private void drawTextureFromAsset(Image image, float x, float y) {
        Texture tex = assetManager.get(image.getImageURL(), Texture.class);
        Sprite sprite = new Sprite(tex);
        sprite.flip(image.isReversed(), false);
        batch.draw(sprite, x, y);
    }

    private void drawBackgroundImageForWorld() {
        Texture firstTex = assetManager.get(firstBackgroundImage.getImageURL(), Texture.class);
        Texture secondTex = assetManager.get(secondBackgroundImage.getImageURL(), Texture.class);
        firstTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        secondTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.draw(secondTex, CAM.position.x - CAM.viewportWidth / 2, 0, secondBackgroundImageScrollX, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        batch.draw(firstTex, CAM.position.x - CAM.viewportWidth / 2, 0, firstBackgroundImageScrollX, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());
    }

    
}
