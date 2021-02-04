package Controllers;

import Algorithms.BresenhamAlgorithm;
import Algorithms.FloodFillAlgorithm;
import Algorithms.Varredura;
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

public class ScanlineController {

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

    private List<Point> allVertices;

    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        controllerUtils.setMaxAndMinValues2(drawCanvas, xMaxValue, xMinValue, yMaxValue, yMinValue);
        allVertices = new ArrayList<>();
    }

    /*@FXML
    public void calculatePoints() {
        Point p1 = new Point(Integer.parseInt(pointX1.getText()), Integer.parseInt(pointY1.getText()));
        Point p2 = new Point(Integer.parseInt(pointX2.getText()), Integer.parseInt(pointY2.getText()));
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
        List<Point> linePoints = new ArrayList<>();
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        resetTextFields(pointX2.getText(), pointY2.getText(), true);

        allLinePoints.addAll(linePoints);
    }*/
    @FXML
    public void storePoints() {
        Point p1 = new Point(Integer.parseInt(pointX1.getText()), Integer.parseInt(pointY1.getText()));
        Point p2 = new Point(Integer.parseInt(pointX2.getText()), Integer.parseInt(pointY2.getText()));
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
        List<Point> linePoints = new ArrayList<>();
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        resetTextFields(pointX2.getText(), pointY2.getText(), true);

        drawPoints(Color.rgb(0,0,0,1.0), linePoints);
        //allVertices.add(p1);
        if(allVertices.isEmpty()){
            allVertices.add(p1);
        }

        if(!p2.equals(allVertices.get(0))) {
            allVertices.add(p2);
        }
    }

    @FXML
    public void drawPointsScanline() {
        Varredura varredura = new Varredura();
        //Point chosenPoint = new Point(Integer.parseInt(fillPointX.getText()), Integer.parseInt(fillPointY.getText()));
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        //allVertices.remove(allVertices.size()-1);
        Point[] points = new Point[allVertices.size()];
        for(int i = 0; i < points.length; i++) {
            points[i] = allVertices.get(i);
        }
        varredura.algorithm(points, gc);
    }

    public void drawPoints(Color color, List<Point> points) {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();

        for(Point point : points) {
            pw.setColor(point.getX(), point.getY(), color);
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
}
