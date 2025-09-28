package test.java.com.aitkali.algos.geometry;

import main.java.com.aitkali.algos.metrics.Metrics;
import main.java.com.aitkali.algos.util.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {
    @Test
    public void bruteForceValidation() {
        for (int t=0;t<10;t++) {
            int n = 200;
            Point[] pts = new Point[n];
            for (int i=0;i<n;i++) pts[i] = new Point(Math.random()*1000, Math.random()*1000);
            Metrics m = new Metrics();
            double d = main.java.com.aitkali.algos.geometry.ClosestPair.closest(pts, m);
            // brute force
            double bf = Double.POSITIVE_INFINITY;
            for (int i=0;i<n;i++) for (int j=i+1;j<n;j++) bf = Math.min(bf, pts[i].dist(pts[j]));
            assertEquals(bf, d, 1e-9);
        }
    }
}
