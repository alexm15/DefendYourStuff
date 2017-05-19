
package sdu.group8.commonability.data;

import java.util.ArrayList;
import java.util.Arrays;


public class EffectContainer {
    
    private ArrayList<Effect> effects;
    
    public EffectContainer(Effect... ef) {
        effects = new ArrayList<>();
        effects.addAll(Arrays.asList(ef));
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }
}

    

