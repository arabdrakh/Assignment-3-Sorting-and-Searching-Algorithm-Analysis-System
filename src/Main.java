import java.util.Arrays;

/**
 * ============================================================
 *  TASK 3 — Main Entry Point
 * ============================================================
 *  Demonstrates the full Sorting & Searching Analysis System.
 *
 *  Steps:
 *    1. Instantiate Sorter, Searcher, Experiment.
 *    2. Generate arrays:  Small (10), Medium (100), Large (1,000+).
 *    3. Show sorting on a small demo array.
 *    4. Run full performance experiments via Experiment class.
 * ============================================================
 */
public class Main {

    public static void main(String[] args) {

        // ── Instantiate core classes ──
        Sorter     sorter     = new Sorter();
        Searcher   searcher   = new Searcher();
        Experiment experiment = new Experiment();

        System.out.println("==============================================================");
        System.out.println("  SORTING & SEARCHING ALGORITHM ANALYSIS SYSTEM");
        System.out.println("  Selected Algorithms:");
        System.out.println("    • Insertion Sort  (Basic Sort   — Category A)");
        System.out.println("    • Merge Sort      (Advanced Sort — Category B)");
        System.out.println("    • Binary Search   (Search        — Category C)");
        System.out.println("==============================================================");
        System.out.println();

        // ════════════════════════════════════════════════════
        //  DEMO 1 — Small array (10 elements, random)
        // ════════════════════════════════════════════════════
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  DEMO 1: Insertion Sort on a SMALL random array (10 elements)");
        System.out.println("──────────────────────────────────────────────────────────────");

        int[] smallRandom = sorter.generateRandomArray(10);
        System.out.print("  Before sorting: ");
        sorter.printArray(smallRandom);

        long time1 = experiment.measureSortTime(smallRandom, "basic");
        System.out.print("  After sorting:  ");
        sorter.printArray(smallRandom);
        System.out.printf("  Time: %,d ns%n%n", time1);

        // ════════════════════════════════════════════════════
        //  DEMO 2 — Small array (10 elements, Merge Sort)
        // ════════════════════════════════════════════════════
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  DEMO 2: Merge Sort on a SMALL random array (10 elements)");
        System.out.println("──────────────────────────────────────────────────────────────");

        int[] smallRandom2 = sorter.generateRandomArray(10);
        System.out.print("  Before sorting: ");
        sorter.printArray(smallRandom2);

        long time2 = experiment.measureSortTime(smallRandom2, "advanced");
        System.out.print("  After sorting:  ");
        sorter.printArray(smallRandom2);
        System.out.printf("  Time: %,d ns%n%n", time2);

        // ════════════════════════════════════════════════════
        //  DEMO 3 — Medium array (100 elements)
        // ════════════════════════════════════════════════════
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  DEMO 3: Sorting a MEDIUM random array (100 elements)");
        System.out.println("──────────────────────────────────────────────────────────────");

        int[] mediumRandom1 = sorter.generateRandomArray(100);
        int[] mediumRandom2 = mediumRandom1.clone();

        long timeBasicMed    = experiment.measureSortTime(mediumRandom1, "basic");
        long timeAdvancedMed = experiment.measureSortTime(mediumRandom2, "advanced");

        System.out.printf("  Insertion Sort time: %,d ns%n", timeBasicMed);
        System.out.printf("  Merge Sort time:     %,d ns%n", timeAdvancedMed);
        System.out.print("  Sorted (first 20):   ");
        sorter.printArray(mediumRandom1);
        System.out.println();

        // ════════════════════════════════════════════════════
        //  DEMO 4 — Large array (1,000 elements)
        // ════════════════════════════════════════════════════
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  DEMO 4: Sorting a LARGE random array (1,000 elements)");
        System.out.println("──────────────────────────────────────────────────────────────");

        int[] largeRandom1 = sorter.generateRandomArray(1000);
        int[] largeRandom2 = largeRandom1.clone();

        long timeBasicLg    = experiment.measureSortTime(largeRandom1, "basic");
        long timeAdvancedLg = experiment.measureSortTime(largeRandom2, "advanced");

        System.out.printf("  Insertion Sort time: %,d ns%n", timeBasicLg);
        System.out.printf("  Merge Sort time:     %,d ns%n", timeAdvancedLg);
        System.out.print("  Sorted (preview):    ");
        sorter.printArray(largeRandom1);
        System.out.println();

        // ════════════════════════════════════════════════════
        //  DEMO 5 — Sorted array (insertion sort best case)
        // ════════════════════════════════════════════════════
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  DEMO 5: Sorting an ALREADY-SORTED array (1,000 elements)");
        System.out.println("──────────────────────────────────────────────────────────────");

        int[] sortedArr1 = sorter.generateSortedArray(1000);
        int[] sortedArr2 = sortedArr1.clone();

        long timeBasicSorted    = experiment.measureSortTime(sortedArr1, "basic");
        long timeAdvancedSorted = experiment.measureSortTime(sortedArr2, "advanced");

        System.out.printf("  Insertion Sort time: %,d ns  (best case — O(n))%n", timeBasicSorted);
        System.out.printf("  Merge Sort time:     %,d ns  (always O(n log n))%n", timeAdvancedSorted);
        System.out.println();

        // ════════════════════════════════════════════════════
        //  DEMO 6 — Binary Search
        // ════════════════════════════════════════════════════
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.println("  DEMO 6: Binary Search on sorted arrays");
        System.out.println("──────────────────────────────────────────────────────────────");

        int[] searchArr = sorter.generateSortedArray(1000);
        int target1 = 500;   // exists
        int target2 = 9999;  // does NOT exist

        long searchTime1 = experiment.measureSearchTime(searchArr, target1);
        int  result1     = searcher.search(searchArr, target1);
        System.out.printf("  Searching for %d → index %d  |  Time: %,d ns%n",
                target1, result1, searchTime1);

        long searchTime2 = experiment.measureSearchTime(searchArr, target2);
        int  result2     = searcher.search(searchArr, target2);
        System.out.printf("  Searching for %d → index %d (not found)  |  Time: %,d ns%n",
                target2, result2, searchTime2);
        System.out.println();

        // ════════════════════════════════════════════════════
        //  TASK 4 — Full Performance Experiments
        // ════════════════════════════════════════════════════
        System.out.println("==============================================================");
        System.out.println("  RUNNING FULL PERFORMANCE EXPERIMENTS");
        System.out.println("==============================================================");
        experiment.runAllExperiments();

        System.out.println("==============================================================");
        System.out.println("  ALL EXPERIMENTS COMPLETE.");
        System.out.println("==============================================================");
    }
}
