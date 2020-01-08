package problems;

import java.util.*;

public class HackerRank {
    public static void main(String[] args) {
//        int[] inputArray = new int[]{100, 100, 50, 40, 40, 20, 10};
//        int[] aliceArray = new int[]{5, 25, 50, 120};
//
//
//        extraLongFactorials(25);



//        ArrayList<Integer> inputArray = new ArrayList<Integer>();
//        inputArray.add(1);
//        inputArray.add(7);
//        inputArray.add(2);
//        inputArray.add(4);


//        System.out.println(inputArray.subList(0, inputArray.size() - 1));

//        System.out.println(nonDivisibleSubset(3, inputArray));

//        String input = "ifailuhkqq";
//        char[] sToArray = input.toCharArray();
//        System.out.println(Arrays.copyOfRange(sToArray, 0, 3));

//        System.out.println(sherlockAndAnagrams("kkkk"));
    }

//    public static int[] climbingLeaderboard(int[] scores, int[] alice) {
//        /*
//            Make scores array unique
//         */
//        int[] res = new int[alice.length];
//        List<Integer> uniqueScores = new ArrayList();
//
//        for (int i = 0; i < scores.length; i++) {
//            if (i == scores.length - 1) {
//                if (scores[i] == scores[i - 1]) {
//                    continue;
//                } else {
//                    uniqueScores.add(scores[i]);
//                }
//            } else {
//                if (scores[i] == scores[i + 1]) {
//                    continue;
//                } else {
//                    uniqueScores.add(scores[i]);
//                }
//            }
//        }
//
//        for (int i: uniqueScores) {
//            System.out.println(i);
//        }
//
//        int index = uniqueScores.size() - 1;
//
//        for (int i = 0; i < alice.length; i++) {
//            for (int j = index; j >= 0; j--) {
//                if (alice[i] < uniqueScores.get(j)) {
//                    index = uniqueScores.indexOf(uniqueScores.get(j));
//                    res[i] = index + 2;
//
//                    break;
//                }
//                if (alice[i] == uniqueScores.get(j)) {
//                    index = uniqueScores.indexOf(uniqueScores.get(j));
//                    res[i] = index + 1;
//
//                    break;
//                }
//                if (j == 0 && alice[i] > uniqueScores.get(j)) {
//                    index = 0;
//                    res[i] = index + 1;
//
//                    break;
//                }
//            }
//        }
//
//        return res;
//
//        /*
//            Find index where each of alice's scores should go and add 1.
//         */
//
//    }

    public static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] res = new int[alice.length];  /* The result will be the same length as alice Array, so instantiate it */
        int index = 0;  /* Using an index to not loop through the entire scores array every time. Instead loop from where we left off. */
        int repeat = 0;  /* Need the amount of repeats in scores to factor it into our calculations. */

        for (int i = alice.length - 1; i >= 0; i--) {  /* loop from the back of alice array to go in ascending order of values */
            for (int j = index; j < scores.length; j++) { /* loop from front of scores array */
                if (j > 0) { /* count the repeats */
                    if (scores[j] == scores[j - 1]) {
                        repeat += 1;
                    }
                }
                if (alice[i] >= scores[j]) {  /* when alice array value is greater than or equal to the score array value */
                    res[i] = j + 1 - repeat;
                    index = j;
                    break;
                }
                if (j == scores.length - 1) {  /* when alice array value is less than the lowest score array value */
                    if (alice[i] < scores[j]) {
                        res[i] = j + 2 - repeat;
                        index = j;
                        break;
                    }
                }
            }
        }

        return res;
    }

    public static void extraLongFactorials(int n) {
        long dp[] = new long[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] * (i + 1);
        }

        System.out.println(dp[n - 1]);
    }

    public static int nonDivisibleSubset(int k, List<Integer> s) {

        return nonDivisibleSubset(k, s, new ArrayList<Integer>());
    }

    static int nonDivisibleSubset(int k, List<Integer> s, List<Integer> res) {
        System.out.println("s: " + s);
        System.out.println("res: " + res);

        if (s.isEmpty()) {
            System.out.println("empty");
            /* check if res is max */
            int sum = 0;
            for (int i = 0; i < res.size(); i++) {
                sum += res.get(i);
            }

            if (sum % k != 0) {
                return 1;
            } else {
                return 0;
            }
        }


        res.add(s.get(0));


        return Math.max(1 + nonDivisibleSubset(k, s.subList(1, s.size()), res), nonDivisibleSubset(k, s.subList(1, s.size()), res.subList(0, res.size() - 1)));
    }

//    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
//        int[] closest_obstacles = new int[8];
//        int row = r_q;
//        int column = c_q;
//
//        for (int i = 0; i < obstacles.length; i++) {
//            for (int j = 0; j < obstacles[i].length; j++) {
//
//            }
//        }
//
//
//    }

    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> wordBank = new HashMap();
        String flag = "Yes";

        for (String s: magazine) {
            wordBank.put(s, wordBank.getOrDefault(s, 0) + 1);
        }

        for (String s: note) {
            if (!wordBank.containsKey(s)) {
                flag = "No";
                break;
            }
            if (wordBank.get(s) >= 1) {
                wordBank.put(s, wordBank.get(s) - 1);
            } else {
                flag = "No";
                break;
            }
        }



        System.out.println(flag);
    }

    public static int sherlockAndAnagrams(String s) {
        /*
            "ifailuhqq"
            "ifa, fai, i, i, q, q"
         */

        char[] sToArray = s.toCharArray();
        Set<String> wordBank = new HashSet();
        int count = 0;

        for (int i = 0; i < sToArray.length; i++) {
            if (i == sToArray.length - 1) {
                if (sToArray[i] == sToArray[i - 1]) {
                    count++;
                }
            }
            for (int j = i + 1; j < sToArray.length; j++) {
                StringBuilder sbInner = new StringBuilder();
                char[] subArray = Arrays.copyOfRange(sToArray, i, j);
                Arrays.sort(subArray);
                for (char c: subArray) {
                    sbInner.append(c);
                }
                if (wordBank.contains(sbInner.toString())) {
                    System.out.println("second if: " + sbInner.toString());
                    count++;
                }
                wordBank.add(sbInner.toString());
            }
        }

        return count;
    }

}

