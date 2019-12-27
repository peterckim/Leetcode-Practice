import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFS {
    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }

    /*
        Solution to Leetcode Problem 784: Letter Case Permutation
        Post Order DFS
     */
    public static List<String> letterCasePermutation(String S) {
        /* If S is null return */
        if (S == null) {
            new LinkedList<>();
        }

        List<String> res = new LinkedList<>();
        helper(S.toCharArray(), res, 0);
        return res;
    }


    public static void helper(char[] chs, List<String> res, int pos) {
        /* If we get to the last element in chs, add to res */
        if (pos == chs.length) {
            res.add(new String(chs));
            return;
        }

        /* If current element is a number, skip it */
        if (chs[pos] >= '0' && chs[pos] <= '9') {
            helper(chs, res, pos + 1);
            return;
        }

        /*  Left */
        chs[pos] = Character.toLowerCase(chs[pos]);
        helper(chs, res, pos + 1);

        /* Right */
        chs[pos] = Character.toUpperCase(chs[pos]);
        helper(chs, res, pos + 1);
    }
}
