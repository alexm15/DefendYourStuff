package sdu.group8.commonbuilding.services;

import sdu.group8.common.data.GameData;

/**
 * IDefBuilding is an interface for interacting with defensive buildings.
 * Include reapairing of buildings and upgrading of buildings from superinterface.
 * 
 * @author Group 8
 */
public interface IDefBuilding extends IBuildingService{
    /**
     * Repairs the building
     * 
     * Precondition: an entity is interacting with a building implementing this
     * interface, and the building has reduced health.
     * 
     * Postcondition: the building's health is increased up to a certain maximum
     * defined by the building.
     * @param goldCost gold cost for repairing the building.
     */
    void repair(GameData goldCost);
}
