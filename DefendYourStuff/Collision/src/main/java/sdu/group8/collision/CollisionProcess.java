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
