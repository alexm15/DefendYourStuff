/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.gameengine.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Installer
        extends ModuleInstall {

    @Override
    public void restored() {
        // TODO
        LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = "DefendYourStuff";
		cfg.width = 800;
		cfg.height = 600;
		cfg.useGL30 = false;
		cfg.resizable = false;
                
		new LwjglApplication(Game.getInstance(), cfg);
    }

}
