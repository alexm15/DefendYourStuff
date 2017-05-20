/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.defendyourstuff;

import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.common.services.IGamePluginService;
import sdu.group8.common.services.IPreStartPluginService;
import sdu.group8.common.services.IGamePostProcessingService;
import java.io.IOException;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;
/**
 *
 * @author Mads
 */

public class SilentUpdateTest extends NbTestCase {
    
    private static final String REMOVE_PLAYER = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\noplayer.xml";
    private static final String UPDATES_FILE = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\updates.xml";
    private static final String ALL_FILES = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\all.xml";
    private static final String NO_FILES = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\none.xml";
    private static final String REMOVE_AI = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\noai.xml";
    private static final String REMOVE_ENEMY = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\noenemy.xml";
    private static final String REMOVE_DAY = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\noday.xml";
    private static final String REMOVE_BUILDING = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\nobuilding.xml";
    private static final String REMOVE_ABILITY = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\noability.xml";
    private static final String REMOVE_COLLISION = "C:\\Users\\joach\\Documents\\NetBeansProjects\\DefendYourStuff\\DefendYourStuff\\GameEngine\\src\\main\\resources\\netbeans_site\\testxml\\nocollision.xml";
    
    public static Test suite() {
        return NbModuleSuite.createConfiguration(SilentUpdateTest.class).
                gui(false).
                failOnMessage(Level.WARNING).
                //failOnException(Level.INFO).
                enableClasspathModules(false).
                clusters(".*").
                suite();
    }
    
    public SilentUpdateTest(String n) {
        super(n);
    }
    
    public void testSilentUpdater() throws InterruptedException, IOException {
        
        //Setup
        List<IGameProcessingService> processors = new CopyOnWriteArrayList<>();
        List<IGamePluginService> plugins = new CopyOnWriteArrayList<>();
        List<IPreStartPluginService> prestart = new CopyOnWriteArrayList<>();
        List<IGamePostProcessingService> postProcess = new CopyOnWriteArrayList<>();
        
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        copy(get(NO_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Pre asserts
        //Size should be 0 as there are no modules installed.
        assertEquals("no processors", 0, processors.size());
        assertEquals("no plugins", 0, plugins.size());
        assertEquals("no prestart", 0, prestart.size());
        assertEquals("no postprocess", 0, postProcess.size());
        
        //Test: Load Player with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: All components is loaded
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        playerTest(processors, plugins, prestart, postProcess);
        
        AITest(processors, plugins, prestart, postProcess);
        
        enemyTest(processors, plugins, prestart, postProcess);
        
        dayNightTest(processors, plugins, prestart, postProcess);
        
        buildingTest(processors, plugins, prestart, postProcess);
        
        abilityTest(processors, plugins, prestart, postProcess);
        
        collisionTest(processors, plugins, prestart, postProcess);
        
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void playerTest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws InterruptedException, IOException {        
        //Test: Unload Player with the update center.
        copy(get(REMOVE_PLAYER), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: Player unloaded.
        assertEquals("Four processors", 4, processors.size());
        assertEquals("Five plugins", 5, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        //Test: Reaload the player with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: Player reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void AITest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws InterruptedException, IOException {
        //AI
        //Test: Unload Player with the update center.
        copy(get(REMOVE_AI), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: AI unloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Five plugins", 5, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        //Test: Reaload the AI with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: AI reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void enemyTest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws IOException, InterruptedException {
        //ENEMY
        //Test: Unload Enemy with the update center.
        copy(get(REMOVE_ENEMY), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: Enemy unloaded.
        assertEquals("Four processors", 4, processors.size());
        assertEquals("Five plugins", 5, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        //Test: Reaload the enemy with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: enemy reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void dayNightTest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws InterruptedException, IOException {
        // Day/Night
        //Test: Unload day/night with the update center.
        copy(get(REMOVE_DAY), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: day/night unloaded.
        assertEquals("Four processors", 4, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        //Test: Reaload the day/night with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: day/night reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void buildingTest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws IOException, InterruptedException {
        //Building
        //Test: Unload building with the update center.
        copy(get(REMOVE_BUILDING), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: building unloaded.
        assertEquals("Four processors", 4, processors.size());
        assertEquals("Five plugins", 5, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        //Test: Reaload the building with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: building reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void abilityTest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws InterruptedException, IOException {
        //Ability
        //Test: Unload ability with the update center.
        copy(get(REMOVE_ABILITY), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: ability unloaded.
        assertEquals("Four processors", 4, processors.size());
        assertEquals("Five plugins", 5, plugins.size());
        assertEquals("Zero prestart", 0, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
        
        //Test: Reaload the ability with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: ability reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }

    private void collisionTest(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws InterruptedException, IOException {
        //Collision
        //Test: Unload collision with the update center.
        copy(get(REMOVE_COLLISION), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: collision unloaded.
        assertEquals("Four processors", 5, processors.size());
        assertEquals("Five plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("Zero postprocess", 0, postProcess.size());
        
        //Test: Reaload the collision with the update center.
        copy(get(ALL_FILES), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, prestart, postProcess);
        
        //Asserts: collision reloaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("One prestart", 1, prestart.size());
        assertEquals("One postprocess", 1, postProcess.size());
    }
    
    
    private void waitForUpdate(List<IGameProcessingService> processors, List<IGamePluginService> plugins, List<IPreStartPluginService> prestart, List<IGamePostProcessingService> postProcess) throws InterruptedException {
        Thread.sleep(10000);
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IGameProcessingService.class));
        
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
        
        prestart.clear();
        prestart.addAll(Lookup.getDefault().lookupAll(IPreStartPluginService.class));
                
        postProcess.clear();
        postProcess.addAll(Lookup.getDefault().lookupAll(IGamePostProcessingService.class));
    }
}    