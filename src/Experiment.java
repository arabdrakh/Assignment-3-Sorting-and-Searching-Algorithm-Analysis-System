import java.util.Arrays;

/**
 * ============================================================
 *  TASK 2 — Core Class 3: Experiment
 * ============================================================
 *  Responsible for running performance experiments and
 *  measuring execution time using System.nanoTime().
 *
 *  Tests Insertion Sort, Merge Sort, and Binary Search
 *  across:
 *    • Array sizes: 10, 100, 1_000, 10_000
 *    • Input types: Random, Already Sorted
 * ============================================================
 */
public class Experiment {

    private final Sorter   sorter   = new Sorter();
    private final Searcher searcher = new Searcher();

    // Array sizes to benchmark
    private static final int[] SIZES = { 10, 100, 1_000, 10_000 };

    // ────────────────────────────────────────────────────────
    //  TASK 3 — Measure sort execution time
    // ────────────────────────────────────────────────────────
    /**
     * Measures the time (in nanoseconds) to sort the given array
     * using either the basic or advanced sorting algorithm.
     *
     * @param arr  array to sort (will be modified in-place)
     * @param type "basic" for Insertion Sort,
     *             "advanced" for Merge Sort
     * @return elapsed time in nanoseconds
     */
    public long measureSortTime(int[] arr, String type) {
        long start = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(arr);
        } else {
            sorter.advancedSort(arr);
        }

