package test.java.com.aitkali.algos.sort;

import main.java.com.aitkali.algos.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {
    @Test
    public void selectMatchesSort() {
        Random rnd = new Random(2);
        for (int t=0;t<100;t++){
            int n = 100 + rnd.nextInt(400);
            int[] a = new int[n];
            for (int i=0;i<n;i++) a[i] = rnd.nextInt(10000);
            int k = rnd.nextInt(n);
            Metrics m = new Metrics();
            int sel = main.java.com.aitkali.algos.sort.DeterministicSelect.select(a.clone(), k, m);
            int[] b = a.clone();
            Arrays.sort(b);
            assertEquals(b[k], sel);
        }
    }
}
