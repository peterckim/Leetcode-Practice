package problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode {
    public static void main(String[] args) {
    }

    /**
     * @problem     152. Maximum Product Subarray
     * @link        https://leetcode.com/problems/maximum-product-subarray/
     * @tags        [Array, Dynamic Programming]
     * @difficulty  Medium
     * @param       nums -> [2,3,-2,4]
     * @return      res -> 6
     */
    public int maxProduct(int[] nums) {
        /*
            Thoughts
                1. Must have an even number of negatives
                2. Avoid 0's
         */



        return 0;
    }

    /**
     * @problem     153. Find Minimum in Rotated Sorted Array
     * @link        https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
     * @tags        [Array, Binary Search]
     * @difficulty  Medium
     * @param       nums -> [3,4,5,1,2]
     * @return      res -> 1
     */
    public int findMin(int[] nums) {
        /* if nums is empty, return -1 */
        if (nums.length == 0) return -1;

        /* if nums has 1 element, or is not rotated, return first element */
        if (nums.length == 1 || (nums[0] < nums[nums.length - 1])) return nums[0];

        return findMin(nums, 0, nums.length - 1);
    }

    /**
     * @function Find the Minimum
     */
    public int findMin(int[] nums, int left, int right) {
        while (left <= right) {
            int pivot = (left + right) / 2;

            if (nums[pivot] > nums[pivot + 1]) {
                return nums[pivot + 1];
            } else {
                if (nums[pivot] < nums[left]) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            }
        }

        return -1;
    }


    /**
     * @problem     33. Search in Rotated Sorted Array
     * @link        https://leetcode.com/problems/search-in-rotated-sorted-array/
     * @tags        [Array, Binary Search]
     * @difficulty  Medium
     * @param       nums -> [4,5,6,7,0,1,2]
     * @param       target -> 0
     * @return      res -> 4
     */
    public int search(int[] nums, int target) {
        /* If nums is empty, return -1 */
        if (nums.length == 0) return -1;

        /* If nums only has 1 number, check if that number is target and return */
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        /* Find rotation index */
        int rotation_index = findRotationIndex(nums, 0, nums.length - 1);

        /*
            If
                the rotation index number is target, return rotation index
            Else if
                rotation index is 0, there has been no rotation; thus, run a binary search on the entire array
            Else
                target is less than first element, the target has to be after the rotation
                OR
                target is greater than or equal to first element, so it has to be before the rotation
        */
        if (nums[rotation_index] == target) {
            return rotation_index;
        } else if (rotation_index == 0) {
            return search(nums, target, 0, nums.length - 1);
        } else {
            return (target >= nums[0]) ? search(nums, target, 0, rotation_index) : search(nums, target, rotation_index, nums.length - 1);
        }
    }

    /**
     * @function    Binary Search
     */
    public int search(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int pivot = (right + left) / 2;

            if (nums[pivot] == target) {
                return pivot;
            } else {
                if (target < nums[pivot]) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            }
        }

        return -1;
    }

    /**
     * @function Find the Rotation Index
     */
    public int findRotationIndex(int[] nums, int left, int right) {
        if (nums[left] < nums[right]) return 0;

        while (left <= right) {
            int pivot = (right + left) / 2;

            if (nums[pivot] > nums[pivot + 1]) {
                return pivot + 1;
            } else {
                if (nums[pivot] < nums[left]) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            }
        }

        return 0;
    }

    /**
     * @problem     15. 3Sums
     * @link        https://leetcode.com/problems/3sum/
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
    }
}
