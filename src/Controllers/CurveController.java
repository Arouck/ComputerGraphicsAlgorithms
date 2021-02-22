package Controllers;

import Algorithms.BresenhamAlgorithm;
import Algorithms.CurveAlgorithm;
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

public class CurveController {

    @FXML
    private TextField pointX1;
    @FXML
    private TextField pointY1;
    @FXML
    private TextField pointX2;
    @FXML
    private TextField pointY2;
    @FXML
    private TextField pointXcontrol;
    @FXML
    private TextField pointYcontrol;
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

    private List<Point> allPoints = new ArrayList<>();
    private List<Point> controlPoints = new ArrayList<>();
    private Point initialPoint;
    private Point finalPoint;
    private Point controlPoint;

    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        controllerUtils.setMaxAndMinValues2(drawCanvas, xMaxValue, xMinValue, yMaxValue, yMinValue);
    }

    @FXML
    public void addControlPoints() {
        if(initialPoint == null) {
            initialPoint = new Point(Integer.parseInt(pointX1.getText()),
                    Integer.parseInt(pointY1.getText()));
            controlPoints.add(initialPoint);
        }
        if(controlPoint == null) {
            controlPoint = new Point(Integer.parseInt(pointXcontrol.getText()),
                    Integer.parseInt(pointYcontrol.getText()));
            Point aux = new Point(controlPoint.getX(), controlPoint.getY());
            controlPoints.add(aux);
        } else if (controlPoint.getX() != Integer.parseInt(pointXcontrol.getText()) ||
                controlPoint.getY() != Integer.parseInt(pointYcontrol.getText())){
            controlPoint.setX(Integer.parseInt(pointXcontrol.getText()));
            controlPoint.setY(Integer.parseInt(pointYcontrol.getText()));
            Point aux = new Point(controlPoint.getX(), controlPoint.getY());
            Point temp = controlPoints.get(controlPoints.size() - 1);
            controlPoints.set(controlPoints.size() - 1, aux);
            controlPoints.add(temp);
        }
        if(finalPoint == null) {
            finalPoint = new Point(Integer.parseInt(pointX2.getText()),
                    Integer.parseInt(pointY2.getText()));
            controlPoints.add(finalPoint);
        }
    }

    @FXML
    public void printPoints() {
        Point[] controlArray = new Point[controlPoints.size()];
        for(int i = 0; i < controlPoints.size(); i++) {
            controlArray[i] = controlPoints.get(i);
        }
        CurveAlgorithm curveAlgorithm = new CurveAlgorithm();
        List<Point> curvePoints = curveAlgorithm.start(controlPoints.size(), controlArray);

        Color color = Color.rgb(0, 0, 0, 1.0);
        drawPoints(color, curvePoints);
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
