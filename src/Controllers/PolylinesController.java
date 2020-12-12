package Controllers;

import Algorithms.BresenhamAlgorithm;
import Exceptions.ExceptionHandler;
import Exceptions.ValueException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import math.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PolylinesController {

    @FXML
    private TextField pointX1;
    @FXML
    private TextField pointY1;
    @FXML
    private TextField pointX2;
    @FXML
    private TextField pointY2;
    @FXML
    private Canvas drawCanvas;
    @FXML
    private Button backButton;
    @FXML
    private Label xMaxValue;
    @FXML
    private Label xMinValue;
    @FXML
    private Label yMaxValue;
    @FXML
    private Label yMinValue;

    private List<Point> allPoints = new ArrayList<>();;

    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        controllerUtils.setMaxAndMinValues(drawCanvas, xMaxValue, xMinValue, yMaxValue, yMinValue);
    }

    @FXML
    public void calculatePoints() {
        try {
            BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
            bresenhamAlgorithm.verifyValueException(pointX1, pointY1, pointX2, pointY2, drawCanvas);
            List<Point> linePoints = bresenhamAlgorithm.start();

            allPoints.addAll(linePoints);

            resetTextFields(pointX2.getText(), pointY2.getText(), true);

        } catch(ValueException ex) {
            ex.printStackTrace();
        }
    }

    public void resetTextFields(String pointx1, String pointy1, boolean disabled){
        pointX1.setText(pointx1);
        pointY1.setText(pointy1);
        pointX1.setDisable(disabled);
        pointY1.setDisable(disabled);
        pointX2.setText("");
        pointY2.setText("");
    }

    @FXML
    public void drawLines() {
        calculatePoints();
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        Color color = Color.rgb(0, 0, 0, 1.0);

        for(Point point : allPoints) {
            pw.setColor(point.getX(), point.getY(), color);
        }
    }

    @FXML
    public void cleanPoints() {
        resetTextFields("", "", false);
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        List<Point> copyAllPoints = new ArrayList<>(allPoints);
        Color color = Color.rgb(255, 255, 255, 1.0);

        for(Point point : copyAllPoints) {
            pw.setColor(point.getX(), point.getY(), color);
            allPoints.remove(point);
        }
    }
}
