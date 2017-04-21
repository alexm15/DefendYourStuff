/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.map.tiles;

import sdu.group8.common.data.Image;
import sdu.group8.common.entity.Tile;

/**
 *
 * @author Martin
 */
public class Tile_Dirt implements Tile {

    private Image image = new Image("Tiles/tile_dirt.png", false);

    @Override
    public Image getImage() {
        return image;
    }
}
