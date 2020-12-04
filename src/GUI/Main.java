package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

       /* */

        stage.setTitle("Computer Graphics Algorithms");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        /*Pane drawPane = (Pane) scene.lookup("#drawPane");

        drawPane.getChildren().add(path);*/

        primaryStage = stage;

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
