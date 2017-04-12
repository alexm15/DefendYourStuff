package sdu.group8.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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

    private final GameData gameData = new GameData();
    private World world = new World();
    private Lookup lookup = Lookup.getDefault();
    private List<IGameProcessingService> gameProcesses = new ArrayList<>();
    private List<IGamePluginService> gamePlugins = new ArrayList<>();
    private List<IGamePostProcessingService> postProcesses = new ArrayList<>();
    private static Game instance = null;
    private Collection<Entity> entities;
    private Entity player;

    private int screen = 8;
    private int leftOfScreen;
    private int rightOfScreen;

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
        leftOfScreen = 8;
        rightOfScreen = 8;

        // Draw sprites
        drawMap();

        camLockTarget(CAM); // Draw player

        sr.begin(ShapeType.Line);
        sr.setColor(Color.MAROON);
        sr.line(0, 100, 800, 100);
        sr.end();
    }

    private void drawMap() {

        int tileOffsetX = 0;
        //TODO: set tile size in GameData
        final int TILE_SIZE = 100;

        Chunk chunkMiddle = world.getChunkMiddle();
        Tile[][] chunkMiddleTileMatrix = chunkMiddle.getTileMatrix();
        Texture base_bg_texture = new Texture(getTextureFile(chunkMiddle.getBackgroundImageURL()));

        Position base_bg_position = new Position(0, 0);
        drawToSpriteBatch(base_bg_texture, base_bg_position);

        for (int r = chunkMiddleTileMatrix.length - 1; r >= 0; r--) {

            for (int c = 0; c < chunkMiddleTileMatrix[r].length; c++) {
//                Tile currentTile = chunkMiddleTileMatrix[r][c];
//
//                Texture texture = new Texture(getTextureFile(currentTile.getImageURL()));
                Position spritePosition = new Position((tileOffsetX + r) * TILE_SIZE, c * TILE_SIZE);
//
//                drawToSpriteBatch(texture, spritePosition);
                batch.begin();
                font.draw(batch, "Hey", spritePosition.getX(), spritePosition.getY());
                batch.end();
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
        fileHandle = Gdx.files.classpath("defaultImage.PNG");
        return fileHandle;
    }

    private void drawSprite(Texture texture, Position position) {
        Sprite sprite = new Sprite(texture);
        sprite.setPosition(position.getX(), position.getY());
        sprite.draw(batch);
    }

    private void drawToSpriteBatch(Texture texture, Position position) {
        batch.begin();
        batch.draw(texture, position.getX(), position.getY());
        batch.end();
    }
}
