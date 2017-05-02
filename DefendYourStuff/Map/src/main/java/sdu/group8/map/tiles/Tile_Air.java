
package sdu.group8.map.tiles;

import sdu.group8.common.data.Image;
import sdu.group8.common.entity.Tile;


public class Tile_Air implements Tile{
    
    private Image image = new Image("Tiles/tile_air.png", false);
    
    @Override
    public Image getImage() {
        return image;
    }

}
