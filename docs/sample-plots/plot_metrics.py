import pandas as pd
import matplotlib.pyplot as plt

# читаем CSV
df = pd.read_csv("metrics.csv")

# пример: график времени работы от размера массива
for algo in df["algorithm"].unique():
    subset = df[df["algorithm"] == algo]
    plt.plot(subset["n"], subset["time_ms"], label=algo)

plt.xlabel("Input size (n)")
plt.ylabel("Time (ms)")
plt.title("Running time of algorithms")
plt.legend()
plt.grid(True)
plt.savefig("docs/sample-plots/time_vs_n.png")
plt.close()

# пример: график глубины рекурсии
for algo in df["algorithm"].unique():
    subset = df[df["algorithm"] == algo]
    plt.plot(subset["n"], subset["maxDepth"], label=algo)

plt.xlabel("Input size (n)")
plt.ylabel("Max recursion depth")
plt.title("Recursion depth of algorithms")
plt.legend()
plt.grid(True)
plt.savefig("docs/sample-plots/depth_vs_n.png")
plt.close()
