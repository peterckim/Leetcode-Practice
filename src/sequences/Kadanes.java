package sequences;


/**
 * Kadane's Algorithm to find the Largest Sum of Contigious Array
 *
 * @see <a href="https://en.wikipedia.org/wiki/Maximum_subarray_problem">Maximum Subarray Problem (Wikipedia)</a>
 */
public class Kadanes {
    /**
     * Kadane's Algorithm to find the Largest Sum of Contigious Array using Dynamic Programming
     *
     * @param   nums -> [1, 0, 4, -3, 5]
     * @return  max_so_far -> 7
     *
     * Time Complexity:
     *  worst-case -> O(N)
     *
     * Space Complexity:
     *  worst-case -> O(1)
     */
    public static int Kadanes(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        /*
            This is dp memoization array
            HOWEVER: There is a space optimized way to achieve the same thing
                Change dp array to a variable at each iteration
                Goes from O(N) -> O(1)
        */
        /*
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
        */


        /* variable to keep track of greatest number <= 0, in the case all numbers are negative */
        int max_negative_value = Integer.MIN_VALUE;
        /* Space Optimized Code. Using a variable instead of an array */
        int prevValue = nums[0];
        int max_so_far = prevValue;
        if (prevValue <= 0) max_negative_value = prevValue;


        for (int i = 1; i < nums.length; i++) {
            prevValue = Math.max(prevValue, 0) + nums[i];

            /* update greatest number <= 0 in case all numbers are negative */
            if (nums[i] <= 0 && max_negative_value < nums[i]) max_negative_value = nums[i];

            /* update max_so_far if current_max > max_so_far */
            if (max_so_far < prevValue) max_so_far = prevValue;
        }

        /* If there are no positive integers in the array, return max_negative_value instead */
        return (max_so_far == 0) ? max_negative_value : max_so_far;
    }
}
