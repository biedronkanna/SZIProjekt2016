package objects;

import javafx.scene.image.Image;

/**
 * Created by Domin on 2016-04-26.
 */
public class Field {

    private boolean isStorage;

    private Boolean isdifficultToTraverse;

    private Location location;

    private Integer id;

    private Image image;

    public void setPosition(Integer x, Integer y)
    {
        location.set(x,y);
    }

    public void setX(Integer x)
    {
        location.setX(x);
    }

    public void setY(Integer y)
    {
        location.setY(y);
    }

    public void setDifficulty(boolean difficult) { isdifficultToTraverse = difficult; }

    public Integer getX()
    {
        return location.getX();
    }

    public Integer getY()
    {
        return location.getY();
    }

    public Boolean getIsdifficultToTraverse() { return isdifficultToTraverse; }

    public Image getImage() { return image; }

    public Field(Integer id, Integer x, Integer y, boolean isStorage, Image image, Boolean difficultToTraverse) {
        this.id = id;
        location = new Location();
        location.set(x,y);
        this.isStorage = isStorage;
        this.image = image;
        this.isdifficultToTraverse = difficultToTraverse;
    }
}
