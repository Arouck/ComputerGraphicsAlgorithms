package Algorithms;

import Exceptions.ExceptionHandler;
import Exceptions.ValueException;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import math.Point;

import java.util.ArrayList;
import java.util.List;

public class CircleAndEllipseAlgorithm {
    private int valueXCenter;
    private int valueYCenter;
    private int valueRadius;

    public List<Point> calculatePoints(Point center, int radius, List<Point> allPoints) {
        int x = 0;
        int y = radius;
        int e = -radius;
        Point point = new Point(x, y);
        add8Points(allPoints, point, center);
        while(x <= y) {
            e += 2*x + 1;
            x++;
            if(e >= 0) {
                e += 2*y - 2;
                y--;
            }
            point.setX(x);
            point.setY(y);
            add8Points(allPoints, point, center);
        }
        return allPoints;
    }

    public void add8Points(List<Point> allPoints, Point point, Point center) {
        allPoints.add(new Point(point.getX() + center.getX(), point.getY() + center.getY()));
        allPoints.add(new Point(point.getY() + center.getX(), point.getX() + center.getY()));
        allPoints.add(new Point(point.getY() + center.getX(), -point.getX() + center.getY()));
        allPoints.add(new Point(point.getX() + center.getX(), -point.getY() + center.getY()));
        allPoints.add(new Point(-point.getX() + center.getX(), -point.getY() + center.getY()));
        allPoints.add(new Point(-point.getY() + center.getX(), -point.getX() + center.getY()));
        allPoints.add(new Point(-point.getY() + center.getX(), point.getX() + center.getY()));
        allPoints.add(new Point(-point.getX() + center.getX(), point.getY() + center.getY()));
    }

    public void verifyValueException(TextField pointXCenter, TextField pointYCenter, TextField radius,
                                     Canvas drawCanvas) throws ValueException {
        int maxWidth = (int) drawCanvas.getWidth()/2;
        int maxHeight = (int) drawCanvas.getHeight()/2;

        this.setValueRadius(Integer.parseInt(radius.getText()));
        this.setValueXCenter(Integer.parseInt(pointXCenter.getText()));
        this.setValueYCenter(Integer.parseInt(pointYCenter.getText()));

        ExceptionHandler.circleValuesException(valueRadius, valueXCenter, valueYCenter, maxWidth, maxHeight);
    }

    public List<Point> start() {
        Point center = new Point(valueXCenter, valueYCenter);
        List<Point> allPoints = new ArrayList<>();
        calculatePoints(center, valueRadius, allPoints);

        return allPoints;
    }

    public int getValueXCenter() {
        return valueXCenter;
    }

    public void setValueXCenter(int valueXCenter) {
        this.valueXCenter = valueXCenter;
    }

    public int getValueYCenter() {
        return valueYCenter;
    }

    public void setValueYCenter(int valueYCenter) {
        this.valueYCenter = valueYCenter;
    }

    public int getValueRadius() {
        return valueRadius;
    }

    public void setValueRadius(int valueRadius) {
        this.valueRadius = valueRadius;
    }
}
