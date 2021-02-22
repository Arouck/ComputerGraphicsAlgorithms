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

    public void start(List<Point> allPoints, Point chosenPoint, GraphicsContext gc) {
        PixelWriter pixelWriter = gc.getPixelWriter();
        Color color = Color.rgb(0, 0, 0, 1.0);
        for(Point point : allPoints) {
           pixelWriter.setColor(point.getX(), point.getY(), color);
        }
        Color paintedColor = Color.rgb(168, 50, 50, 1.0);
        calculatePoints(allPoints, chosenPoint.getX(), chosenPoint.getY(), null, gc, paintedColor);
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
