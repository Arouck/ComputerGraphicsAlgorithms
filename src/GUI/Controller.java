package GUI;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField pointX1;
    @FXML
    private TextField pointY1;
    @FXML
    private TextField pointX2;
    @FXML
    private TextField pointY2;
    @FXML
    private Pane drawPane;

    @FXML
    public void printPoints() {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(new Label(pointX1.getText()), new Label(pointY1.getText()),
                new Label(pointX2.getText()), new Label(pointY2.getText()));
        drawPane.getChildren().add(hBox);
    }
}
