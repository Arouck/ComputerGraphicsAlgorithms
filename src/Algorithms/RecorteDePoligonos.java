package Algorithms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import math.Point;

import java.util.ArrayList;

public class RecorteDePoligonos {
    public int x_min;
    public int x_max;
    public int y_min;
    public int y_max;

    public ArrayList<Point> clip(Point[] polygon, int x_max, int x_min, int y_max, int y_min) {
        ArrayList<Point> newPolygon = new ArrayList<>();
        for(int i = 0; i < polygon.length; i++) {
            Point p1 = polygon[i];
            Point p2 = polygon[(i + 1) % polygon.length];

            if(p1.getX() >= x_min) {
                if(p2.getX() >= x_min) {
                    newPolygon.add(p2);
                } else {
                    int y_int = (int) (((x_min - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX() + 0.00001)) + p1.getY());
                    newPolygon.add(
                            new Point(x_min, y_int)
                    );
                }
            } else {
                if(p2.getX() >= x_min) {
                    int y_int = (int) (((x_min - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX() + 0.00001)) + p1.getY());
                    newPolygon.add(
                            new Point(x_min, y_int)
                    );
                    newPolygon.add(p2);
                }
            }
        }

        ArrayList<Point> newPolygon2 = new ArrayList<>();
        for(int i = 0; i < newPolygon.size(); i++) {
            Point p1 = newPolygon.get(i);
            Point p2 = newPolygon.get((i + 1) % newPolygon.size());

            if(p1.getX() <= x_max) {
                if(p2.getX() <= x_max) {
                    newPolygon2.add(p2);
                } else {
                    int y_int = (int) (((x_max - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX() + 0.00001)) + p1.getY());
                    newPolygon2.add(
                            new Point(x_max, y_int)
                    );
                }
            } else {
                if(p2.getX() <= x_max) {
                    int y_int = (int) (((x_max - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX() + 0.00001)) + p1.getY());
                    newPolygon2.add(
                            new Point(x_max, y_int)
                    );
                    newPolygon2.add(p2);
                }
            }
        }

        ArrayList<Point> newPolygon3 = new ArrayList<>();
        for(int i = 0; i < newPolygon2.size(); i++) {
            Point p1 = newPolygon2.get(i);
            Point p2 = newPolygon2.get((i + 1) % newPolygon2.size());

            if(p1.getY() >= y_min) {
                if(p2.getY() >= y_min) {
                    newPolygon3.add(p2);
                } else {
                    int x_int = (int) (((y_min - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY() + 0.00001)) + p1.getX());
                    newPolygon3.add(
                            new Point(x_int, y_min)
                    );
                }
            } else {
                if(p2.getY() >= y_min) {
                    int x_int = (int) (((y_min - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY() + 0.00001)) + p1.getX());
                    newPolygon3.add(
                            new Point(x_int, y_min)
                    );
                    newPolygon3.add(p2);
                }
            }
        }

        ArrayList<Point> newPolygon4 = new ArrayList<>();
        for(int i = 0; i < newPolygon3.size(); i++) {
            Point p1 = newPolygon3.get(i);
            Point p2 = newPolygon3.get((i + 1) % newPolygon3.size());

            if(p1.getY() <= y_max) {
                if(p2.getY() <= y_max) {
                    newPolygon4.add(p2);
                } else {
                    int x_int = (int) (((y_max - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY() + 0.00001)) + p1.getX());
                    newPolygon4.add(
                            new Point(x_int, y_max)
                    );
                }
            } else {
                if(p2.getY() <= y_max) {
                    int x_int = (int) (((y_max - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY() + 0.00001)) + p1.getX());
                    newPolygon4.add(
                            new Point(x_int, y_max)
                    );
                    newPolygon4.add(p2);
                }
            }
        }

        return newPolygon4;
    }
}
