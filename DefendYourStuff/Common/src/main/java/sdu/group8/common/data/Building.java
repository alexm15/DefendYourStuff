/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

import sdu.group8.common.enums.BuildingType;

/**
 *
 * @author Martin
 */
public class Building extends Entity{
    private BuildingType buildingType;
    private AbilityContainer abilities;
    private boolean isAttackable;
    private int upgradeLevel;
    private HealthSystem health;
}
