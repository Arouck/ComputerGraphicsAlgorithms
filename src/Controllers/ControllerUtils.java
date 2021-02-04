package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtils {

    public void setBackButtonActionListener(Button backButton) {
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../resources/main.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Computer Graphics Algorithms");
                    stage.setScene(new Scene(root, 400, 300));
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  void setMaxAndMinValues(Canvas drawCanvas, Label xMaxValue, Label xMinValue,
                                    Label yMaxValue, Label yMinValue) {
        xMaxValue.setText(String.valueOf(drawCanvas.getWidth()/2));
        xMinValue.setText(String.valueOf(-drawCanvas.getWidth()/2));
        yMaxValue.setText(String.valueOf(drawCanvas.getHeight()/2));
        yMinValue.setText(String.valueOf(-drawCanvas.getHeight()/2));
    }

    public  void setMaxAndMinValues2(Canvas drawCanvas, Label xMaxValue, Label xMinValue,
                                    Label yMaxValue, Label yMinValue) {
        xMaxValue.setText(String.valueOf(drawCanvas.getWidth()));
        xMinValue.setText(String.valueOf(0));
        yMaxValue.setText(String.valueOf(drawCanvas.getHeight()));
        yMinValue.setText(String.valueOf(0));
    }

   /* public  void setMaxValue(Canvas drawCanvas, Label xMaxValue, Label yMaxValue, Label yMinValue, Label xMinValue) {
        xMaxValue.setText(String.valueOf(drawCanvas.getWidth()/2));
        xMinValue.setText("-"+xMaxValue.getText());
        yMaxValue.setText(String.valueOf(drawCanvas.getHeight()/2));
        yMinValue.setText("-"+yMaxValue.getText());
    }*/
}
