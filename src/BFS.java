import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        System.out.println(letterCasePermutation("3z4"));
    }

    /*
        Solution to Leetcode Problem 784: Letter Case Permutation
        BFS
     */
    public static List<String> letterCasePermutation(String S) {
        /* If S is null, return */
        if (S == null) {
            new LinkedList<>();
        }

        Queue<String> q = new LinkedList<>();
        q.offer(S);

        /* Loop through the characters in S */
        for (int i = 0; i < S.length(); i++) {
            /* If S.charAt(i) is a number, skip this iteration */
            if (Character.isDigit(S.charAt(i))) {
                continue;
            }

            /* Get number of current leaf nodes */
            int size = q.size();

            /* Add leaf nodes */
            for (int j = 0; j < size; j++) {
                String curr = q.poll();
                char[] chs = curr.toCharArray();

                chs[i] = Character.toUpperCase(chs[i]);
                q.offer(String.valueOf(chs));

                chs[i] = Character.toLowerCase(chs[i]);
                q.offer(String.valueOf(chs));
            }
        }

        return new LinkedList<>(q);
    }
}
