package sequences;

/**
 * Longest Increasing Subsequence
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_increasing_subsequence">Longest Increasing Subsequence Problem (Wikipedia)</a>
 */
public class LongestIncreasingSubsequence {
    public static int longestIncreasingSubsequence(int[] nums, String method) {
        return (method == "top-down") ? longestIncreasingSubsequence(nums) : longestIncreasingSubsequenceBottomUp(nums);
    }

    public static int longestIncreasingSubsequence(int[] nums) {
        return 0;
    }

    public static int longestIncreasingSubsequenceBottomUp(int[] nums) {
        return 0;
    }
}
