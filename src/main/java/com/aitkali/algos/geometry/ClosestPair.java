package main.java.com.aitkali.algos.geometry;

import main.java.com.aitkali.algos.util.Point;
import main.java.com.aitkali.algos.metrics.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closest(Point[] pts, Metrics m) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        Point[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p -> p.x));
        Point[] byY = pts.clone();
        Arrays.sort(byY, Comparator.comparingDouble(p -> p.y));
        m.incAllocs(2);
        return rec(byX, byY, 0, pts.length - 1, m);
    }

    private static double rec(Point[] byX, Point[] byY, int lo, int hi, Metrics m) {
        m.enterRec();
        try {
            int n = hi - lo + 1;
            if (n <= 3) {
                double best = Double.POSITIVE_INFINITY;
                for (int i = lo; i <= hi; i++) {
                    for (int j = i + 1; j <= hi; j++) {
                        best = Math.min(best, byX[i].dist(byX[j]));
                        m.incComp(1);
                    }
                }
                return best;
            }
            int mid = (lo + hi) >> 1;
            double midX = byX[mid].x;

            // build left/right byY
            Point[] leftY = new Point[mid - lo + 1];
            Point[] rightY = new Point[hi - mid];
            int li = 0, ri = 0;
            for (Point p : byY) {
                if (p.x <= midX && li < leftY.length) leftY[li++] = p;
                else if (ri < rightY.length) rightY[ri++] = p;
            }
            m.incAllocs(leftY.length + rightY.length);

            double dl = rec(byX, leftY, lo, mid, m);
            double dr = rec(byX, rightY, mid + 1, hi, m);
            double d = Math.min(dl, dr);

            // strip: points within d of mid line, sorted by y (leftY/rightY merged order)
            Point[] strip = new Point[byY.length];
            int s = 0;
            for (Point p : byY) {
                if (Math.abs(p.x - midX) < d) strip[s++] = p;
            }
            m.incAllocs(s);

            for (int i = 0; i < s; i++) {
                for (int j = i + 1; j < s && (strip[j].y - strip[i].y) < d; j++) {
                    double dist = strip[i].dist(strip[j]);
                    m.incComp(1);
                    if (dist < d) d = dist;
                }
            }
            return d;
        } finally {
            m.exitRec();
        }
    }
}
