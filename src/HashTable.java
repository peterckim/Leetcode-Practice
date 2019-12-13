import java.util.*;

public class HashTable {
    public static void main(String[] args) {
        System.out.println(numberOfJewelsInStones("aA","aAAbbbb"));
    }

    public static int numberOfJewelsInStones(String J, String S) {
        Set<Character> Jset = new HashSet();
        for (char j: J.toCharArray())
            Jset.add(j);

        int ans = 0;
        for (char s: S.toCharArray())
            if (Jset.contains(s))
                ans++;
        return ans;
    }

    public static int[] anagramMappings(int[] A, int[] B) {
        /*
        A = [12, 28, 46, 32, 50]
        B = [50, 12, 32, 46, 28]
         */
        Map<Integer, Integer> D = new HashMap();
        for (int i = 0; i < B.length; ++i)
            D.put(B[i], i);

        /*
        50: 0,
        12: 1,
        32: 2,
        46: 3,
        28: 4
         */

        int[] ans = new int[A.length];
        int t = 0;
        for (int x: A)
            ans[t++] = D.get(x);
        return ans;
    }

//    public static List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
//        Map<Integer, Integer> D = new HashMap();
//
//    }
}
