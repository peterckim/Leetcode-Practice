public class DivideAndConquer {
    /*
        3 Steps to Divide and Conquer Algorithms:
            1. Divide: Break the problem down into sub-problems.
            2. Conquer: Solve each sub-problem.
            3. Combine: Merge the results back together to get the ultimate answer.
     */
    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

        System.out.println(searchMatrixOptimized(array, 5));
    }

    /*
        Solution to Leetcode Problem 240: Search a 2D Matrix II
        Start from top-right number.
        If target is greater than number, eliminate current row. If target is less than number, eliminate current col
        Time Complexity: O(m + n)
     */
    public static boolean searchMatrixOptimized(int[][] matrix, int target) {
        /* If matrix is empty, return false */
        if (matrix.length == 0 || matrix == null) {
            return false;
        }

        /* Instantiate current position to top-right element */
        int row = 0;
        int col = matrix[0].length - 1;

        /* If target is greater than number, eliminate current row. If target is less than number, eliminate current col */
        while (row <= matrix.length - 1 && col >= 0 && row >= 0 && col <= matrix[0].length - 1) {
            if (target > matrix[row][col]) {
                row++;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target == matrix[row][col]) {
                return true;
            }
        }

        return false;
    }

    /*
        Solution to Leetcode Problem 240: Search a 2D Matrix II
        Divide and Conquer Approach: Partitions
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        /* If matrix is empty, target will not be in the matrix */
        if (matrix.length == 0 || matrix == null || (matrix.length == 1 && matrix[0].length == 0)) {
            return false;
        }

        /* Recursively Divide and Conquer to find the target in the Matrix */
        return searchMatrixRec(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    /*
        Solution to Leetcode Problem 240: Search a 2D Matrix II
        Divide and Conquer Approach: Partitions  (RECURSION METHOD)
     */
    public static boolean searchMatrixRec(int[][] matrix, int target, int left, int top, int right, int bottom) {
        /* If matrix's top-left value is greater than target, the target does not exist in the matrix */
        if (matrix[left][top] > target) {
            return false;
        }

        /* If matrix's bottom-right value is less than target, the target does not exist in the matrix */
        if (matrix[right][bottom] < target) {
            return false;
        }

        /* If the only remaining value in the matrix is the target, it exists in the matrix */
        if (matrix[left][top] == target || matrix[right][bottom] == target) {
            return true;
        }

        /* Get the rowMid and colMid to partition the matrix into 4 submatrices */
        int rowMid = left + (right - left) / 2;
        int colMid = top + (bottom - top) / 2;


        /* Create new values to store whether each submatrices has the target */
        boolean quad1 = false;
        boolean quad2 = false;
        boolean quad3 = false;
        boolean quad4 = false;

        /* Update values Recursively */
        /* matrix[0..rowMid][0..colMid] */
        quad1 = searchMatrixRec(matrix, target, left, top, rowMid, colMid);
        /* matrix[0..rowMid][colMid + 1..matrix[0].length] */
        if (rowMid + 1 <= matrix.length - 1) {
            quad2 = searchMatrixRec(matrix, target, rowMid + 1, top, right, colMid);
        }
        /* matrix[rowMid + 1..matrix.length][0..colMid] */
        if (colMid + 1 <= matrix[0].length - 1) {
            quad3 = searchMatrixRec(matrix, target, left, colMid + 1, rowMid, bottom);
        }
        /* matrix[rowMid + 1..matrix.length][colMid + 1..matrix[0].length] */
        if (colMid + 1 <= matrix[0].length - 1 && rowMid + 1 < matrix.length - 1) {
            quad4 = searchMatrixRec(matrix, target, rowMid + 1, colMid + 1, right, bottom);
        }

        /* If one of the quads returns true, the target does exist in the matrix */
        return quad1 || quad2 || quad3 || quad4;
    }

    /*
        Solution to Leetcode Problem 312: Burst Balloons
     */
    public static int maxCoins(int[] nums) {
        return 0;
    }

    public static void mergeSort(int arr[], int l, int r) {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
