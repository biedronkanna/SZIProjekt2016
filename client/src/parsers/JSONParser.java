package parsers;

import containers.FieldContainer;
import javafx.scene.image.Image;
import objects.ForkLift;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import objects.Field;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Domin on 2016-04-26.
 */
public class JSONParser {

    private static List<String> path;

    public static Integer mapCanvasHeight;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static void parseJSONBoard(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray jsonArray = new JSONArray(jsonText);
            for(int i=0; i<jsonArray.length(); i++) {
                JSONArray column = jsonArray.getJSONArray(i);
                System.out.println(column.toString());
                for(int j=0; j<column.length(); j++) {
                    JSONObject object = column.getJSONObject(j);
                    if(!object.isNull("forkLift")) {
                        ForkLift.currentPosition = object.getInt("id");
                        FieldContainer.addField(object.getInt("id"), object.getInt("x"), object.getInt("y"), object.getBoolean("difficultToTraverse"), new Image("resources/forklift.png"), object.getBoolean("difficultToTraverse"));
                        ForkLift.setOnMapDirection(object.getJSONObject("forkLift").getString("direction"));
                    }
                    else if(!object.isNull("storage")) {
                        FieldContainer.addField(object.getInt("id"), object.getInt("x"), object.getInt("y"), object.getBoolean("difficultToTraverse"), new Image("resources/foodstorage.png"), object.getBoolean("difficultToTraverse"));
                    }
                    else {
                        Image image;
                        if(object.getBoolean("difficultToTraverse") == true) {
                            image = new Image("resources/fielddifficult.png");
                        }
                        else {
                            image = new Image("resources/field.png");
                        }
                        FieldContainer.addField(object.getInt("id"), object.getInt("x"), object.getInt("y"), object.getBoolean("difficultToTraverse"), image, object.getBoolean("difficultToTraverse"));
                    }
                    System.out.println(object.get("id"));
                    System.out.println(object.getBoolean("difficultToTraverse"));
                }
            }
        } finally {
            is.close();
        }
    }

    public static void parseJSONPath(String url) throws IOException, JSONException, NullPointerException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader((new InputStreamReader(is, Charset.forName("UTF-8"))));
            String jsonText = readAll(rd);
            String regex = "\\[|\\]";
            jsonText = jsonText.replaceAll(regex, "");
            String[] pathList = jsonText.split(",");

            Map fields = FieldContainer.getFields();
            for ( int i = 0; i < pathList.length; i++) {
                if(pathList[i].equals("\"MOVE\"")) {
                    System.out.println("MOVE");
                    ForkLift.currentPosition += ForkLift.getNextMove();
                    Field currentField = (Field)fields.get(ForkLift.currentPosition);
                    System.out.println(currentField.getX());
                    System.out.println(ForkLift.currentPosition);
                    FieldContainer.addField(ForkLift.currentPosition, currentField.getX(), currentField.getY(), currentField.getIsdifficultToTraverse(), new Image("resources/forklift.png"), currentField.getIsdifficultToTraverse());
                }
                else if(pathList[i].equals("\"LEFT\"")) {
                    System.out.println("LEFT");
                    ForkLift.setDirection("LEFT");
                }
                else if(pathList[i].equals("\"RIGHT\"")) {
                    System.out.println("RIGHT");
                    ForkLift.setDirection("RIGHT");
                }
                else System.out.println("Found NOTHING");
            }
        } catch (NullPointerException npe) {

        } finally {
            is.close();
        }
    }
}
