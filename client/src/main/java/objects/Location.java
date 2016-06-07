package objects;

/**
 * Created by Domin on 2016-04-26.
 */
public class Location {

    private Integer x,y;

    public void set(Integer xPos, Integer yPos) {
        this.x = xPos;
        this.y = yPos;
    }

    public void setX(Integer xPos)
    {
        this.x = xPos;
    }

    public void setY(Integer yPos)
    {
        this.y = yPos;
    }

    public Integer getX()
    {
        return this.x;
    }

    public Integer getY() { return this.y; }
}
