package main.java.com.aitkali.algos.sort;

import main.java.com.aitkali.algos.metrics.Metrics;

public class DeterministicSelect {

    public static int select(int[] a, int k, Metrics m) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k out of range");
        return selectRec(a, 0, a.length - 1, k, m);
    }

    private static int selectRec(int[] a, int lo, int hi, int k, Metrics m) {
        m.enterRec();
        try {
            while (true) {
                if (lo == hi) return a[lo];
                int pivot = medianOfMedians(a, lo, hi, m);
                int pivotIndex = partition(a, lo, hi, pivot, m);
                if (k == pivotIndex) return a[k];
                else if (k < pivotIndex) {
                    hi = pivotIndex - 1;
                } else {
                    lo = pivotIndex + 1;
                }
            }
        } finally {
            m.exitRec();
        }
    }

    // groups of 5
    private static int medianOfMedians(int[] a, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int groupLo = lo + i * 5;
            int groupHi = Math.min(groupLo + 4, hi);
            insertionSort(a, groupLo, groupHi, m);
            // move median to front
            int medianIndex = groupLo + (groupHi - groupLo) / 2;
            swap(a, lo + i, medianIndex);
            m.incSwaps(1);
        }
        // recurse to find median of medians
        int mid = lo + (numGroups - 1) / 2;
        return selectRec(a, lo, lo + numGroups - 1, mid, m);
    }

    private static int partition(int[] a, int lo, int hi, int pivotValue, Metrics m) {
        // find pivot index and move to end
        int pivotIndex = lo;
        while (pivotIndex <= hi && a[pivotIndex] != pivotValue) pivotIndex++;
        if (pivotIndex > hi) pivotIndex = lo; // fallback
        swap(a, pivotIndex, hi);
        m.incSwaps(1);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            m.incComp(1);
            if (a[i] < pivotValue) {
                swap(a, store, i);
                m.incSwaps(1);
                store++;
            }
        }
        swap(a, store, hi);
        m.incSwaps(1);
        return store;
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.incComp(1);
                if (a[j] > key) { a[j+1] = a[j]; j--; m.incSwaps(1); } else break;
            }
            a[j+1] = key;
            m.incAllocs(1);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
