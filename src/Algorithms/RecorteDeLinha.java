package Algorithms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import math.Point;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class RecorteDeLinha {
    public int x_min;
    public int x_max;
    public int y_min;
    public int y_max;

    public boolean[] mkcode(Point point) {
        boolean[] bits = { false, false, false, false };
        if(point.getY() > y_max){
            bits[0] = true;
        } else if(point.getY() < y_min) {
            bits[1] = true;
        }
        if(point.getX() > x_max){
            bits[2] = true;
        } else if(point.getX() < x_min) {
            bits[3] = true;
        }

        return bits;
    }

    public int booleansToInt(boolean[] arr){
        int n = 0;
        for (boolean b : arr)
            n = (n << 1) | (b ? 1 : 0);
        return n;
    }

    /*public int findWindowLine(int diffIndex) {
        switch (diffIndex) {
            case 0:
                return y_max;

            case 1:
                return y_min;

            case 2:
                return  x_max;

            case 3:
                return x_min;

            default:
                return -1;
        }
    }*/

    public Point intersectLines(int diffIndex, Point p1, Point p2) {
        Point point = null;
        if(diffIndex == 0) {
            int x_int = ((y_max - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY())) + p1.getX();
            point = new Point(x_int, y_max);
        } else if(diffIndex == 1) {
            int x_int = ((y_min - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY())) + p1.getX();
            point = new Point(x_int, y_min);
        } else if(diffIndex == 2) {
            int y_int = ((x_max - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX())) + p1.getY();
            point = new Point(x_max, y_int);
        } else if(diffIndex == 3) {
            int y_int = ((x_min - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX())) + p1.getY();
            point = new Point(x_min, y_int);
        }
        return point;
    }

    public void drawLine(GraphicsContext gc, Color color, ArrayList<Point> allPoints) {
        PixelWriter pw = gc.getPixelWriter();
        for(Point point : allPoints) {
            pw.setColor(point.getX(), point.getY(), color);
        }
    }

    public void clip(Point p1, Point p2, int x_max, int x_min, int y_max, int y_min, GraphicsContext gc, Color color) {
        this.x_max = x_max;
        this.x_min = x_min;
        this.y_max = y_max;
        this.y_min = y_min;

        boolean[] c1Arr = mkcode(p1);
        boolean[] c2Arr = mkcode(p2);
        int c1 = booleansToInt(c1Arr);
        int c2 = booleansToInt(c2Arr);

        if((c1 | c2) == 0) {
            ArrayList<Point> allPoints = new ArrayList<>();
            BresenhamAlgorithm bresenhamAlgorithm = new BresenhamAlgorithm();
            bresenhamAlgorithm.calculateLinePoints(p1, p2, allPoints);
            drawLine(gc, color, allPoints);
        } else if((c1 & c2) != 0) {
            byte b = 0;
        } else {
            int diffIndex = -1;
            for(int i = 0; i < 4; i++) {
                if(c1Arr[i] | c2Arr[i]) {
                    diffIndex = i;
                    break;
                }
            }
            Point pi = intersectLines(diffIndex, p1, p2);

            if(!c1Arr[diffIndex]) {
                clip(p1, pi, x_max, x_min, y_max, y_min, gc, color);
            } else {
                clip(pi, p2, x_max, x_min, y_max, y_min, gc, color);
            }
            /*Point pm = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
            clip(p1, pm, x_max, x_min, y_max, y_min, gc, color);
            clip(pm, p2, x_max, x_min, y_max, y_min, gc, color);*/

        }
    }

    /*public static void main(String[] args) {
        boolean[] a = {false, true, false};
        boolean[] b = {false, false, true};
        boolean[] c = a | b;
    }*/
}
