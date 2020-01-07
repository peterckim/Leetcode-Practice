import java.util.*;
import java.util.LinkedList;

public class General {
    public static void main(String[] args) {

        int[] input = new int[]{3, -3, 2, -3};

        maxSubArray(input);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }

    public int maxProfit(int[] prices) {
        int min_price = Integer.MAX_VALUE;
        int max_profit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min_price) {
                min_price = prices[i];
            }

            if (prices[i] - min_price > max_profit) {
                max_profit = prices[i] - min_price;
            }
        }

        return max_profit;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> store = new HashSet<>();

        for (int i: nums) {
            if (store.contains(i)) {
                return true;
            }

            store.add(i);
        }

        return false;
    }

    public int[] productExceptSelf(int[] nums) {
        /* Constant Space Complexity */
        int[] output = new int[nums.length];
        int right = 1;

        output[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        for (int i = nums.length; i >= 0; i--) {
            output[i] = output[i] * right;
            right = right * nums[i];
        }

        return output;

        /* O(N) without Constant Space Complexity */
        /*
            int[] L = new int[nums.length];
            int[] R = new int[nums.length];

            int[] output = new int[nums.length];



            L[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                L[i] = L[i - 1] * nums[i - 1];
            }

            R[nums.length - 1] = 1;
            for (int i = nums.length - 2; i >= 0; i--) {
                R[i] = R[i + 1] * nums[i + 1];
            }

            for (int i = 0; i < nums.length; i++) {
                output[i] = L[i] * R[i];
            }

            return output;
        */
    }

    /* Review the coding */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            max = Math.max(nums[i], max);
        }

        return max;
    }

    public int maxProduct(int[] nums) {
        int numberOfNegatives = 0;
        int max = nums[0];

        if (max < 0) {
            numberOfNegatives = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                numberOfNegatives++;
            }

            nums[i] *= nums[i - 1];

            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

    public int maxArea(int[] height) {
        return maxAreaRecurse(height, 0, 0, height.length - 1);
    }

    public int maxAreaRecurse(int[] height, int max_area, int first_pointer, int last_pointer) {
        if (last_pointer <= first_pointer) {
            return max_area;
        }

        int curr_area = (last_pointer - first_pointer) * Math.min(height[last_pointer], height[first_pointer]);

        if (curr_area > max_area) {
            max_area = curr_area;
        }

        if (height[first_pointer] <= height[last_pointer]) {
            return maxAreaRecurse(height, max_area, first_pointer + 1, last_pointer);
        } else {
            return maxAreaRecurse(height, max_area, first_pointer, last_pointer - 1);
        }
    }

    /**
     * @problem     15. 3Sums
     * @tags        [Array, Two Pointers]
     * @difficulty  Medium
     * @param       nums -> [-1, 0, 1, 2, -1, 4]
     * @return      res -> [ [-1, 0, 1], [-1, -1, 2] ]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();

        /*
            Only iterate up to nums.length - 3. Why?

            Because for a 3Sums problem, we need a TRIPLET of numbers equaling 0.
            With 2 pointers going in the same direction, we can't form a triplet with a starting index of anything
                greater than nums.length - 3;
         */
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i + 1; int hi = nums.length - 1; int sum = -1 * nums[i];

                /*
                    IF lower pointer passed higher pointer, we have went through all possible solutions, so stop then.
                 */
                while (lo < hi) {
                    /* If both pointers numbers add up to sum */
                    /* Else If both pointers numbers is less than sum, the sum of the numbers needs to increase, so increase lower pointer */
                    /* Else decrease higher pointer */
                    if (nums[lo] + nums[hi] == sum) {
                        /* Add to result List */
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                        /* If next indices contain repeat numbers, skip them to improve performance */
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;

                        /* Increment pointers */
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }

        return res;


        /*  BRUTE FORCE ALGORITHM USING A HASH SET. NOT OPTIMIZED AT ALL. VIEW ABOVE ALGORITHM */

        /*
            List<List<Integer>> res = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                int init_number = nums[i];
                Set<Integer> numberBank = new HashSet<>();
                for (int j = i + 1; j < nums.length; j++) {
                    int curr_number = nums[j];
                    int needed_number = -1 * (init_number + curr_number);

                    if (numberBank.contains(needed_number)) {
                        List<Integer> curr_res = new ArrayList<>();
                        curr_res.add(init_number);
                        curr_res.add(curr_number);
                        curr_res.add(needed_number);

                        Collections.sort(curr_res);

                        if (!res.contains(curr_res)) {
                            res.add(curr_res);
                        }
                    }
                    numberBank.add(curr_number);
                }
            }

            return res;
        */
    }
}
