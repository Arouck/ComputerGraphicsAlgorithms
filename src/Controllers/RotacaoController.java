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

import java.util.ArrayList;
import java.util.List;

public class RotacaoController {

    @FXML
    private TextField pointX1;
    @FXML
    private TextField pointY1;
    @FXML
    private TextField pointX2;
    @FXML
    private TextField pointXPivo;
    @FXML
    private TextField pointYPivo;
    @FXML
    private TextField pointY2;
    @FXML
    private TextField angulo;
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
    public void aplicarRotacao() {
        double rotacao = Double.parseDouble(angulo.getText());
        double angulo = Math.toRadians(rotacao);

        Point pivo = new Point(Integer.parseInt(pointXPivo.getText()),
                Integer.parseInt(pointYPivo.getText()));

        cleanPoints();

        int x = pivo.getX();
        int y = pivo.getY();

        for(int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setX(vertices.get(i).getX() - x);
            vertices.get(i).setY(vertices.get(i).getY() - y);

            double xRotacao = (vertices.get(i).getX() * Math.cos(angulo)) - (vertices.get(i).getY() * Math.sin(angulo));
            double yRotacao = (vertices.get(i).getX() * Math.sin(angulo)) + (vertices.get(i).getY() * Math.cos(angulo));

            vertices.get(i).setX((int) Math.round(xRotacao));
            vertices.get(i).setY((int) Math.round(yRotacao));

            vertices.get(i).setX(vertices.get(i).getX() + x);
            vertices.get(i).setY(vertices.get(i).getY() + y);
        }

        for(int i = 0; i < vertices.size() - 1; i++) {
            calculatePoints2(vertices.get(i), vertices.get(i + 1));
        }

        drawLines();
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
