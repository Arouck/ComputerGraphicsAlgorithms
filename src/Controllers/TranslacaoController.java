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

public class TranslacaoController {

    @FXML
    private TextField pointX1;
    @FXML
    private TextField pointY1;
    @FXML
    private TextField pointX2;
    @FXML
    private TextField pointY2;
    @FXML
    private TextField pointXTranslacao;
    @FXML
    private TextField pointYTranslacao;
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
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        allPoints.addAll(linePoints);

        resetTextFields(pointX2.getText(), pointY2.getText());
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
    public void aplicarTranslacao() {
        Point translacao = new Point(Integer.parseInt(pointXTranslacao.getText()),
                Integer.parseInt(pointYTranslacao.getText()));

        cleanPoints();
        for(Point point : allPoints) {
            point.setX(point.getX() + translacao.getX());
            point.setY(point.getY() + translacao.getY());
        }
        drawLines();
    }

    public void cleanPoints() {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        List<Point> copyAllPoints = new ArrayList<>(allPoints);
        Color color = Color.rgb(255, 255, 255, 1.0);

        for(Point point : copyAllPoints) {
            pw.setColor(point.getX(), point.getY(), color);
        }
    }
}
