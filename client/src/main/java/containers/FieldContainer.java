package containers;

import javafx.scene.image.Image;
import objects.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Domin on 2016-04-26.
 */
public class FieldContainer {

    public static Map fields = new ConcurrentHashMap<Integer, Field>();

    public static Map getFields() { return fields; }

    public static void addField(Integer id, Integer x, Integer y, boolean isStorage, Image image, Boolean difficultToTraverse)
    {
        fields.put(id, new Field(id, x, y, isStorage, image, difficultToTraverse));
    }

    public static Field getField(Integer id)
    {
        Field field = (Field)fields.get(id);
        return field;
    }
}
