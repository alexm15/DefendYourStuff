
package sdu.group8.commonweapon.services;

import sdu.group8.commonweapon.data.Weapon;

public interface IWeaponService {

    public Weapon createRanged();
    public Weapon createMelee();
}