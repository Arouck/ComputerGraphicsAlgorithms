package Controllers;

import Algorithms.BresenhamAlgorithm;
import Algorithms.RecorteDeLinha;
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

public class RecorteDeLinhaController {

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

    private ArrayList<Point> allVertices;


    @FXML
    public void initialize() {
        ControllerUtils controllerUtils = new ControllerUtils();
        controllerUtils.setBackButtonActionListener(backButton);
        xMaxValue.setText(String.valueOf(501));
        xMinValue.setText(String.valueOf(167));
        yMaxValue.setText(String.valueOf(410));
        yMinValue.setText(String.valueOf(137));
        allVertices = new ArrayList<>();

        calculatePoints(new Point(Integer.parseInt(xMaxValue.getText()), Integer.parseInt(yMaxValue.getText())),
                new Point(Integer.parseInt(xMaxValue.getText()), Integer.parseInt(yMinValue.getText())));
        calculatePoints(new Point(Integer.parseInt(xMaxValue.getText()), Integer.parseInt(yMinValue.getText())),
                new Point(Integer.parseInt(xMinValue.getText()), Integer.parseInt(yMinValue.getText())));
        calculatePoints(new Point(Integer.parseInt(xMinValue.getText()), Integer.parseInt(yMinValue.getText())),
                new Point(Integer.parseInt(xMinValue.getText()), Integer.parseInt(yMaxValue.getText())));
        calculatePoints(new Point(Integer.parseInt(xMinValue.getText()), Integer.parseInt(yMaxValue.getText())),
                new Point(Integer.parseInt(xMaxValue.getText()), Integer.parseInt(yMaxValue.getText())));

        drawPoints(Color.rgb(0, 0, 0, 1.0), allVertices);
    }

    public void calculatePoints(Point p1, Point p2) {
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
        List<Point> linePoints = new ArrayList<>();
        bresenhamAlgorithm.calculateLinePoints(p1, p2, linePoints);

        allVertices.addAll(linePoints);
    }

    @FXML
    public void drawLine() {
        Point p1 = new Point(Integer.parseInt(pointX1.getText()), Integer.parseInt(pointY1.getText()));
        Point p2 = new Point(Integer.parseInt(pointX2.getText()), Integer.parseInt(pointY2.getText()));
        int x_max = Integer.parseInt(xMaxValue.getText());
        int x_min = Integer.parseInt(xMinValue.getText());
        int y_max = Integer.parseInt(yMaxValue.getText());
        int y_min = Integer.parseInt(yMinValue.getText());
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();

        RecorteDeLinha algorithm = new RecorteDeLinha();
        algorithm.clip(p1, p2, x_max, x_min, y_max, y_min, gc, Color.rgb(52, 119, 235, 1.0));
    }

    public void drawPoints(Color color, List<Point> points) {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();

        for(Point point : points) {
            pw.setColor(point.getX(), point.getY(), color);
        }
    }
}
