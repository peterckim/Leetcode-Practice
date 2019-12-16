public class DynamicProgramming {
    public static void main(String[] args) {
//        int[] inputArray = new int[]{0, 0, 1, 1};
//        System.out.println(minCostClimbingStairs(inputArray));

        System.out.println(numWays(3, 2));
    }

    public static int maxProfit(int[] prices) {
        /*
            Input: [7,1,5,3,6,4]
            Output: 5
            Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
                         Not 7-1 = 6, as selling price needs to be larger than buying price.
         */
        int max_profit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max_profit = Math.max(max_profit, prices[j] - prices[i]);
            }
        }

        return max_profit;
    }

    public static int minCostClimbingStairs(int[] cost) {
        /*
            Input: cost = [10, 15, 20]
            Output: 15
            Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
         */
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i-1], cost[i-2]);
        }
        return Math.min(cost[cost.length-1], cost[cost.length-2]);
    }

    public static int climbStairs(int n) {
        return climbStairs(1, n);
    }

    public static int climbStairs(int stair, int n) {
        if (stair >= n) {
            return 1;
        }

        return climbStairs(stair + 1, n) + climbStairs(stair + 2, n);
    }

    public static int numWays(int n, int k) {
        return numWays(1, n, k, 0);
    }

    public static int numWays(int post, int n, int k, int sum) {
        /*
            Base Case
         */
        if (post >= n) {
            return 1;
        }

        /*
            Restriction
            If both prev post and prev prev post have one color, cant use that color
         */


        /*
            Recursive Case
         */
        for (int i = 0; i < k; i++) {
             sum += numWays(post + i, n, k, sum);
        }

        return sum;
    }

    public static int mctFromLeafValues(int[] arr) {
        /*
            Input: arr = [6,2,4]
            Output: 32
            Explanation: There are two possible trees.
                The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
         */

        return 0;
    }
}
