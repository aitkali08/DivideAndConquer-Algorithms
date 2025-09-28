package main.java.com.aitkali.algos.util;

public final class Point {
    public final double x, y;
    public Point(double x, double y){ this.x = x; this.y = y; }
    public double dist(Point o){
        double dx = x - o.x, dy = y - o.y;
        return Math.hypot(dx, dy);
    }
}
