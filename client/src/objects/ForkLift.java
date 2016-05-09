package objects;

import javafx.scene.image.Image;

/**
 * Created by Domin on 2016-04-26.
 */
public class ForkLift {

    private static Location location = new Location();

    private static Image image;

    public static int currentPosition;

    public static int onMapDirection; // 1 - N, 2 - W, 3 - S, 4 - E

    public static void setLocation(int posX, int posY) {
        location.set(posX, posY);
    }

    public static void setImage(Image img) { image = img; }

    public static Image getImage() { return image; }

    public static Integer getXPos()
    {
        return location.getX();
    }

    public static Integer getYPos()
    {
        return location.getY();
    }

    public static Integer moveDown() { return -50; } //-50

    public static Integer moveUp() { return (50); } //50

    public static Integer moveLeft() { return (-1); }

    public static Integer moveRight() { return 1; }

    public static Integer getNextMove() {
        if(onMapDirection == 4) return moveRight();
        if(onMapDirection == 1) return moveUp();
        if(onMapDirection == 2) return moveLeft();
        if(onMapDirection == 3) return moveDown();
        return null;
    }

    public static void setOnMapDirection(String direction) {
        if(direction.equals("N")) onMapDirection = 1;
        if(direction.equals("W")) onMapDirection = 2;
        if(direction.equals("S")) onMapDirection = 3;
        if(direction.equals("E")) onMapDirection = 4;
    }

    public static void setDirection(String rotation) {
        if(rotation.equals("LEFT")) {
            if(onMapDirection == 4) onMapDirection = 1;
            else onMapDirection++;
        }
        else if(rotation.equals("RIGHT")) {
            if(onMapDirection == 1) onMapDirection = 4;
            else onMapDirection--;
        }
    }
}
