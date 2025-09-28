package test.java.com.aitkali.algos.sort;

import main.java.com.aitkali.algos.metrics.Metrics;
import main.java.com.aitkali.algos.util.ArraysUtil;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void adversarialAndRandom() {
        Random rnd = new Random(1);
        int[] a = new int[1000];
        for (int i=0;i<a.length;i++) a[i] = rnd.nextInt();
        Metrics m = new Metrics();
        main.java.com.aitkali.algos.sort.QuickSort.sort(a,m);
        assertTrue(ArraysUtil.isSorted(a));
    }
}
