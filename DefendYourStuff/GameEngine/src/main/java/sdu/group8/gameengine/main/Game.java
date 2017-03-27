package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.BlockTypes;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;
import static sdu.group8.common.entity.ChunkTypes.*;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.gameengine.managers.GameInputProcessor;
import sdu.group8.common.entity.Character;

/**
 *
 * @author Group8
 */
public class Game
        implements ApplicationListener {

    private static OrthographicCamera CAM;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private World world = new World();
    private Lookup lookup = Lookup.getDefault();
    private List<IGameProcessingService> gameProcesses = new ArrayList<>();
    private List<IGamePluginService> gamePlugins = new ArrayList<>();
    private List<IGamePostProcessingService> postProcesses = new ArrayList<>();
    private static Game instance = null;
    private Collection<Character> characters;
    private SpriteBatch batch;
    private BitmapFont font;
    

    /**
     * Positions chunk in the game window
     */
    private static int SCREEN = 8;
    /**
     * Positions chunk left of game window
     */
    private int LEFT_OF_SCREEN;
    /**
     * Positions chunk right of game window 
     */
    private int RIGHT_OF_SCREEN;

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        CAM = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        CAM.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
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
        characters = world.getCharacters();
        //TODO: load content of matrix into grid.

    }

    private Game() {

    }

    public static Game getInstance() {
        if (instance == null) {
            return new Game();
        }
        return instance;
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
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {

        for (IGameProcessingService gameProcess : getGameProcesses()) {
            gameProcess.process(gameData, world);
        }
        for (IGamePostProcessingService postProcess : getPostProcesses()) {
            postProcess.process(gameData, world);
        }
        
        
    }

    //TODO: Change draw method later for sprites.
    private void draw() {
        LEFT_OF_SCREEN = 8;
        RIGHT_OF_SCREEN = 8;
        
        ArrayList<BlockTypes[][]> middleChunk = gameData.getChunksMiddle();
        ArrayList<BlockTypes[][]> leftChunk = gameData.getChunksRight();
        ArrayList<BlockTypes[][]> rightChunk = gameData.getChunksLeft();
        
        for (BlockTypes[][] chunk : middleChunk) {
            loadScreenChunk(chunk, SCREEN, "Middle");
        }
        for (BlockTypes[][] chunk : leftChunk) {
            LEFT_OF_SCREEN -= 8;
            loadScreenChunk(chunk, LEFT_OF_SCREEN, "Left");
        }
        for (BlockTypes[][] chunk : rightChunk) {
            RIGHT_OF_SCREEN += 8;
            loadScreenChunk(chunk, RIGHT_OF_SCREEN, "Right");
        }
        
        sr.begin(ShapeType.Line);
        sr.setColor(1, 1, 1, 1);
        sr.line(0, 100, 800, 100);
        sr.end();
         // Used to test playermovements
        for (Character player : characters) {
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

    /**
     * 
     * @param theChunk the chunk loaded in game window
     * @param chunkPosition the chunk position in the game window. (See static int's for positions)
     */
    private void loadScreenChunk(BlockTypes[][] theChunk, int chunkPosition, String arrayPosition) {
        batch.begin();
        ArrayList<Integer> a = new ArrayList<>();
        if (arrayPosition.equalsIgnoreCase("middle")) {
            a = gameData.getWindowsxMiddle();
        }
        else if (arrayPosition.equalsIgnoreCase("left")) {
            a = gameData.getWindowsxLeft();
        }
        else if (arrayPosition.equalsIgnoreCase("right")) {
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
