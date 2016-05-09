package controllers;

import containers.FieldContainer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import objects.Field;
import objects.ForkLift;
import parsers.JSONParser;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Domin on 2016-04-26.
 */
public class MainController implements Initializable {

    @FXML
    public static Stage stage;

    @FXML
    public Canvas mapCanvas;

    @FXML
    public Button deliverButton;

    @FXML
    public Button resetButton;

    public GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = mapCanvas.getGraphicsContext2D();
        System.out.println("color set to black");
        //gc.fillRect(50, 50, 100, 100);
        System.out.println("draw rectangle");
        fillCanvas(FieldContainer.fields);
        JSONParser.mapCanvasHeight = (int)mapCanvas.getHeight();
    }

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;

    }

    @FXML
    public void deliverButtonClicked() throws NullPointerException {
        Stage deliverItemWindow = new Stage();
        deliverItemWindow.initModality(Modality.APPLICATION_MODAL);
        deliverItemWindow.setTitle("Podaj id pola końcowego");
        deliverItemWindow.setMinWidth(300);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label idLabel = new Label("Id:");
        GridPane.setConstraints(idLabel, 0, 0);

        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Button okButton = new Button("Zatwierdź");
        GridPane.setConstraints(okButton, 1, 1);

        okButton.setOnAction(e -> {
            try {
                Integer id = Integer.parseInt(idInput.getText());
                JSONParser.parseJSONPath("http://localhost:8080/path?endId=" + id.toString());
                fillCanvas(FieldContainer.fields);
            } catch (Exception ex) {
            } finally {
                deliverItemWindow.close();
            }
        });

        //Add everything to grid
        grid.getChildren().addAll(idLabel, idInput, okButton);

        Scene scene = new Scene(grid, 300, 100);
        deliverItemWindow.setScene(scene);
        deliverItemWindow.showAndWait();
    }

    @FXML
    public void resetButtonClicked() throws NullPointerException {
        resetButton.setOnAction(e -> {
            try{
                ForkLift.setLocation(0,0);
                ForkLift.setImage(new Image("resources/forklift.png"));
                JSONParser.parseJSONBoard("http://localhost:8080");
                fillCanvas(FieldContainer.fields);
            } catch (IOException ioe){

            }
        });
    }

    public void fillCanvas(Map<Integer, Field> mp)
    {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Field field = (Field)pair.getValue();
            gc.drawImage( field.getImage(), field.getX()*20, (mapCanvas.getHeight() - field.getY()*20) - 20); //500 - 490 - 10
            System.out.println("Drawing " + field.getX().toString() + " " + field.getY().toString());
        }
    }
}
