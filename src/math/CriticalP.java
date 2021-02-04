package math;

public class CriticalP {
    public int index;
    public int dir;
    public float x_intersection;
    public float inv_slope;

    public CriticalP(int index, int dir, float x_intersection, float inv_slope) {
        this.index = index;
        this.dir = dir;
        this.x_intersection = x_intersection;
        this.inv_slope = inv_slope;
    }
}
