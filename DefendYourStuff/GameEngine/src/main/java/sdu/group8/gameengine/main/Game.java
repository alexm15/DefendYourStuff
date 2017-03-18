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
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.gameengine.managers.GameInputProcessor;
import sdu.group8.map.chunks.BlockTypes;
import sdu.group8.map.chunks.Chunk;

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
        Chunk c = new Chunk();
        int[] gameColumn = new int[8];
        for (int i = 1; i <= 8; i++) {
            gameColumn[i-1] = i*(gameData.getDisplayWidth() / 10);
        }
        int[] gameRows = new int[6];
        for (int i = 1; i <= 6; i++) {
            gameRows[i-1] = i*(gameData.getDisplayHeight() / 10);
        }
        System.out.println(gameColumn[0]);
        System.out.println(gameRows[0]);
        
        BlockTypes[][] blocks = c.getCastleChunk();
        System.out.println(blocks[0][0]);
        batch.begin();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length-1; j++) {
                font.draw(batch, blocks[i][j].name(), gameColumn[i]/2, gameRows[j]/2);
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
