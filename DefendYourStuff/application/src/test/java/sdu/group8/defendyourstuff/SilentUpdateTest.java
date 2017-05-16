/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.defendyourstuff;

import sdu.group8.common.services.IGameProcessingService;
import sdu.group8.common.services.IGamePluginService;
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
    
    private static final String ADD_PLAYER_UPDATES_FILE = "c:\\DefendYourStuffUpdates\\netbeans_site\\addupdates.xml";
    private static final String REMOVE_PLAYER_UPDATES_FILE = "c:\\DefendYourStuffUpdates\\netbeans_site\\removeupdates.xml";
    private static final String UPDATES_FILE = "c:\\DefendYourStuffUpdates\\netbeans_site\\updates.xml";
    
    public static Test suite() {
        return NbModuleSuite.createConfiguration(SilentUpdateTest.class).
                gui(false).
                failOnMessage(Level.WARNING).
                failOnException(Level.INFO).
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
        waitForUpdate(processors, plugins);
        
        //Pre asserts
        //Size should be 0 as there are no modules installed.
        assertEquals("no processors", 0, processors.size());
        assertEquals("bo plugins", 0, plugins.size());
        
        //Test: Load Player with the update center.
        copy(get(ADD_PLAYER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        //Asserts: Player loaded.
        assertEquals("Five processors", 5, processors.size());
        assertEquals("Five plugins", 5, plugins.size());
        
        //Test: Unload Player with the update center.
        copy(get(REMOVE_PLAYER_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        //Asserts: Player unloaded.
        assertEquals("Four processors", 4, processors.size());
        assertEquals("Four plugins", 4, plugins.size());
    }
    
    private void waitForUpdate(List<IGameProcessingService> processors, List<IGamePluginService> plugins) throws InterruptedException {
        Thread.sleep(10000);
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IGameProcessingService.class));
        
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
    }
}    
