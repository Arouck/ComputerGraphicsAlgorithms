package Controllers;

import Algorithms.BresenhamAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import math.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscalaController {

    @FXML
    private TextField pointX1;
    @FXML
    private TextField pointY1;
    @FXML
    private TextField pointX2;
    @FXML
    private TextField pointY2;
    @FXML
    private TextField escalaX;
    @FXML
    private TextField escalaY;
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

    private List<Point> vertices = new ArrayList<>();

    private List<Point> newVertices = new ArrayList<>();

    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        controllerUtils.setMaxAndMinValues2(drawCanvas, xMaxValue, xMinValue, yMaxValue, yMinValue);
    }

    @FXML
    public void calculatePoints() {
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
        List<Point> linePoints = new ArrayList<>();
        Point p1 = new Point(
                Integer.parseInt(pointX1.getText()),
                Integer.parseInt(pointY1.getText())
        );
        Point p2 = new Point(
                Integer.parseInt(pointX2.getText()),
                Integer.parseInt(pointY2.getText())
        );
        vertices.add(p1);
        vertices.add(p2);
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        allPoints.addAll(linePoints);

        resetTextFields(pointX2.getText(), pointY2.getText());
    }

    public void calculatePoints2(Point p1, Point p2) {
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
        List<Point> linePoints = new ArrayList<>();

        newVertices.add(p1);
        newVertices.add(p2);
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        allPoints.addAll(linePoints);
    }

    public void resetTextFields(String pointx1, String pointy1){
        pointX1.setText(pointx1);
        pointY1.setText(pointy1);
        pointX1.setDisable(true);
        pointY1.setDisable(true);
        pointX2.setText("");
        pointY2.setText("");
    }

    @FXML
    public void drawLines() {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        Color color = Color.rgb(0, 0, 0, 1.0);

        for(Point point : allPoints) {
            pw.setColor(point.getX(), point.getY(), color);
        }
    }

    @FXML
    public void aplicarEscala() {
        double x = Double.parseDouble(escalaX.getText());
        double y = Double.parseDouble(escalaY.getText());

        cleanPoints();
        ArrayList<Point> oldVertices = new ArrayList<>(vertices);
        for(int i = 0; i < vertices.size() - 1; i++) {
            Point p1 = new Point((int) Math.round(vertices.get(i).getX() * x),
                    (int) Math.round(vertices.get(i).getY() * y));
            Point p2 = new Point((int) Math.round(vertices.get(i + 1).getX() * x),
                    (int) Math.round(vertices.get(i + 1).getY() * y));
            calculatePoints2(p1, p2);
        }
        drawLines();
        vertices.removeAll(oldVertices);
        vertices.addAll(newVertices);
        newVertices.removeAll(vertices);
    }

    public void cleanPoints() {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        ArrayList<Point> copyPoints = new ArrayList<>(allPoints);
        Color color = Color.rgb(255, 255, 255, 1.0);

        for(Point point : copyPoints) {
            pw.setColor(point.getX(), point.getY(), color);
        }

        allPoints.removeAll(copyPoints);
    }
}
