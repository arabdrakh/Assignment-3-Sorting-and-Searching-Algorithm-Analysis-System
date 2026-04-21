import java.util.Random;

/**
 * ============================================================
 *  TASK 2 — Core Class 1: Sorter
 * ============================================================
 *  Handles all sorting operations.
 *
 *  Chosen Algorithms:
 *    Category A (Basic Sort)    → Insertion Sort   — O(n²)
 *    Category B (Advanced Sort) → Merge Sort       — O(n log n)
 *
 *  Also provides utility methods for generating random arrays
 *  and printing array contents.
 * ============================================================
 */
public class Sorter {

    // ─── Random instance for array generation ───
    private final Random random = new Random();

    // ────────────────────────────────────────────────────────
    //  TASK 1 — Category A: Insertion Sort  O(n²)
    // ────────────────────────────────────────────────────────
    /**
     * Sorts the array in-place using the Insertion Sort algorithm.
     *
     * How it works:
     *   1. Iterate from index 1 to the end of the array.
     *   2. For each element ("key"), compare it with elements
     *      to its left.
     *   3. Shift larger elements one position to the right.
     *   4. Insert the key into its correct position.
     *
     * Time Complexity:
     *   Best  — O(n)    (already sorted)
     *   Avg   — O(n²)
     *   Worst — O(n²)   (reverse-sorted)
     *
     * @param arr the array to sort
     */
    public void basicSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];       // current element to insert
            int j = i - 1;

            // shift elements that are greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;      // place key in the correct position
        }
    }

    // ────────────────────────────────────────────────────────
    //  TASK 1 — Category B: Merge Sort  O(n log n)
    // ────────────────────────────────────────────────────────
    /**
     * Sorts the array in-place using the Merge Sort algorithm.
     *
     * How it works:
     *   1. Recursively divide the array into two halves
     *      until each sub-array has one element.
     *   2. Merge the two sorted halves back together by
     *      comparing elements from each half and placing
     *      them in order.
     *
     * Time Complexity:
     *   Best / Avg / Worst — O(n log n)
     *
     * Space Complexity: O(n)  (temporary arrays during merge)
     *
     * @param arr the array to sort
     */
    public void advancedSort(int[] arr) {
        if (arr.length < 2) return;         // base case
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive helper — divides the array and merges sorted halves.
     */
    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;          // base case: single element

        int mid = left + (right - left) / 2;   // avoid integer overflow
        mergeSort(arr, left, mid);              // sort left half
        mergeSort(arr, mid + 1, right);         // sort right half
        merge(arr, left, mid, right);           // merge the two halves
    }

    /**
     * Merges two sorted sub-arrays arr[left..mid] and arr[mid+1..right].
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;    // size of left sub-array
        int n2 = right - mid;       // size of right sub-array

        // temporary arrays
        int[] leftArr  = new int[n1];
        int[] rightArr = new int[n2];

        // copy data into temp arrays
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        // merge elements in sorted order
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // copy remaining elements from leftArr (if any)
        while (i < n1) arr[k++] = leftArr[i++];

        // copy remaining elements from rightArr (if any)
        while (j < n2) arr[k++] = rightArr[j++];
    }

    // ────────────────────────────────────────────────────────
    //  TASK 2 — Utility: Print Array
    // ────────────────────────────────────────────────────────
    /**
     * Prints array contents in a readable format.
     * For large arrays (>20 elements) prints first and last 10
     * elements to keep output manageable.
     *
     * @param arr the array to print
     */
    public void printArray(int[] arr) {
        if (arr.length <= 20) {
            // print full array for small sizes
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
                if (i < arr.length - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb);
        } else {
            // abbreviated output for large arrays
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < 10; i++) {
                sb.append(arr[i]).append(", ");
            }
            sb.append("... , ");
            for (int i = arr.length - 10; i < arr.length; i++) {
                sb.append(arr[i]);
                if (i < arr.length - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    // ────────────────────────────────────────────────────────
    //  TASK 2 — Utility: Generate Random Array
    // ────────────────────────────────────────────────────────
    /**
     * Generates an array of the given size filled with random
     * integers in the range [0, size * 10).
     *
     * @param size number of elements
     * @return new random array
     */
    public int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }

    /**
     * Generates an already-sorted array of the given size.
     * Values go from 1 to size.
     *
     * @param size number of elements
     * @return new sorted array
     */
    public int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
