import java.util.Arrays;
import java.util.Map;

public class DynamicProgramming {
    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,4};
//        int[] newArray = Arrays.copyOfRange(input, 1, input.length);
//        for (int i = 0; i < newArray.length; i++) {
//            System.out.println(newArray[i]);
//        }
        System.out.println(numberOfArithmeticSlices(input));
    }

    /*
        Solution to Leetcode Problem 1105: Filling Bookcase Shelves
        Time Complexity: O(N)  -  Loop through A once.
        Space Complexity: O(N)  -  dp of size N is used
        Bottom Up Dynamic Programming
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        /* Initialize dp array */
        int[] dp = new int[books.length];
        dp[0] = books[0][1];

        for (int i = 1; i < books.length; i++) {
            /* Place the current book on its own shelf */
            dp[i] = dp[i - 1] + books[i][1];

            /* Check to see if bringing previous books down reduces overall height */
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = Math.min(dp[i-1] + books[i][1], books[i][1]);
            }
        }

        /* Return last element of dp array */
        return dp[books.length - 1];
    }

    /*
        Solution to Leetcode Problem 413: Arithmetic Slices
        Time Complexity: O(N)  -  Loop through A once.
        Space Complexity: O(N)  -  dp of size N is used
        Bottom Up Dynamic Programming
     */
    public static int numberOfArithmeticSlices(int[] A) {
        /* Initialize dp Array */
        int[] dp = new int[A.length];
        /* Initialize Variables */
        int sum = 0;

        /* Loop through A and solve */
        for (int i = 2; i < dp.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
    }

    /*
        Solution to Leetcode Problem 714: Best Time to Buy and Sell Stock with Transaction Fee
        Time Complexity: O(N)
        Bottom Up Dynamic Programming
     */
    public static int maxProfit(int[] prices, int fee) {
        /* Base Case */
        if (prices.length <= 1) {
            return 0;
        }

        /* Variables */
        int days = prices.length;
        int[] buy = new int[days];
        int[] sell = new int[days];

        /* Make Buy[0] the profit if one buys a stock on the first day */
        buy[0] = -prices[0];

        /* Iterate through every day O(N) */
        for (int i = 1; i < days; i++) {
            /* Don't buy, or buy the stock by getting sell value from yesterday and subtracting todays price */
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            /* Don't sell, or sell the stock by getting buy value from yesterday and adding todays price */
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]) - fee;
        }

        /* Return the last value of sell assuming the last transaction HAS TO BE a sell for maximum profit */
        return sell[days - 1];
    }

    /*
        Solution to Leetcode Problem 651: 4 Keys Keyboard
        Time Complexity: O(N * J)
        Bottom Up Dynamic Programming
     */
    public static int maxA(int N) {
        /* Create Dynamic Programming Array */
        int[] dp = new int[N + 1];

        /* Loop through all values leading up to N. Bottom Up. */
        for (int i = 0; i <= N; i++) {
            /* Initialize dp[i] to worst case. Printing 'A' i many times */
            dp[i] = i;

            /* Optimize */
            for (int j = 0; j < i - 3; j++) {
                /* Make dp[i] the max between worse case dp[i] or dp[j] * (i - j - 1) */
                /* TODO: FURTHER UNDERSTAND THIS LINE */
                /* https://leetcode.com/problems/4-keys-keyboard/discuss/105980/Java-4-lines-recursion-with-step-by-step-explanation-to-derive-DP */
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }

        /* Return the answer */
        return dp[N];

    }

    /*
        Solution to Leetcode Problem 931: Minimum Falling Path
        Time Complexity: O(N log N) because of Arrays.sort
        Bottom Up Dynamic Programming
     */
    public static int minFallingPathSum(int[][] A) {
        /* Create Variables */
        int rows = A.length;
        int cols = A.length;

        /* Create dp array */
        int[][] dp = new int[rows][cols];

        /* Make bottom row of dp array same as bottom row of A because the minimum path for bottom row is just itself. */
        dp[rows - 1] = A[rows - 1];

        /* At each position on the grid, return the minimum path from down left, down, down right positions + itself. */
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i + 1][j + 1], dp[i + 1][j]) + A[i][j];
                } else if (j == cols - 1) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j - 1]) + A[i][j];
                } else {
                    int tempMin = Math.min(dp[i + 1][j - 1], dp[i + 1][j]);
                    dp[i][j] = Math.min(tempMin, dp[i + 1][j + 1]) + A[i][j];
                }
            }
        }

        /* Sort the top row */
        Arrays.sort(dp[0]);

        /* Return the minimum value in the top row */
        return dp[0][0];
    }

