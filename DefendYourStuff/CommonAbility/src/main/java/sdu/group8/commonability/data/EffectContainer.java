/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.commonability.data;

import java.util.ArrayList;


/**
 *
 * @author joach
 */
public class EffectContainer {
    
    private ArrayList<Effect> effects;
    
    public EffectContainer(Effect... ef) {
        effects = new ArrayList<>();
        for(Effect effect : ef) {
            effects.add(effect);
        }
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }
}

    

