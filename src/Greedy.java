import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Greedy {
    public static void main(String[] args) {

        int[][] trips = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};

        System.out.println(findMinArrowShots(trips));
    }

    public static int findMinArrowShots(int[][] points) {
        /*
            Solution:
                1. Sort the balloons by their right-most pos.
                2. Pop balloons at their right-most pos. Next pop the next unpopped balloon at its right-most pos
         */

        /* Base Case: If there are no balloons, you don't need any arrows */
        if (points.length == 0) {
            return 0;
        }

        /* Sort the balloons by their right-most pos */
        Arrays.sort(points, (a, b) -> a[1] - b[1]);

        /* Instantiate firstArrowPosition and arrowCount to first arrow shot */
        int arrowPos = points[0][1];
        int arrowCnt = 1;

        /* Loop through balloons and increment arrowCount and arrowPosition for unpopped balloons */
        for (int i = 1; i < points.length; i++) {
            /* Continue if balloon has already been popped by an arrow */
            if (arrowPos >= points[i][0]) {
                continue;
            }

            /* Increment values if unpopped balloon */
            arrowCnt += 1;
            arrowPos = points[i][1];
        }

        /* Return the number of arrows required */
        return arrowCnt;
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        /* Create an array of every stop */
        int[] stops = new int[1001];

        /* At each stop add the number of passengers getting in or subtract the number of passengers getting out */
        for (int t[] : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }

        /* At each stop, subtract from capacity the number of passengers getting in, or add to capacity the number of passengers getting off */
        for (int i = 0; capacity >= 0 && i < 1001; ++i) {
            capacity -= stops[i];
        }

        /*
            If capacity ever becomes negative, return false because the car can't accept all trips.
                If capacity is not negative, it is possible so return true.
         */
        return capacity >= 0;
    }
}
