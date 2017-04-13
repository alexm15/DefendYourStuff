package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.entity.Tile;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.gameengine.managers.GameInputProcessor;
import sdu.group8.commonplayer.IPlayer;

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
    private Entity player;

    public Collection<? extends IGameProcessingService> getGameProcesses() {
        return lookup.lookupAll(IGameProcessingService.class);
    }

    public Collection<? extends IGamePluginService> getGamePlugins() {
        return lookup.lookupAll(IGamePluginService.class);
    }

    public Collection<? extends IGamePostProcessingService> getPostProcesses() {
        return lookup.lookupAll(IGamePostProcessingService.class);
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        CAM = new OrthographicCamera();
        CAM.setToOrtho(false, 800, 600);

        CAM.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        for (IGamePluginService gamePlugin : getGamePlugins()) {
            gamePlugin.start(gameData, world);
        }

        assetManager.load("Chunks/chunk_bg_base.PNG", Texture.class);
        assetManager.load("Chunks/chunk_bg_forrest01.PNG", Texture.class);
        assetManager.load("Chunks/chunk_bg_forrest02.PNG", Texture.class);
        assetManager.load("Chunks/chunk_bg_grassland01.PNG", Texture.class);
        assetManager.load("Chunks/chunk_bg_grassland02.PNG", Texture.class);
        assetManager.load("defaultImage.PNG", Texture.class);
        assetManager.load("Tiles/tile_dirt.PNG", Texture.class);
        assetManager.load("Tiles/tile_woodenFence.PNG", Texture.class);
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        update();

        gameData.getKeys().update();

        if (assetManager.update()) {
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

    }

    private void draw() {
        batch.setProjectionMatrix(CAM.combined);

        // Draw chunks
        drawMap();

        camLockTarget(CAM); // Draw player

        sr.begin(ShapeType.Line);
        sr.setColor(Color.MAROON);
        sr.line(0, 100, 800, 100);
        sr.end();
    }

    private void drawMap() {

        Chunk chunkMiddle = world.getChunkMiddle();
        renderChunk(chunkMiddle);

        for (Chunk chunk : world.getChunksRight()) {
            renderChunk(chunk);
        }

        for (Chunk chunk : world.getChunksLeft()) {
            renderChunk(chunk);
        }
    }

    private void renderChunk(Chunk chunk) {
        int posX = 0;
        int posY = 0;
        int tileOffsetX = chunk.getTileOffsetX();
        Tile[][] chunkTileMatrix = chunk.getTileMatrix();

        drawTextureFromAsset(chunk.getBackgroundImageURL(), (posX + tileOffsetX) * 100, gameData.getTILE_SIZE());
        for (Tile[] tileRow : chunkTileMatrix) {
            for (Tile tile : tileRow) {
                drawTextureFromAsset(tile.getImageURL(), (tileOffsetX + posX) * gameData.getTILE_SIZE(), posY * gameData.getTILE_SIZE());
                posY++;
            }
            posX++;
            posY = 0;
        }
    }

    private void camLockTarget(Camera cam) {
        for (Entity objectp : world.getEntities()) {
            if (objectp instanceof IPlayer) {
                this.player = objectp;

                //FIXME: midlertid løsning til at tegne en player.
                IPlayer p = (IPlayer) objectp;
                Position pos = p.getPlayerPosition();

                sr.setColor(Color.RED);
                sr.begin(ShapeRenderer.ShapeType.Filled);
                float x = player.getX();
                float y = player.getY();
                float height = player.getHeight();
                float width = player.getWidth();
                sr.rect(x, y, width, height);
                sr.end();

            }

        }
        Vector2 target = new Vector2(player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2));
        Vector3 position = cam.position;
        position.x = target.x;
//        position.y = target.y;
        cam.update();

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

    private void drawTextureFromAsset(String path, float x, float y) {
        Texture tex = assetManager.get(path, Texture.class);
        batch.begin();
        batch.draw(tex, x, y);
        batch.end();
    }
}
