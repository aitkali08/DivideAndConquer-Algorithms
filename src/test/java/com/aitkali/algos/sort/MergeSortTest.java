package test.java.com.aitkali.algos.sort;

import main.java.com.aitkali.algos.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    public void randomArraysSorted() {
        Random rnd = new Random(42);
        for (int t=0;t<50;t++){
            int n = rnd.nextInt(1000);
            int[] a = new int[n];
            for (int i=0;i<n;i++) a[i] = rnd.nextInt(10000);
            Metrics m = new Metrics();
            main.java.com.aitkali.algos.sort.MergeSort.sort(a,m);
            for (int i=1;i<n;i++) assertTrue(a[i-1] <= a[i]);
        }
    }
}
