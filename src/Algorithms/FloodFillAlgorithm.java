package Algorithms;

import Exceptions.ExceptionHandler;
import Exceptions.ValueException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import math.Calculator;
import math.Point;
import math.SignalInverter;

import java.util.*;

public class FloodFillAlgorithm {

    private int xMaxValue;
    private int xMinValue;
    private int yMaxValue;
    private int yMinValue;

    private int valueX1;
    private int valueY1;
    private int valueX2;
    private int valueY2;

    public void calculatePoints(List<Point> allPoints, int x, int y, List<Point> paintedPoints, GraphicsContext gc,
                                Color color) {
        if(paintedPoints == null) {
            paintedPoints = new ArrayList<>();
        }
        boolean painted = false;
        Point point = new Point(x, y);
        for(Point edgePoint : allPoints) {
            if (edgePoint.equals(point)) {
                painted = true;
                break;
            }
        }
        if(!painted) {
            for(Point paintedPoint : paintedPoints) {
                if (paintedPoint.equals(point)) {
                    painted = true;
                    break;
                }
            }
        }
        if(!painted) {
            paintedPoints.add(point);
            PixelWriter pixelWriter = gc.getPixelWriter();
            pixelWriter.setColor(point.getX(), point.getY(), color);
            calculatePoints(allPoints, x+1, y, paintedPoints, gc, color);
            calculatePoints(allPoints, x, y+1, paintedPoints, gc, color);
            calculatePoints(allPoints, x-1, y, paintedPoints, gc, color);
            calculatePoints(allPoints, x, y-1, paintedPoints, gc, color);
        }
    }

    public void colorPoints(int x, int y, List<Point> allPoints, List<Point> paintedPoints, GraphicsContext gc,
                            Color color) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(x, y));
        PixelWriter pw = gc.getPixelWriter();
        while(!points.isEmpty()) {
            paintedPoints.add(points.get(points.size() - 1));
            Point point = points.remove(points.size() - 1);
            pw.setColor(point.getX(), point.getY(), color);

            if(!checkValidity(point.getX() + 1, point.getY(), allPoints, paintedPoints)){
                points.add(new Point(point.getX() + 1, point.getY()));
            }
            if(!checkValidity(point.getX(), point.getY() + 1, allPoints, paintedPoints)){
                points.add(new Point(point.getX(), point.getY() + 1));
            }
            if(!checkValidity(point.getX(), point.getY() - 1, allPoints, paintedPoints)){
                points.add(new Point(point.getX(), point.getY() - 1));
            }
            if(!checkValidity(point.getX() - 1, point.getY(), allPoints, paintedPoints)){
                points.add(new Point(point.getX() - 1, point.getY()));
            }
        }
    }

    public boolean checkValidity(int x, int y, List<Point> allPoints, List<Point> paintedPoints) {
        Point point = new Point(x, y);
        boolean painted = false;
        for(Point edgePoint : allPoints) {
            if (edgePoint.equals(point)) {
                painted = true;
                break;
            }
        }
        if(!painted) {
            for(Point paintedPoint : paintedPoints) {
                if (paintedPoint.equals(point)) {
                    painted = true;
                    break;
                }
            }
        }

        return painted;
    }

    public void verifyValueException(TextField pointX1, TextField pointY1, TextField pointX2,
                                TextField pointY2, Canvas drawCanvas) throws ValueException {
    }

    public void start(List<Point> allPoints, Point chosenPoint, GraphicsContext gc) {
        PixelWriter pixelWriter = gc.getPixelWriter();
        Color color = Color.rgb(0, 0, 0, 1.0);
        for(Point point : allPoints) {
           pixelWriter.setColor(point.getX(), point.getY(), color);
        }
        Color paintedColor = Color.rgb(168, 50, 50, 1.0);
        calculatePoints(allPoints, chosenPoint.getX(), chosenPoint.getY(), null, gc, paintedColor);
        //colorPoints(chosenPoint.getX(), chosenPoint.getY(), allPoints, new ArrayList<>(), gc, paintedColor);
    }

    public int getxMaxValue() {
        return xMaxValue;
    }

    public void setxMaxValue(int xMaxValue) {
        this.xMaxValue = xMaxValue;
    }

    public int getxMinValue() {
        return xMinValue;
    }

    public void setxMinValue(int xMinValue) {
        this.xMinValue = xMinValue;
    }

    public int getyMaxValue() {
        return yMaxValue;
    }

    public void setyMaxValue(int yMaxValue) {
        this.yMaxValue = yMaxValue;
    }

    public int getyMinValue() {
        return yMinValue;
    }

    public void setyMinValue(int yMinValue) {
        this.yMinValue = yMinValue;
    }

    public int getValueX1() {
        return valueX1;
    }

    public void setValueX1(int valueX1) {
        this.valueX1 = valueX1;
    }

    public int getValueY1() {
        return valueY1;
    }

    public void setValueY1(int valueY1) {
        this.valueY1 = valueY1;
    }

    public int getValueX2() {
        return valueX2;
    }

    public void setValueX2(int valueX2) {
        this.valueX2 = valueX2;
    }

    public int getValueY2() {
        return valueY2;
    }

    public void setValueY2(int valueY2) {
        this.valueY2 = valueY2;
    }
}
