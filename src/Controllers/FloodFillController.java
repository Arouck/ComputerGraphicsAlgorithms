package Controllers;

import Algorithms.BresenhamAlgorithm;
import Algorithms.CurveAlgorithm;
import Algorithms.FloodFillAlgorithm;
import Exceptions.ValueException;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import math.Point;

import java.util.ArrayList;
import java.util.List;

public class FloodFillController {

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
    @FXML
    private TextField fillPointX;
    @FXML
    private TextField fillPointY;

    private List<Point> allLinePoints;

    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        controllerUtils.setMaxAndMinValues2(drawCanvas, xMaxValue, xMinValue, yMaxValue, yMinValue);
        allLinePoints = new ArrayList<>();
    }

    @FXML
    public void calculatePoints() {
        Point p1 = new Point(Integer.parseInt(pointX1.getText()), Integer.parseInt(pointY1.getText()));
        Point p2 = new Point(Integer.parseInt(pointX2.getText()), Integer.parseInt(pointY2.getText()));
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
        List<Point> linePoints = new ArrayList<>();
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        resetTextFields(pointX2.getText(), pointY2.getText(), true);

        allLinePoints.addAll(linePoints);
    }

    @FXML
    public void drawPoints() {
        FloodFillAlgorithm algorithm = new FloodFillAlgorithm();
        Point chosenPoint = new Point(Integer.parseInt(fillPointX.getText()), Integer.parseInt(fillPointY.getText()));
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        algorithm.start(allLinePoints, chosenPoint, gc);
    }

    public void resetTextFields(String pointx1, String pointy1, boolean disabled){
        pointX1.setText(pointx1);
        pointY1.setText(pointy1);
        pointX1.setDisable(disabled);
        pointY1.setDisable(disabled);
        pointX2.setText("");
        pointY2.setText("");
    }
}
