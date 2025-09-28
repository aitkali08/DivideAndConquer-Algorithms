package main.java.com.aitkali.algos;

import main.java.com.aitkali.algos.metrics.Metrics;
import main.java.com.aitkali.algos.sort.MergeSort;
import main.java.com.aitkali.algos.sort.QuickSort;
import main.java.com.aitkali.algos.sort.DeterministicSelect;
import main.java.com.aitkali.algos.geometry.ClosestPair;
import main.java.com.aitkali.algos.util.Point;

import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Simple CLI: run for n = 1e3, 1e4, 1e5 sample sizes; emit CSV to metrics.csv
        String csv = "metrics.csv";
        String header = "algorithm,n,time_ms,maxDepth,comparisons,swaps,allocations";
        Random rnd = new Random(12345);
        int[] sizes = new int[]{1000, 5000, 10000, 50000}; // tweak as needed
        for (int n : sizes) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt();
            // MergeSort
            Metrics m = new Metrics(); m.startTimer();
            MergeSort.sort(arr.clone(), m);
            m.stopTimer();
            m.writeCsv(csv, header, String.format("mergesort,%d,%.3f,%d,%d,%d,%d",
                    n, m.elapsedMillis(), m.maxDepth.get(), m.comparisons.get(), m.swaps.get(), m.allocations.get()));

            // QuickSort
            m = new Metrics(); m.startTimer();
            QuickSort.sort(arr.clone(), m);
            m.stopTimer();
            m.writeCsv(csv, header, String.format("quicksort,%d,%.3f,%d,%d,%d,%d",
                    n, m.elapsedMillis(), m.maxDepth.get(), m.comparisons.get(), m.swaps.get(), m.allocations.get()));

            // Deterministic select: pick median (k = n/2) on smaller sizes to keep quick
            if (n <= 50000) {
                m = new Metrics(); m.startTimer();
                DeterministicSelect.select(arr.clone(), n/2, m);
                m.stopTimer();
                m.writeCsv(csv, header, String.format("select,%d,%.3f,%d,%d,%d,%d",
                        n, m.elapsedMillis(), m.maxDepth.get(), m.comparisons.get(), m.swaps.get(), m.allocations.get()));
            }

            // Closest pair: use random points
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble()*1000, rnd.nextDouble()*1000);
            m = new Metrics(); m.startTimer();
            ClosestPair.closest(pts, m);
            m.stopTimer();
            m.writeCsv(csv, header, String.format("closest,%d,%.3f,%d,%d,%d,%d",
                    n, m.elapsedMillis(), m.maxDepth.get(), m.comparisons.get(), m.swaps.get(), m.allocations.get()));
            System.out.println("Completed n=" + n);
        }
        System.out.println("Done. CSV: metrics.csv");
    }
}
