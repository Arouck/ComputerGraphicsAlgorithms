package Controllers;

import Algorithms.BresenhamAlgorithm;
import Algorithms.CircleAndEllipseAlgorithm;
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

public class CircleController {

    @FXML
    private TextField pointXc;
    @FXML
    private TextField pointYc;
    @FXML
    private TextField radius;
    @FXML
    private Canvas drawCanvas;
    @FXML
    private Button backButton;
    @FXML
    private Label maxValue;

    private List<Point> allPoints = new ArrayList<>();;

    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        controllerUtils.setMaxValue(drawCanvas, maxValue);
    }

    @FXML
    public void printPoints() {
        try {
            CircleAndEllipseAlgorithm circleAndEllipseAlgorithm = new CircleAndEllipseAlgorithm();
            circleAndEllipseAlgorithm.verifyValueException(pointXc, pointYc, radius, drawCanvas);
            List<Point> allPoints = circleAndEllipseAlgorithm.start();

            Color color = Color.rgb(0, 0, 0, 1.0);
            drawPoints(color, allPoints);
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
