/**
 * ============================================================
 *  TASK 2 — Core Class 2: Searcher
 * ============================================================
 *  Handles searching operations.
 *
 *  Chosen Algorithm:
 *    Category C (Searching) → Binary Search — O(log n)
 *
 *  Binary Search requires the input array to be sorted.
 *  If the array is unsorted the result is undefined, so
 *  callers should sort the array first.
 * ============================================================
 */
public class Searcher {

    // ────────────────────────────────────────────────────────
    //  TASK 1 — Category C: Binary Search  O(log n)
    // ────────────────────────────────────────────────────────
    /**
     * Searches for a target value in a SORTED array using
     * the Binary Search algorithm.
     *
     * How it works:
     *   1. Maintain two pointers: low (start) and high (end).
     *   2. Compute mid = (low + high) / 2.
     *   3. If arr[mid] == target → found, return mid.
     *   4. If arr[mid] < target  → discard left half  (low  = mid + 1).
     *   5. If arr[mid] > target  → discard right half (high = mid - 1).
     *   6. Repeat until low > high (element not found → return -1).
     *
     * Why Binary Search needs a sorted array:
     *   Binary Search decides which half to discard based on
     *   whether the middle element is less than or greater than
     *   the target. This decision is only valid when the array
     *   is sorted in ascending order. In an unsorted array, the
     *   ordering assumption breaks and the algorithm may skip
     *   the half that actually contains the target.
     *
     * Time Complexity:
     *   Best  — O(1)      (target is at the middle)
     *   Avg   — O(log n)
     *   Worst — O(log n)
     *
     * Space Complexity: O(1) (iterative implementation)
     *
     * @param arr    sorted array to search in
     * @param target value to find
     * @return index of target, or -1 if not found
     */
    public int search(int[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;   // safe from overflow

            if (arr[mid] == target) {
                return mid;                      // target found
            } else if (arr[mid] < target) {
                low = mid + 1;                   // target is in the right half
            } else {
                high = mid - 1;                  // target is in the left half
            }
        }

        return -1;  // target not present in the array
    }
}
