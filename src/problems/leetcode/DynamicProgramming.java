package problems.leetcode;

/**
 * This is a java file with all Leetcode dp-related problems I have solved.
 *
 * @author Peter Kim <peterckim96@gmail.com>
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        /* climbStairs(3, "top-down"); */

        /* coinChange(new int[]{1, 2, 5}, 11, "top-down"); */
    }

    /**
     * @problem     70. Climbing Stairs
     * @link        https://leetcode.com/problems/climbing-stairs/
     * @tags        [Dynamic Programming]
     * @difficulty  Easy
     * @param       n -> 3
     * @param       method -> "top-down", ^(?!.*(top-down))
     * @return      res -> 3
     * @wrapper
     */
    public static int climbStairs(int n, String method) {
        return (method == "top-down") ? climbStairs(n) : climbStairsBottomUp(n);
    }

    /**
     * @problem     70. Climbing Stairs
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
     * @param       coins -> [1, 2, 5]
     * @param       amount -> 11
     * @param       method -> "top-down", ^(?!.*(top-down))
     * @return      res -> 3
     * @wrapper
     */
    public static int coinChange(int[] coins, int amount, String method) {
        if (amount <= 0) return 0;

        return (method == "top-down") ? coinChange(coins, amount, new int[amount]) : coinChangeBottomUp(coins, amount);
    }

    /**
     * @problem     322. Coin Change
     * @param       coins -> [1, 2, 5]
     * @param       amount -> 11
     * @param       dp -> []
     * @return      res -> 3
     * @topdown
     */
    public static int coinChange(int[] coins, int remaining, int[] dp) {
        /* Base Cases */
        if (remaining < 0) return -1;
        if (remaining == 0) return 0;

        /* Memoization Step */
        if (dp[remaining - 1] != 0) return dp[remaining - 1];

        int min_value = Integer.MAX_VALUE;

        /* Iterate each branch at each level of recursion tree */
        for (int i = 0; i < coins.length; i++) {
            /* Get value of child Nodes */
            int res = coinChange(coins, remaining - coins[i], dp);

            /* If path is valid, check if it is the min value for current root */
            if (res >= 0 && res < min_value) {
                min_value = 1 + res;
            };
        }

        /* After iterating through each child node from CURRENT ROOT, update dp array with minimum of current subtree */
        dp[remaining - 1] = (min_value == Integer.MAX_VALUE) ? -1 : min_value;

        return dp[remaining - 1];
    }

    /**
     * @problem     322. Coin Change
     * @param       coins -> [1, 2, 5]
     * @param       amount -> 11
     * @return      res -> 3
     * @bottomup
     */
    public static int coinChangeBottomUp(int[] coins, int amount) {
        return 0;
    }
}
