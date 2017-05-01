/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.group8.common.data;

/**
 *
 * @author Martin
 */
public class Image {
    private String imageURL;
    private boolean reversed;

    public Image(String imageURL, boolean reversed) {
        this.imageURL = imageURL;
        this.reversed = reversed;
    }

    public Image() {
        //TODO: implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reverse) {
        this.reversed = reverse;
    }
    
    
}
