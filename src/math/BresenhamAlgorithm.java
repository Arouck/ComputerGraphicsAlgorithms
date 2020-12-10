package math;

import java.util.List;

public class BresenhamAlgorithm {

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
}
