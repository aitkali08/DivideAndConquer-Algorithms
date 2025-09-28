package main.java.com.aitkali.algos.metrics;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    public final AtomicLong comparisons = new AtomicLong(0);
    public final AtomicLong swaps = new AtomicLong(0);
    public final AtomicLong allocations = new AtomicLong(0);
    public final ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);
    public final AtomicLong maxDepth = new AtomicLong(0);
    private long startTime = 0;
    private long endTime = 0;

    public void startTimer(){ startTime = System.nanoTime(); }
    public void stopTimer(){ endTime = System.nanoTime(); }
    public double elapsedMillis(){ return (endTime - startTime) / 1_000_000.0; }

    public void incComp(long x){ comparisons.addAndGet(x); }
    public void incSwaps(long x){ swaps.addAndGet(x); }
    public void incAllocs(long x){ allocations.addAndGet(x); }

    public void enterRec() {
        int d = depth.get() + 1;
        depth.set(d);
        maxDepth.updateAndGet(prev -> Math.max(prev, d));
    }
    public void exitRec() {
        depth.set(Math.max(0, depth.get() - 1));
    }

    public void writeCsv(String path, String header, String line) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            if (new java.io.File(path).length() == 0) pw.println(header);
            pw.println(line);
        }
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "comparisons=" + comparisons +
                ", swaps=" + swaps +
                ", allocations=" + allocations +
                ", maxDepth=" + maxDepth +
                ", elapsedMillis=" + elapsedMillis() +
                '}';
    }
}
