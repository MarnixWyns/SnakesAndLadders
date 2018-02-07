package be.kdg.SnakesAndLadders.model;


import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Ruben Vanloo
 * 6/02/2018.
 */
public class DiceAnimation {
    private ArrayList images;
    private int imagesIndex;
    private long animationTime;
    private long wantedTime;

    //constructor
    public DiceAnimation(){
        images = new ArrayList();
        wantedTime = 0;
        start();
    }


    //add scene to arraylist and set time for each scene
    public synchronized void addImages(Image image, long time){
        wantedTime+=time;
        images.add(new oneImage(image, wantedTime));
    }
    //start animation from beginning
    private synchronized void start() {
        animationTime = 0;
        imagesIndex = 0;
    }
    //change images
    public synchronized void update(long timePassed){
        if(images.size() > 1){
            animationTime += timePassed;
            if(animationTime >= wantedTime){
                animationTime = 0;
                imagesIndex = 0;
            }
            while (animationTime > getimage(imagesIndex).endTime){
                imagesIndex++;
            }
        }
    }

    //get animations current image
    public synchronized javafx.scene.image.Image getAnimationImage(){
        if(images.size() ==0){
            return null;
        }else {
            return getimage(imagesIndex).pic;
        }
    }
    //getimage
    private oneImage getimage(int x){
        return (oneImage)images.get(x);
    }
    //private inner class
    private class oneImage {
        Image pic;
        long endTime;

        public oneImage (Image pic, long endTime){
            this.pic = pic;
            this.endTime = endTime;
        }
    }

}
