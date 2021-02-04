package Algorithms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import math.CriticalP;
import math.CriticalPComparator;
import math.Point;

import java.util.ArrayList;

public class Varredura {
    int y_min;
    int y_max;

    public ArrayList<CriticalP> encontraBoundingBox(Point[] points) {
        y_min = Integer.MAX_VALUE;
        y_max = Integer.MIN_VALUE;
        ArrayList<CriticalP> criticals = new ArrayList<>();

        for(int i = 0; i < points.length; i++) {
            if(points[i].getY() < y_min) {
                y_min = points[i].getY();
            }
            if(points[i].getY() > y_max) {
                y_max = points[i].getY();
            }

            Point point = points[(i + 1) % points.length];
            if(points[i].getY() < point.getY()){
                criticals.add(new CriticalP(i, 1, points[i].getX(),
                        (point.getX()-points[i].getX() * 1.0f) / (point.getY()-points[i].getY() * 1.0f)));
            }
            point = points[(i - 1 + points.length) % points.length];
            if(points[i].getY() < point.getY()){
                criticals.add(new CriticalP(i, -1, points[i].getX(),
                        (point.getX()-points[i].getX() * 1.0f) / (point.getY()-points[i].getY() * 1.0f)));
            }
        }
        return criticals;
    }

    public void algorithm(Point[] points, GraphicsContext gc) {
        ArrayList<CriticalP> criticals = encontraBoundingBox(points);
        ArrayList<CriticalP> activeCriticalPs = new ArrayList<>();
        CriticalPComparator comparator = new CriticalPComparator();
        PixelWriter pw = gc.getPixelWriter();
        Color color = Color.rgb(168, 50, 50, 1.0);

        for(int y = y_min; y <= y_max; y++) {

            for(CriticalP e : activeCriticalPs) {
                e.x_intersection += e.inv_slope;
            }

            for(CriticalP e : criticals) {
                if(points[e.index].getY() == y) {
                    activeCriticalPs.add(e);
                }
            }

            for(int i = activeCriticalPs.size()-1; i >= 0; i--) {
                CriticalP e = activeCriticalPs.get(i);
                Point p_max = points[(e.index + e.dir + points.length) % points.length];
                if(p_max.getY() == y) {
                    activeCriticalPs.remove(i);
                }
            }

            activeCriticalPs.sort(comparator);

            for(int i = 0; i < activeCriticalPs.size(); i += 2) {
                int x_start = Math.round(activeCriticalPs.get(i).x_intersection);
                int x_end = Math.round(activeCriticalPs.get(i+1).x_intersection);
                for(int x = x_start; x <= x_end; x++) {
                    pw.setColor(x, y, color);
                }
            }
        }
    }
}
