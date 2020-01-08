package problems.leetcode;

/**
 * This is a java file with all Leetcode dp-related problems I have solved.
 *
 * @author Peter Kim <peterckim96@gmail.com>
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

    /**
     * @problem     70. Climbing Stairs
     * @link        https://leetcode.com/problems/climbing-stairs/
     * @tags        [Dynamic Programming]
     * @difficulty  Easy
     * @param       n -> 3
     * @return      res -> 3
     * @topdown
     */
    public static int climbStairs(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;

        return (climbStairs(n - 2) + climbStairs(n - 1));
    }

    /**
     * @problem     70. Climbing Stairs
     * @link        https://leetcode.com/problems/climbing-stairs/
     * @tags        [Dynamic Programming]
     * @difficulty  Easy
     * @param       n -> 3
     * @return      res -> 3
     * @bottomup
     */
    public static int climbStairsBottomUp(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;


        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }
}
