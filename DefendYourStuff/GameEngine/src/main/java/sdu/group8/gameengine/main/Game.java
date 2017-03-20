package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.World;
import sdu.group8.common.entity.Chunk;
import sdu.group8.common.entity.ChunkTypes;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.gameengine.managers.GameInputProcessor;


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
    private SpriteBatch batch;
    private BitmapFont font;
   

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
        //TODO: create grid with matrix from Map module.
        
        
        //TODO: load content of matrix into grid.

        for (IGamePluginService gamePlugin : getGamePlugins()) {
            gamePlugin.start(gameData, world);
        }
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
        
        int[] windowsX = new int[8];
        for (int i = 1; i <= 8; i++) {
            windowsX[i-1] = i*(gameData.getDisplayWidth() / 8);
        }
        int[] windowsY = new int[6];
        for (int i = 1; i <= 6; i++) {
            windowsY[i-1] = i*(gameData.getDisplayHeight() / 6);
        }
        
        previousMapLoad(windowsX, windowsY);
        
        Chunk castleChunk = world.getChunk(ChunkTypes.CASTLE_CHUNK);
        System.out.println("The castleChunk: " + castleChunk);

    }

    private void previousMapLoad(int[] windowsX, int[] windowsY) {
//        ChunkStuff c = new ChunkStuff();
//        
//        
//        
//        BlockTypes[][] blocks = c.getCastleChunk();
//        batch.begin();
//        for (int i = 0; i < blocks.length; i++) {
//            for (int j = 0; j < blocks[i].length; j++) {
//                font.draw(batch, blocks[i][j].name(), windowsX[i]-50, windowsY[j]-50);
//            }
//        }
        
        
//        for (int i = 0; i < windowsX.length; i++) {
//            System.out.println("WindowsX["+i+"] = " + windowsX[i]);
//        }
//        for (int i = 0; i < windowsY.length; i++) {
//            System.out.println("WindowsY["+i+"] = " + windowsY[i]);
//        }
        
        
        
        //batch.end();
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
