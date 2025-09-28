# Assignment 1: Divide and Conquer Algorithms

## 📖 About
This project is for **Assignment 1** in Design and Analysis of Algorithms (DAA).  
It implements several **divide-and-conquer algorithms** and compares their performance.

Algorithms included:
- MergeSort
- QuickSort
- Selection (Median of Medians)
- Closest Pair of Points

---

## ⚙️ How to Run Java Code
1. Open this project in **IntelliJ IDEA**.
2. Build the project:
    - In the top menu, click **Build > Build Project**.
3. Run the program:
    - Open `Main.java` (in `src/main/java/com/aitkali/algos/`).
    - Right-click → **Run 'Main.main()'**.
4. To pass arguments (example with 4 algorithms and input sizes):
    - Go to **Run > Edit Configurations > Program arguments**.
    - Insert:
      ```
      mergesort quicksort select closest 1000 5000 10000 50000
      ```
    - Run again.

---

## 📊 Collecting Results
After running, the program will print results (time, depth, comparisons, swaps, allocations).  
Copy them into a CSV file:


Format:
metrics/results.csv

```csv
algorithm,n,time,depth,comparisons,swaps,allocations
mergesort,1000,0.0021,12,15000,0,2000
quicksort,1000,0.0018,15,12000,5000,1000
select,1000,0.0009,10,8000,2000,500
closest,1000,0.0032,20,25000,0,3000
```

📈 How to Plot Graphs

Install Python libraries (only once):
```
pip install pandas matplotlib
```

Run plotting script:
```
python docs/sample-plots/plot_metrics.py metrics/results.csv
```
This will generate graphs in:
```
docs/sample-plots/

```

📑 Graphs to Include

At least 2 graphs must be shown in the report:

Running Time vs Input Size

Comparisons vs Input Size

(Optional: recursion depth, swaps, allocations)