        long end = System.nanoTime();
        return end - start;
    }

    // ────────────────────────────────────────────────────────
    //  TASK 3 — Measure search execution time
    // ────────────────────────────────────────────────────────
    /**
     * Measures the time (in nanoseconds) to search for a
     * target value in the given (sorted) array using
     * Binary Search.
     *
     * @param arr    sorted array to search
     * @param target value to find
     * @return elapsed time in nanoseconds
     */
    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        long end = System.nanoTime();
        return end - start;
    }

    // ────────────────────────────────────────────────────────
    //  TASK 4 — Run all experiments & print results
    // ────────────────────────────────────────────────────────
    /**
     * Runs the complete performance analysis:
     *   1. For each size in {10, 100, 1_000, 10_000}:
     *      a. Generate a RANDOM array and a SORTED array.
     *      b. Measure Insertion Sort on both.
     *      c. Measure Merge Sort on both.
     *      d. Measure Binary Search on the sorted arrays.
     *   2. Print a clear, formatted comparison table.
     */
    public void runAllExperiments() {
        printHeader();

        // ──── Sorting Experiments ────
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        SORTING PERFORMANCE RESULTS                         ║");
        System.out.println("╠══════════════╦════════════╦═══════════════════════╦═══════════════════════╣");
        System.out.println("║  Array Size  ║ Input Type ║  Insertion Sort (ns)  ║   Merge Sort (ns)     ║");
        System.out.println("╠══════════════╬════════════╬═══════════════════════╬═══════════════════════╣");

        for (int size : SIZES) {
            // ── Random array tests ──
            int[] randomForBasic    = sorter.generateRandomArray(size);
            int[] randomForAdvanced = randomForBasic.clone();  // identical copy

            long basicRandomTime    = measureSortTime(randomForBasic, "basic");
            long advancedRandomTime = measureSortTime(randomForAdvanced, "advanced");

            System.out.printf("║ %12d ║  Random    ║ %21s ║ %21s ║%n",
                    size, formatNano(basicRandomTime), formatNano(advancedRandomTime));

            // ── Sorted array tests ──
            int[] sortedForBasic    = sorter.generateSortedArray(size);
            int[] sortedForAdvanced = sortedForBasic.clone();

            long basicSortedTime    = measureSortTime(sortedForBasic, "basic");
            long advancedSortedTime = measureSortTime(sortedForAdvanced, "advanced");

            System.out.printf("║ %12d ║  Sorted    ║ %21s ║ %21s ║%n",
                    size, formatNano(basicSortedTime), formatNano(advancedSortedTime));

            System.out.println("╠══════════════╬════════════╬═══════════════════════╬═══════════════════════╣");
        }

        // close the sorting table
        System.out.println("╚══════════════╩════════════╩═══════════════════════╩═══════════════════════╝");
        System.out.println();

        // ──── Searching Experiments ────
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       SEARCHING PERFORMANCE RESULTS                        ║");
        System.out.println("╠══════════════╦════════════╦═══════════════════════╦═══════════════════════╣");
        System.out.println("║  Array Size  ║  Scenario  ║ Binary Search (ns)    ║ Result                ║");
        System.out.println("╠══════════════╬════════════╬═══════════════════════╬═══════════════════════╣");

        for (int size : SIZES) {
            // generate a sorted array for binary search
            int[] sortedArr = sorter.generateSortedArray(size);

            // ── Search for an existing element (middle element) ──
            int existingTarget = sortedArr[size / 2];
            long searchFoundTime = measureSearchTime(sortedArr, existingTarget);
            int foundIndex = searcher.search(sortedArr, existingTarget);

            System.out.printf("║ %12d ║  Found     ║ %21s ║ Index: %-14d ║%n",
                    size, formatNano(searchFoundTime), foundIndex);

            // ── Search for a non-existing element ──
            int missingTarget = size * 10 + 999;  // guaranteed to be absent
            long searchNotFoundTime = measureSearchTime(sortedArr, missingTarget);

            System.out.printf("║ %12d ║  Not Found ║ %21s ║ Index: %-14d ║%n",
                    size, formatNano(searchNotFoundTime), -1);

            System.out.println("╠══════════════╬════════════╬═══════════════════════╬═══════════════════════╣");
        }

        System.out.println("╚══════════════╩════════════╩═══════════════════════╩═══════════════════════╝");
        System.out.println();

        // ──── Summary / Observations ────
        printSummary();
    }

    // ────────────────────────────────────────────────────────
    //  Private helpers
    // ────────────────────────────────────────────────────────

    /** Pretty header for the experiment output. */
    private void printHeader() {
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│   ╔══════════════════════════════════════════════════════════════════════╗   │");
        System.out.println("│   ║   SORTING & SEARCHING ALGORITHM PERFORMANCE ANALYSIS SYSTEM        ║   │");
        System.out.println("│   ╚══════════════════════════════════════════════════════════════════════╝   │");
        System.out.println("│                                                                              │");
        System.out.println("│   Algorithms Under Test:                                                     │");
        System.out.println("│     • Basic Sort    : Insertion Sort   — O(n²)                               │");
        System.out.println("│     • Advanced Sort : Merge Sort       — O(n log n)                          │");
        System.out.println("│     • Search        : Binary Search    — O(log n)                            │");
        System.out.println("│                                                                              │");
        System.out.println("│   Array Sizes : 10 · 100 · 1,000 · 10,000                                   │");
        System.out.println("│   Input Types : Random · Sorted                                              │");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
    }

    /** Summary observations printed after all experiments. */
    private void printSummary() {
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                          KEY OBSERVATIONS                                    │");
        System.out.println("├──────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│  1. Insertion Sort is fast on small/sorted data but degrades on large        │");
        System.out.println("│     random arrays due to O(n²) complexity.                                   │");
        System.out.println("│                                                                              │");
        System.out.println("│  2. Merge Sort maintains consistent O(n log n) performance regardless        │");
        System.out.println("│     of input order, making it superior for large datasets.                   │");
        System.out.println("│                                                                              │");
        System.out.println("│  3. Binary Search achieves O(log n) — extremely fast, but REQUIRES           │");
        System.out.println("│     a sorted array to function correctly.                                    │");
        System.out.println("│                                                                              │");
        System.out.println("│  4. On sorted input, Insertion Sort's best-case O(n) can outperform          │");
        System.out.println("│     Merge Sort's O(n log n) because of lower constant overhead.              │");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
    }

    /**
     * Formats nanosecond values with thousand-separators for readability.
     * Example: 1234567 → "1,234,567"
     */
    private String formatNano(long nanos) {
        return String.format("%,d", nanos);
    }
}
