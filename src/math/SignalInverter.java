package math;

public class SignalInverter {
    public static void invertSignal(Point p1, Point p2) {
        if(p1.getX() < 0) {
            p1.setX(-p1.getX());
        }

        if(p2.getX() < 0) {
            p2.setX(-p2.getX());
        }

        if(p1.getY() < 0) {
            p1.setY(-p1.getY());
        }

        if(p2.getY() < 0) {
            p2.setY(-p2.getY());
        }
    }
}
