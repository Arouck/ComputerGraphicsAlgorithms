package GUI;

import Exceptions.ValueException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import math.BresenhamAlgorithm;
import math.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Controller {

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

    private List<Point> allPoints;

    @FXML
    public void printPoints() {
        try {
            initAllPoints();

            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            int valueX1 = Integer.parseInt(pointX1.getText());
            int valueY1 = Integer.parseInt(pointY1.getText());
            int valueX2 = Integer.parseInt(pointX2.getText());
            int valueY2 = Integer.parseInt(pointY2.getText());

            int XMAXVALUE = (int) drawCanvas.getWidth()/2;
            int YMAXVALUE = (int) drawCanvas.getHeight()/2;
            int XMINVALUE = (int) -drawCanvas.getWidth()/2;
            int YMINVALUE = (int) -drawCanvas.getHeight()/2;

            ExceptionHandler.valueException(valueX1, valueY1, valueX2, valueY2,
                    XMAXVALUE, YMAXVALUE, XMINVALUE, YMINVALUE);

            BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();

            Point p1 = new Point(valueX1 + XMAXVALUE,
                    valueY1 + YMINVALUE);
            Point p2 = new Point(valueX2 + XMAXVALUE,
                    valueY2 + YMINVALUE);

            List<Point> linePoints = new ArrayList<>();

            bresenhamAlgorithm.calculatePoints(p1, p2, linePoints);

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
        initAllPoints();

        List<Point> copyAllPoints = new ArrayList<>(allPoints);
        Color color = Color.rgb(255, 255, 255, 1.0);

        for(Point point : copyAllPoints) {
            drawPoints(color, copyAllPoints);
            allPoints.remove(point);
        }
    }

    public void initAllPoints() {
        if (allPoints == null) {
            allPoints = new ArrayList<>();
        }
    }
}
