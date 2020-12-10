package math;

import java.util.List;

import static java.lang.Math.abs;

public class Calculator {

    public Boolean[] doReflection(Point point1, Point point2) {
        boolean switchXY = false;
        boolean switchX = false;
        boolean switchY = false;

        Float m = calculateM(point1, point2);

        if((m > 1) || (m < -1)) {
            int temp = point1.getX();
            point1.setX(point1.getY());
            point1.setY(temp);

            temp = point2.getX();
            point2.setX(point2.getY());
            point2.setY(temp);

            switchXY = true;
        }

        if(point1.getX() > point2.getX()) {
            point1.setX(point1.getX() * -1);
            point2.setX(point2.getX() * -1);

            switchX = true;
        }

        if(point1.getY() > point2.getY()) {
            point1.setY(point1.getY() * -1);
            point2.setY(point2.getY() * -1);

            switchY = true;
        }

        return new Boolean[] {switchXY, switchX, switchY};
    }

    public void undoReflection(List<Point> allPoints, Boolean[] switches) {
        if(switches[2]) {
            for(Point point : allPoints) {
                point.setY(-point.getY());
            }
        }

        if(switches[1]) {
            for(Point point : allPoints) {
                point.setX(-point.getX());
            }
        }

        if(switches[0]) {
            for (Point point : allPoints) {
                int temp = point.getX();
                point.setX(point.getY());
                point.setY(temp);
            }
        }
    }

    public Float calculateM(Point point1, Point point2) {
        return (float) abs(point2.getY() - point1.getY()) / abs(point2.getX() - point1.getX());
    }

    public Float calculateE(float m) {
        return (float) (m - 0.5);
    }
}
