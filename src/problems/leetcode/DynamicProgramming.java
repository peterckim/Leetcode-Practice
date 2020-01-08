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
        /*
            Base Cases
                if n reaches negative, it is not a valid path
                if n reaches 0, it is a valid path
        */
        if (n < 0) return 0;
        if (n == 0) return 1;

        /* Recursive Case */
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
        /*
            Base Cases
                if n reaches negative, it is not a valid path
                if n reaches 0, it is a valid path
        */
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;

        /* Initialize dp memoization array */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        /* Bottom Up DP */
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }

    /**
     * @problem     322. Coin Change
     * @link        https://leetcode.com/problems/coin-change/
     * @tags        [Dynamic Programming]
     * @difficulty  Medium
     * @param       coins -> []
     * @param       amount ->
     * @return      res -> 3
     * @topdown
     */
    public static int coinChange(int[] coins, int amount) {
        /* Math.min(coinChange(1), coinChange(2), coinChange(3)); */
        return 0;
    }

    /**
     * @problem     322. Coin Change
     * @link        https://leetcode.com/problems/coin-change/
     * @tags        [Dynamic Programming]
     * @difficulty  Medium
     * @param       coins -> []
     * @param       amount ->
     * @return      res -> 3
     * @bottomup
     */
    public static int coinChangeBottomUp(int[] coins, int amount) {
        return 0;
    }
}
