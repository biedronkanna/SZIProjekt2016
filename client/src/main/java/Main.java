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

import java.io.*;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		JSONParser.parseJSONBoard("http://localhost:8080");
		ForkLift.setImage(new Image(Main.class.getResource("forklift.png").toString()));

		Pane root = FXMLLoader.load(Main.class.getResource("MainStage.fxml"));

		Scene scene = new Scene(root, 1123, 646);
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(1123);
		primaryStage.setMinHeight(646);
		primaryStage.setTitle("Wózek");

		primaryStage.show();
		MainController.setStage(primaryStage);

	}

	public static void main(String[] args) throws IOException, JSONException {
		launch(args);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("Enter id:");
			Integer id = Integer.parseInt(br.readLine());
			Field field = FieldContainer.getField(id);
			System.out.println(field.getX() + " " + field.getY());
		}
	}
}