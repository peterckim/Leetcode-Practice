package problems;

import java.math.BigInteger;

/**
 * This is a java file with all HackerRank problems I have solved.
 *
 * @author Peter Kim <peterckim96@gmail.com>
 */
public class HackerRank {
    public static void main(String[] args) {}

    /**
     * @problem     Climbing the Leaderboard
     * @link        https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?h_r=internal-search
     * @difficulty  Medium
     * @param       scores -> [100, 90, 90, 80, 75, 60]
     * @param       alice -> [50, 65, 77, 90, 102]
     * @return      res -> 6
     */
    public static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] res = new int[alice.length];  /* The result will be the same length as alice Array, so instantiate it */
        int index = 0;  /* Using an index to not loop through the entire scores array every time. Instead loop from where we left off. */
        int repeat = 0;  /* Need the amount of repeats in scores to factor it into our calculations. */

        for (int i = alice.length - 1; i >= 0; i--) {  /* loop from the back of alice array to go in ascending order of values */
            for (int j = index; j < scores.length; j++) { /* loop from front of scores array */
                if (j > 0) { /* count the repeats */
                    if (scores[j] == scores[j - 1]) {
                        repeat += 1;
                    }
                }
                if (alice[i] >= scores[j]) {  /* when alice array value is greater than or equal to the score array value */
                    res[i] = j + 1 - repeat;
                    index = j;
                    break;
                }
                if (j == scores.length - 1) {  /* when alice array value is less than the lowest score array value */
                    if (alice[i] < scores[j]) {
                        res[i] = j + 2 - repeat;
                        index = j;
                        break;
                    }
                }
            }
        }

        return res;
    }

    /**
     * @problem     Extra Long Factorials
     * @link        https://www.hackerrank.com/challenges/extra-long-factorials/problem?h_r=internal-search
     * @difficulty  Medium
     * @param       n -> 25
     * @return      res -> 15511210043330985984000000
     */
    public static void extraLongFactorials(int n) {
        /* instantiate dynamic programming memo array */
        BigInteger dp[] = new BigInteger[n];

        /* first value will be 1 indicating 1! */
        dp[0] = BigInteger.ONE;

        /* build up dp array */
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1].multiply(BigInteger.valueOf(i + 1));
        }

        System.out.println(dp[n - 1]);
    }
}

