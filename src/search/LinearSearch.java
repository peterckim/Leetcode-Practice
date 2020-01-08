package search;

/**
 * In computer science, a linear search or sequential search is a method for finding an element within a list.
 *  It sequentially checks each element of the list until a match is found or the whole list has been searched.
 *
 * Time Complexities:
 *      worst-case -> O(N)
 *      best-case -> O(1)
 *      average-case -> O(N)
 *
 * Space Complexities:
 *      worst-case -> O(1)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Linear_search">Linear Search (Wikipedia)</a>
 */

public class LinearSearch {
    public static final int find(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return i;
        }

        return -1;
    }
}
