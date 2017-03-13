/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import java.util.UUID;
import sdu.group8.common.enums.WeaponType;

/**
 *
 * @author Martin
 */
public class Weapon {
    private UUID id;
    private AbilityContainer abilities;
    private WeaponType weaponType;
    private float damageMultiplier;
    private float rangeMultiplier;
}
