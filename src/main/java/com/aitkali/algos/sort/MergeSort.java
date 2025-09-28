package main.java.com.aitkali.algos.sort;

import main.java.com.aitkali.algos.metrics.Metrics;

public class MergeSort {
    private static final int INSERTION_CUTOFF = 32;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        int[] buf = new int[a.length];
        m.incAllocs(1);
        mergeSort(a, buf, 0, a.length - 1, m);
    }

    private static void mergeSort(int[] a, int[] buf, int lo, int hi, Metrics m) {
        m.enterRec();
        try {
            if (hi - lo + 1 <= INSERTION_CUTOFF) {
                insertionSort(a, lo, hi, m);
                return;
            }
            int mid = lo + ((hi - lo) >> 1);
            mergeSort(a, buf, lo, mid, m);
            mergeSort(a, buf, mid+1, hi, m);
            // if already ordered, skip merge
            if (a[mid] <= a[mid+1]) {
                m.incComp(1);
                return;
            }
            merge(a, buf, lo, mid, hi, m);
        } finally {
            m.exitRec();
        }
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        System.arraycopy(a, lo, buf, lo, hi - lo + 1);
        m.incAllocs(hi - lo + 1);
        int i = lo, j = mid+1, k = lo;
        while (i <= mid && j <= hi) {
            m.incComp(1);
            if (buf[i] <= buf[j]) a[k++] = buf[i++];
            else { a[k++] = buf[j++]; m.incSwaps(1); }
        }
        while (i <= mid) a[k++] = buf[i++];
        // right side already in place
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComp(1);
                if (a[j] > key) {
                    a[j+1] = a[j];
                    m.incSwaps(1);
                    j--;
                } else break;
            }
            a[j+1] = key;
            m.incAllocs(1);
        }
    }
}
