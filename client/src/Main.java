import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import containers.FieldContainer;
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.Field;
import objects.ForkLift;
import org.json.JSONException;
import parsers.JSONParser;

public class Main extends Application {

    @Override
    public void start( Stage primaryStage ) throws Exception {
        JSONParser.parseJSONBoard("http://localhost:8080");
        ForkLift.setImage(new Image("resources/forklift.png"));

        Pane root = FXMLLoader.load( Main.class.getResource("resources/MainStage.fxml") );

        Scene scene = new Scene( root, 1121, 535 );
        primaryStage.setScene( scene );
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(535);
        primaryStage.setTitle("Wózek");

        primaryStage.show();
        MainController.setStage(primaryStage);

    }

    public static void main(String[] args) throws IOException, JSONException {
        launch( args );
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("Enter id:");
            Integer id = Integer.parseInt(br.readLine());
            Field field = FieldContainer.getField(id);
            System.out.println(field.getX() + " " + field.getY());
        }















        /*JSONArray json = readJsonFromUrl("http://localhost:8080");
        for(int i=0; i<json.length()-1; i++) //i dąży do 2500
        {
            JSONArray column = json.getJSONArray(i);
            System.out.println(column.toString());
            for(int j=0; j<column.length(); j++)
            {
                JSONObject object = column.getJSONObject(j);
                System.out.println(object.get("id"));
                //System.out.println(object.toString());
            }
        }
        System.out.println(json.getJSONArray(json.length()-1).toString());
        //System.out.println(json.getJSONObject(56));*/
    }
}