package math;

import static java.lang.Math.abs;

public class BresenhamAlgorithm {

    public Boolean[] doReflection(Point point1, Point point2) {
        Boolean switchXY = false;
        Boolean switchX = false;
        Boolean switchY = false;

        Float m = calculateM(point1, point2);

        if((m > 1) || (m < -1)) {
            Integer temp = point1.getX();
            point1.setX(point2.getX());
            point2.setX(temp);

            temp = point1.getY();
            point1.setY(point2.getY());
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

    public Float calculateM(Point point1, Point point2) {
        Float m = (float)  abs(point2.getY() - point1.getY()) / abs(point2.getX() - point1.getX());
        return m;
    }

    public Float calculateE(float m) {
        Float e = (float) (m - 0.5);
        return e;
    }
}
