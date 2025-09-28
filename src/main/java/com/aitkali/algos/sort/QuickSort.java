package main.java.com.aitkali.algos.sort;

import main.java.com.aitkali.algos.metrics.Metrics;
import main.java.com.aitkali.algos.util.ArraysUtil;

import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        ArraysUtil.shuffle(a); // randomize to avoid worst-case
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            m.enterRec();
            try {
                int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
                int pivot = a[pivotIndex];
                // partition: three-way not needed, but we implement Hoare-style two-way
                int i = lo, j = hi;
                while (i <= j) {
                    while (i <= j) {
                        m.incComp(1);
                        if (a[i] < pivot) i++; else break;
                    }
                    while (i <= j) {
                        m.incComp(1);
                        if (a[j] > pivot) j--; else break;
                    }
                    if (i <= j) {
                        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                        m.incSwaps(1);
                        i++; j--;
                    }
                }
                // Recurse on smaller part, iterate on larger (tail recursion elimination)
                if (j - lo < hi - i) {
                    if (lo < j) quicksort(a, lo, j, m);
                    lo = i;
                } else {
                    if (i < hi) quicksort(a, i, hi, m);
                    hi = j;
                }
            } finally {
                m.exitRec();
            }
        }
    }
}
