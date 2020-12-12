package Algorithms;

import Exceptions.ExceptionHandler;
import Exceptions.ValueException;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import math.Calculator;
import math.Point;
import math.SignalInverter;

import java.util.ArrayList;
import java.util.List;

public class BresenhamAlgorithm {

    private int xMaxValue;
    private int xMinValue;
    private int yMaxValue;
    private int yMinValue;

    private int valueX1;
    private int valueY1;
    private int valueX2;
    private int valueY2;

    public void calculatePoints(Point p1, Point p2, List<Point> allPoints) {

        SignalInverter.invertSignal(p1, p2);

        Calculator calculator = new Calculator();

        Boolean[] switches = calculator.doReflection(p1, p2);
        float m = calculator.calculateM(p1, p2);
        float e = calculator.calculateE(m);

        int x = p1.getX();
        int y = p1.getY();

        allPoints.add(p1);

        do {
            if(e >= 0) {
                y++;
                e--;
            }
            x++;
            e += m;
            allPoints.add(new Point(x, y));
        } while (x < p2.getX());

        calculator.undoReflection(allPoints, switches);
    }

    public void verifyValueException(TextField pointX1, TextField pointY1, TextField pointX2,
                                TextField pointY2, Canvas drawCanvas) throws ValueException {
        this.setValueX1(Integer.parseInt(pointX1.getText()));
        this.setValueY1(Integer.parseInt(pointY1.getText()));
        this.setValueX2(Integer.parseInt(pointX2.getText()));
        this.setValueY2(Integer.parseInt(pointY2.getText()));

        this.setxMaxValue((int) drawCanvas.getWidth()/2);
        this.setxMinValue((int) -drawCanvas.getWidth()/2);
        this.setyMaxValue((int) drawCanvas.getHeight()/2);
        this.setyMinValue((int) -drawCanvas.getHeight()/2);

        ExceptionHandler.valueException(valueX1, valueY1, valueX2, valueY2,
                xMaxValue, yMaxValue, xMinValue, yMinValue);
    }

    public List<Point> start() {
        Point p1 = new Point(valueX1 + xMaxValue,
                valueY1 + yMinValue);
        Point p2 = new Point(valueX2 + xMaxValue,
                valueY2 + yMinValue);

        List<Point> linePoints = new ArrayList<>();
        calculatePoints(p1, p2, linePoints);

        return linePoints;
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
