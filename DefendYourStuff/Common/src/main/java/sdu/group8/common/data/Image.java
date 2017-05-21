
package sdu.group8.common.data;

/**
 * Image class used for storing the image of an entity.
 * @author Group 8
 */
public class Image {
    private String imageURL;
    private boolean reversed;

    /**
     * Creates an image object for an object in the game if no image
     * path is supplied then a default image is loaded, 
     * Images are located in the
     * GameEngine module under DefendYourStuff\GameEngine\src\main\resources, 
     * if image is in subfolder then the image path needs to be the following 
     * format "subfolder/image.png".
     * @param imageURL the path to the image file, is relative to the 
     * projects folder.
     * @param reversed if true then the image is reversed, based on the way
     * the original image file.
     */
    public Image(String imageURL, boolean reversed) {
        if (imageURL.isEmpty())
            this.imageURL = "defaultImage.png";
        else 
            this.imageURL = imageURL;
                
        this.reversed = reversed;        
    }

    public String getImageURL() {
        return imageURL;
    }

    /**
     * Changes the imageFile of the game object, Images are located in the
     * GameEngine module under DefendYourStuff\GameEngine\src\main\resources, 
     * if image is in subfolder then the image path needs to be the following 
     * format "subfolder/image.png".
     * @param imageURL the path to the image file, is relative to the 
     * projects folder.  
     */
    public void setImageURL(String imageURL) {
        if (imageURL.isEmpty())
            this.imageURL = "defaultImage.png";
        else 
            this.imageURL = imageURL;
                
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reverse) {
        this.reversed = reverse;
    }
    
    
}