//    public static int numberOfArithmeticSlices(int[] A) {
//        if (A.length < 3) {
//            return 0;
//        }
//        return numberOfArithmeticSlices(A, new int[A.length]);
//    }

//    public static int numberOfArithmeticSlices(int[] A, int[] res) {
//        if (A.length <= 0) {
//            if (res.length >= 3) {
//                int sum = res[1] - res[0];
//                if (res[0] == 0) {
//                    return 0;
//                }
//                for (int i = 1; i < res.length; i++) {
//                    if (res[i] - res[i - 1] != sum) {
//                        return 0;
//                    }
//                }
//
//                return 1;
//            } else {
//                return 0;
//            }
//        }
//
//        int rightSide = numberOfArithmeticSlices(Arrays.copyOfRange(A, 1, A.length), res);
//        int firstElement = A[0];
//        for (int i = 0; i < res.length; i++) {
//            if (res[i] == 0) {
//                res[i] = firstElement;
//                break;
//            }
//        }
//        int leftSide = numberOfArithmeticSlices(Arrays.copyOfRange(A, 1, A.length), res);
//        /* Sum of both left subtree and right subtree */
//        System.out.println("leftside: " + leftSide);
//        System.out.println("rightside: " + rightSide);
//        return leftSide + rightSide;
//    }

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
        int[] memo = new int[n + 1];

        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }

        return climbStairs(n, memo);
    }

    public static int climbStairs(int n, int[] memo) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int leftSide = climbStairs(n - 2);
        int rightSide = climbStairs(n - 1);

        memo[n] = leftSide + rightSide;

        return memo[n];
    }

    public static int longestCommonSubsequenceTopDown(String text1, String text2) {
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        return longestCommonSubsequenceTopDown(text1, text2, memo);
    }

    public static int longestCommonSubsequenceTopDown(String text1, String text2, int[][] memo) {
        if (text1.isEmpty() || text2.isEmpty()) {
            memo[text1.length()][text2.length()] = 0;
            return memo[text1.length()][text2.length()];
        }

        if (text1.charAt(text1.length() - 1) == text2.charAt(text2.length() - 1)) {
            /* Technically should be memo[n][m] = 1 + memo[n-1][m-1] */
            memo[text1.length()][text2.length()] = 1 + longestCommonSubsequenceTopDown(text1.substring(0, text1.length() - 1), text2.substring(0, text2.length() - 1), memo);
        } else {
            /* Should be memo[n][m] = Max(memo[n-1][m], memo[n][m-1]) */
            memo[text1.length()][text2.length()] = Math.max(longestCommonSubsequenceTopDown(text1.substring(0, text1.length() - 1), text2, memo), longestCommonSubsequenceTopDown(text1, text2.substring(0, text2.length() - 1), memo));
        }

        return memo[text1.length()][text2.length()];
    }

    public static int longestCommonSubsequenceBottomUp(String text1, String text2) {
        int L[][] = new int[text1.length() + 1][text2.length() + 1];
        for (int i=0; i<=text1.length(); i++)
        {
            for (int j=0; j<=text2.length(); j++)
            {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if (text1.charAt(i - 1) == text2.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                } else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
        return L[text1.length()][text2.length()];
    }

    public static int mincostTickets(int[] days, int[] costs) {
        int[] allDays = new int[366];
        int oneDayCost = costs[0];
        int sevenDaysCost = costs[1];
        int thirtyDaysCost = costs[2];

        for (int i = 1; i < allDays.length; i++) {
            for (int j = 0; j < days.length; j++) {
                if (i == days[j]) {
                    System.out.println(i);
                    if (i < 7) {
                        int tempMin = Math.min(allDays[i - 1] + oneDayCost, sevenDaysCost);
                        allDays[i] = Math.min(tempMin, thirtyDaysCost);
                        break;
                    } else if (i < 30) {
                        int tempMin =  Math.min(allDays[i - 1] + oneDayCost, allDays[i - 7] + sevenDaysCost);
                        allDays[i] = Math.min(tempMin, thirtyDaysCost);
                        break;
                    } else {
                        int tempMin = Math.min(allDays[i - 1] + oneDayCost, allDays[i - 7] + sevenDaysCost);
                        allDays[i] = Math.min(tempMin, allDays[i - 30] + thirtyDaysCost);
                        break;
                    }
                } else {
                    allDays[i] = allDays[i - 1];
                }
            }
        }

        return allDays[days[days.length - 1]];
    }
}
