package Controllers;

import Algorithms.BresenhamAlgorithm;
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

public class BresenhamController {

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
    public void printPoints() {
        try {
            BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
            bresenhamAlgorithm.verifyValueException(pointX1, pointY1, pointX2, pointY2, drawCanvas);
            List<Point> linePoints = bresenhamAlgorithm.start();

            Color color = Color.rgb(0, 0, 0, 1.0);
            drawPoints(color, linePoints);
        } catch(ValueException ex) {
            ex.printStackTrace();
        }
    }

    public void drawPoints(Color color, List<Point> points) {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();

        for(Point point : points) {
            pw.setColor(point.getX(), point.getY(), color);
            allPoints.add(point);
        }
    }

    @FXML
    public void cleanPoints() {
        List<Point> copyAllPoints = new ArrayList<>(allPoints);
        Color color = Color.rgb(255, 255, 255, 1.0);

        for(Point point : copyAllPoints) {
            drawPoints(color, copyAllPoints);
            allPoints.remove(point);
        }
    }
}
