package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainController {

    List<String> ALGORITHMS = new ArrayList<String>() {{
        add("Bresenham");
        add("Polylines");
        add("Circulo");
        add("Curve");
        add("Preenchimento-Polylines");
        add("Preenchimento-Circulo");
        add("Varredura");
        add("Recorte-De-Linha");
        add("Recorte-De-Poligonos");
        add("Translacao");
        add("Escala");
        add("Rotacao");
    }};

    ObservableList<String> algorithmChoices = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> choices;

    @FXML
    public Label welcomeLabel;

    @FXML
    public Button buttonStart;

    @FXML
    public void initialize() {
        welcomeLabel.setWrapText(true);
        insertAlgorithmChoices();

        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../resources/" +
                            choices.getValue().toLowerCase() +".fxml"));
                    Stage stage = new Stage();
                    stage.setTitle(choices.getValue() + " Algorithm");
                    stage.setScene(new Scene(root, 668, 715));
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void insertAlgorithmChoices() {
        algorithmChoices.setAll(ALGORITHMS);
        choices.setValue("Bresenham");
        choices.setItems(algorithmChoices);
    }

}
