package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
    private Collection<Entity> entities;
    private SpriteBatch batch;
    private BitmapFont font;

    private int screen = 8;
    private int leftOfScreen;
    private int rightOfScreen;

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
        entities = world.getEntities();
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
        leftOfScreen = 8;
        rightOfScreen = 8;

        Chunk middleChunk = world.getChunkMiddle();
        ArrayList<Chunk> leftChunk = world.getChunksLeft();
        ArrayList<Chunk> rightChunk = world.getChunksRight();

        loadScreenChunk(middleChunk, screen, "Middle");

        for (Chunk chunk : leftChunk) {
            leftOfScreen -= 8;
            loadScreenChunk(chunk, leftOfScreen, "Left");
        }
        for (Chunk chunk : rightChunk) {
            rightOfScreen += 8;
            loadScreenChunk(chunk, rightOfScreen, "Right");
        }

        sr.begin(ShapeType.Line);
        sr.setColor(1, 1, 1, 1);
        sr.line(0, 100, 800, 100);
        sr.end();

        // Used to test playermovements
        // TODO: Insert player
//        for (Entity player : entities) {
//            sr.setColor(Color.RED);
//            sr.begin(ShapeRenderer.ShapeType.Filled);
//            float x = player.getX();
//            float y = player.getY();
//            float height = player.getHeight();
//            float width = player.getWidth();
//            sr.rect(x, y, width, height);
//            sr.end();   
//        }
    }

    /**
     * Draws the block layout of the specific chunk to the game window.
     *
     * @param theChunk the chunk loaded in game window
     * @param chunkPosition the chunk position in the game window. (See static
     * int's for positions)
     */
    private void loadScreenChunk(Chunk theChunk, int chunkPosition, String arrayPosition) {
        batch.begin();
        ArrayList<Integer> a = new ArrayList<>();

        //TODO: Generate chunks
//        if (arrayPosition.equalsIgnoreCase("middle")) {
//            a = gameData.getWindowsxMiddle();
//        } else if (arrayPosition.equalsIgnoreCase("left")) {
//            a = gameData.getWindowsxLeft();
//        } else if (arrayPosition.equalsIgnoreCase("right")) {
//            a = gameData.getWindowsxRight();
//        }
//        for (int i = 0; i < theChunk.length; i++) {
//            for (int j = 0; j < theChunk[i].length; j++) {
//                //FIXME: Fix array indexOutOfBoundsException
//                font.draw(batch, theChunk[i][j].name(), a.get(i) - 50, gameData.getWindowsY()[j] - 50);
//            }
//        }
        batch.end();
    }

    private void drawMap() {

        int tileOffsetX = 0;
        //TODO: set tile size in GameData
        final int TILE_SIZE = 100;

        Chunk chunkMiddle = world.getChunkMiddle();
        Tile[][] chunkMiddleTileMatrix = chunkMiddle.getTileMatrix();
        Texture base_bg_texture = new Texture(getTextureFile(chunkMiddle.getBackgroundImageURL()));
        
        Position base_bg_position = new Position(0, 0);
        drawSprite(base_bg_texture, base_bg_position);
        
        for (int r = chunkMiddleTileMatrix.length; r >= 0; r--) {

            for (int c = 0; c < chunkMiddleTileMatrix[r].length; c++) {
                Tile currentTile = chunkMiddleTileMatrix[r][c];

                Texture texture = new Texture(getTextureFile(currentTile.getImageURL()));
                Position spritePosition = new Position((tileOffsetX + r) * TILE_SIZE, c * TILE_SIZE);
                
                drawSprite(texture, spritePosition);
            }
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

    private FileHandle getTextureFile(String texturePath) {
        FileHandle fileHandle = Gdx.files.classpath(texturePath);

        if (fileHandle.exists()) {
            return fileHandle;
        }

        System.out.println("Can't find texture: " + texturePath);
        fileHandle = Gdx.files.classpath("defaultImage.png");
        return fileHandle;
    }

    private void drawSprite(Texture texture, Position position) {
        Sprite sprite = new Sprite(texture);
        
        sprite.setPosition(position.getX(), position.getY());

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
