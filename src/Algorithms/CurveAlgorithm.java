package Algorithms;

import Exceptions.ExceptionHandler;
import Exceptions.ValueException;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import math.Point;

import java.util.ArrayList;
import java.util.List;

public class CurveAlgorithm {

    private int maxWidth;
    private int maxHeight;
    private int minHeigh;
    private int minWidth;

    public Point bezierPoint(int n, Point[] controlPoints, double t) {
        Point[] points = new Point[n];
        System.arraycopy(controlPoints, 0, points, 0, n);
        n--;
        for(int r = 1; r <= n; r++){
            for(int i = 0; i <= n-r; i++) {
                points[i] = sum(multiply((1 - t), points[i]), multiply(t, points[i+1]));
            }
        }
        return points[0];
    }

    public Point multiply(double t, Point point){
        return new Point((int) Math.round(point.getX() * t),(int) Math.round(point.getY() * t));
    }

    public Point sum(Point p1, Point p2) {
        return new Point(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }

    /*public void verifyValueException() throws ValueException {
    }*/

    public List<Point> start(int n, Point[] controlPoints) {

        List<Point> allPoints = new ArrayList<>();
        Point aux = new Point(controlPoints[0].getX(), controlPoints[0].getY());
        BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();

        for(double t = 0; t <= 1; t+=0.1) {
            List<Point> linePoints = new ArrayList<>();
            Point nextPoint = bezierPoint(n, controlPoints, t);
            bresenhamAlgorithm.calculateLinePoints(aux, nextPoint, linePoints);
            aux = nextPoint;
            allPoints.addAll(linePoints);
        }

        return allPoints;
    }

    /*public int getValueXCenter() {
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
    }*/

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMinHeigh() {
        return minHeigh;
    }

    public void setMinHeigh(int minHeigh) {
        this.minHeigh = minHeigh;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }
}
