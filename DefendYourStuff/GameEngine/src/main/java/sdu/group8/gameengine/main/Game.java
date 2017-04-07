package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.BlockTypes;
import sdu.group8.common.entity.Entity;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.gameengine.managers.GameInputProcessor;
import sdu.group8.commonplayer.IPlayer;
import sdu.group8.commonplayer.IPlayerService;

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

    private final GameData gameData = new GameData();
    private World world = new World();
    private Lookup lookup = Lookup.getDefault();
    private List<IGameProcessingService> gameProcesses = new ArrayList<>();
    private List<IGamePluginService> gamePlugins = new ArrayList<>();
    private List<IGamePostProcessingService> postProcesses = new ArrayList<>();
    private static Game instance = null;
    private Collection<Entity> entities;
    private SpriteBatch batch;
    private BitmapFont font;
    private Entity player;

    /**
     * Positions chunk in the game window
     */
    private int screen = 8;
    /**
     * Positions chunk left of game window
     */
    private int leftOfScreen;
    /**
     * Positions chunk right of game window
     */
    private int rightOfScreen;

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        CAM = new OrthographicCamera(400, 400);
        CAM.setToOrtho(false, 400, 400);
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

        //TODO: load content of matrix into grid.
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        gameData.getKeys().update();

        draw();

        //cam follow player
        Vector2 target = new Vector2(player.getX(), player.getY());

        camLockTarget(CAM, target);

    }

    private void camLockTarget(Camera c, Vector2 target) {
        Vector3 position = c.position;
        position.x = target.x;
        position.y = 200;
        c.position.set(position);

        batch.setProjectionMatrix(c.combined);
        c.update();

    }

    private void update() {

        for (IGameProcessingService gameProcess : getGameProcesses()) {
            gameProcess.process(gameData, world);
        }
        for (IGamePostProcessingService postProcess : getPostProcesses()) {
            postProcess.process(gameData, world);
        }

        for (Object objectp : world.getEntities()) {
            if (objectp instanceof IPlayer) {
                this.player = (Entity) objectp;

                //FIXME: midlertid lÃ¸sning til at tegne en player.
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

    }

    //TODO: Change draw method later for sprites.
    private void draw() {
        leftOfScreen = 8;
        rightOfScreen = 8;

        ArrayList<BlockTypes[][]> middleChunk = gameData.getChunksMiddle();
        ArrayList<BlockTypes[][]> leftChunk = gameData.getChunksRight();
        ArrayList<BlockTypes[][]> rightChunk = gameData.getChunksLeft();

        for (BlockTypes[][] chunk : middleChunk) {
            loadScreenChunk(chunk, screen, "Middle");
        }
        for (BlockTypes[][] chunk : leftChunk) {
            leftOfScreen -= 8;
            loadScreenChunk(chunk, leftOfScreen, "Left");
        }
        for (BlockTypes[][] chunk : rightChunk) {
            rightOfScreen += 8;
            loadScreenChunk(chunk, rightOfScreen, "Right");
        }

        sr.begin(ShapeType.Line);
        sr.setColor(1, 1, 1, 1);
        sr.line(0, 100, 800, 100);
        sr.end();

    }

    /**
     * Draws the block layout of the specific chunk to the game window.
     *
     * @param theChunk the chunk loaded in game window
     * @param chunkPosition the chunk position in the game window. (See static
     * int's for positions)
     */
    private void loadScreenChunk(BlockTypes[][] theChunk, int chunkPosition, String arrayPosition) {
        batch.begin();
        ArrayList<Integer> a = new ArrayList<>();
        if (arrayPosition.equalsIgnoreCase("middle")) {
            a = gameData.getWindowsxMiddle();
        } else if (arrayPosition.equalsIgnoreCase("left")) {
            a = gameData.getWindowsxLeft();
        } else if (arrayPosition.equalsIgnoreCase("right")) {
            a = gameData.getWindowsxRight();
        }
        for (int i = 0; i < theChunk.length; i++) {
            for (int j = 0; j < theChunk[i].length; j++) {
                //FIXME: Fix array indexOutOfBoundsException
                font.draw(batch, theChunk[i][j].name(), a.get(i) - 50, gameData.getWindowsY()[j] - 50);
            }
        }
        batch.end();
    }

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

}
