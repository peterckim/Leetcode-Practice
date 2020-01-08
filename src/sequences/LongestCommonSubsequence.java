package sequences;

/**
 * Longest Common Subsequence
 *
 * @see <a href="https://en.wikipedia.org/wiki/Longest_common_subsequence_problem">Longest Common Subsequence Problem (Wikipedia)</a>
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(char[] A, char[] B) {
        return longestCommonSubsequence(A, B, A.length, B.length);
    }

    /**
     * Longest Common Subsequence Top Down
     *
     * @param   A -> ['A', 'B', 'C', 'D', 'G', 'H']
     * @param   B -> ['A', 'E', 'D', 'F', 'H', 'R']
     * @param   m -> A.length
     * @param   n -> B.length
     * @return  res -> 3
     *
     * Time Complexity:
     *  worst-case -> O(2^N);
     *  2 Branches each level of the recursion tree, N is the depth
     *
     */
    public static int longestCommonSubsequence(char[] A, char[] B, int m, int n) {
        if (m == 0 || n == 0) return 0;

        if (A[m - 1] == B[n - 1]) return (1 + longestCommonSubsequence(A, B, m-1, n-1));

        return (Math.max(longestCommonSubsequence(A, B, m-1, n), longestCommonSubsequence(A, B, m, n-1)));
    }

    /**
     * Longest Common Subsequence Bottom Up
     *
     * @param   A -> ['A', 'B', 'C', 'D', 'G', 'H']
     * @param   B -> ['A', 'E', 'D', 'F', 'H', 'R']
     * @param   m -> A.length
     * @param   n -> B.length
     * @return  res -> 3
     *
     * Time Complexity:
     *  worst-case -> O(NM);
     *
     */
    public static int longestCommonSubsequenceBottomUp(char[] A, char[] B, int m, int n) {
        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= B.length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
