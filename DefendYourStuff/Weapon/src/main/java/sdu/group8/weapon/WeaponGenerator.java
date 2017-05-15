/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.weapon;

import static java.util.Locale.lookup;
import java.util.Random;
import sdu.group8.common.ability.AbilityData;
import sdu.group8.commonweapon.services.IWeaponService;
import sdu.group8.common.weapon.Weapon;
import sdu.group8.commonability.services.AbilitySPI;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Alexander
 */
@ServiceProvider(service = IWeaponService.class)

public class WeaponGenerator implements IWeaponService {

    private Lookup lookup = Lookup.getDefault();
    private float percentage = 1.10f;
    private Random random = new Random();

    /**
     * Generates a random percentage between min and max
     *
     * @param min - the minimum value for the random
     * @param max - the maximum value for the random
     * @return the random as a float in percent
     */
    private float randomMultiplier(int min, int max) {
        float temp = (float) random.nextInt(max - min) + min;
        return (temp / 100);
    }

    /**
     * A weapon with higher range multipliers than damage multipliers.
     *
     * NB: returns null if AbilitySPI is unavailable.
     *
     * @return a weapon with 4 ability slots and a random damage and range
     * multiplier.
     */
    @Override
    public Weapon createRanged() {
        AbilitySPI abilityProvider = lookup.lookup(AbilitySPI.class);

        if (abilityProvider != null) {
            AbilityData[] abilityList = new AbilityData[4];
            AbilityData ab = abilityProvider.getRangedAbilities().get(0);
            abilityList[0] = ab;
            Weapon rangedWep = new Weapon(randomMultiplier(80, 120), (randomMultiplier(80, 120)) * percentage, abilityList);
            return rangedWep;

        } else {
            return null;
        }
        
    }

    /**
     * A weapon with higher damage multipliers than range multipliers.
     *
     * NB: returns null if AbilitySPI is unavailable.
     *
     * @return a weapon with 4 ability slots and a random damage and range
     * multiplier.
     */
    @Override
    public Weapon createMelee() {
        AbilitySPI abilityProvider = lookup.lookup(AbilitySPI.class);
        if (abilityProvider != null) {
            AbilityData[] abilityList = new AbilityData[4];
            AbilityData ab = abilityProvider.getMeleeAbilities().get(0);
            abilityList[0] = ab;
            Weapon meleeWep = new Weapon(randomMultiplier(80, 120) * percentage, randomMultiplier(80, 120), abilityList);
            return meleeWep;
        } else {
            return null;
        }
    }

}
