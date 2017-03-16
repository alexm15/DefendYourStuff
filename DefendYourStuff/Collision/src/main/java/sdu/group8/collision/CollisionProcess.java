/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.collision;

import java.util.ArrayList;
import java.util.Collection;
import sdu.group8.common.data.Building;
import sdu.group8.common.data.GameData;
import sdu.group8.common.data.MovingEntity;
import sdu.group8.common.data.Character;
import sdu.group8.common.data.Dimension;
import sdu.group8.common.data.Position;
import sdu.group8.common.data.World;
import sdu.group8.common.enums.CollisionType;
import sdu.group8.common.services.IGamePostProcessingService;

/**
 *
 * @author Martin
 */
public class CollisionProcess implements IGamePostProcessingService {

    @Override
    public void process(GameData gameData, World world) {

    }

    private boolean circleBoxCollision(Position posE1, float radiusE1, Position posE2, Dimension dimensionE2) {

        float combinedX = Math.abs(posE1.getX() - posE2.getX());
        float combinedY = Math.abs(posE1.getY() - posE2.getY());
        float yLength = radiusE1 + (dimensionE2.getHeight() / 2);
        float xLength = radiusE1 + (dimensionE2.getWidth() / 2);

        // If circle is inside or intersecting with the box dimensions, then there is a collision.
        if (combinedX < xLength) {
            if (combinedY < yLength) {
                return true;
            }
        }
        
        return false;
    }

    private boolean circleCollision(Position e1, Position e2, float radiusE1, float radiusE2) {
        boolean b = false;

        // hyp = sqrt((x1-x2)^2 + (y1-y2)^2)
        float x = e1.getX() - e2.getX();
        float y = e1.getY() - e2.getY();
        float hyp = (float) Math.hypot(x, y);

        // If the distance between both entities (hyp) is less than their combined radius, there is a collision.
        if ((radiusE1 + radiusE2) > hyp) {
            b = true;
        }

        return b;
    }
}
