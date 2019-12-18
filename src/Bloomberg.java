import javafx.util.Pair;

import java.util.*;
import java.util.LinkedList;

public class Bloomberg {
    public static void main(String[] args) {
//        String[] input = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
//        System.out.println(topKFrequent(input, 4));

//        System.out.println(removeDuplicates("abbaca"));

//        int[][] input = {{0,30},{15,20},{5,10}};
//        int[][] input1 = {{7,10}, {2, 4}};
//
//        System.out.println(canAttendMeetings(input1));

//        System.out.println(titleToNumber("hi"));
//        System.out.println(decodeString("3[a]2[bc]"));
//
//        System.out.println(decodeString("3[a2[c]]"));
//
//        System.out.println(decodeString("2[abc]3[cd]ef"));
//        System.out.println(decodeString("100[leetcode]"));

//        int[] inputArray = new int[]{1, 2, 3};
//        System.out.println(permute(inputArray));


//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("leet");
//        wordDict.add("code");
//        System.out.println(wordBreak("leetcode", wordDict));
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(2);
        System.out.println(res.size());



    }

    public static List<String> topKFrequent(String[] words, int k) {
        /*
            Given a non-empty list of words, return the k most frequent elements.
            Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
         */

        /*
            Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
            Output: ["i", "love"]
            Explanation: "i" and "love" are the two most frequent words.
                Note that "i" comes before "love" due to a lower alphabetical order.
         */

        /* Brute Force */
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }

    public static String removeDuplicates(String S) {
        Stack<Character> charStack = new Stack<>();

        // optimize by using charAt instead of creating a char array
        char[] ch = S.toCharArray();

        StringBuilder stringSequence = new StringBuilder();


        for (char c: ch) {
            if (charStack.empty()) {
                charStack.push(c);
            } else {
                if (charStack.peek() == c) {
                    charStack.pop();
                } else {
                    charStack.push(c);
                }
            }
        }
        int initialStackSize = charStack.size();

        for (int i = 0; i < initialStackSize; i++) {
            stringSequence.append(charStack.pop());
        }


        return stringSequence.reverse().toString();
    }

    public static void reverseString(char[] s) {
        /*
            Input: ["h","e","l","l","o"]
            Output: ["o","l","l","e","h"]
         */

        for (int i = 0; i < s.length; i++) {
            int currentIndex = i;
            int oppositeIndex = s.length - 1 - i;

            if (i < oppositeIndex) {
                char temp = s[i];
                s[i] = s[oppositeIndex];
                s[oppositeIndex] = temp;
            }
        }
    }

    public static int singleNumber(int[] nums) {
        /*
            Input: [4,1,2,1,2]
            Output: 4
         */

        /*
            Lookup Table (Check)
         */
        Set<Integer> set = new HashSet<>();
        int result = 0;

        for (int n: nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }


        for (int n: nums) {
            if (set.contains(n)) {
                result = n;
            }
        }

        return result;

        /*
            Bit Manipulation (?)
         */

    }

    public static int singleNumberBit(int[] nums) {
        int rs = 0;

        for (int i = 0; i < nums.length; i++) {
            rs = rs ^ nums[i];

        }

        return rs;
    }

    public static void moveZeroes(int[] nums) {
        /*
            Input: [0,1,0,3,12]
            Output: [1,3,12,0,0]
         */

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == 0) {
                    if (nums[j] != 0) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
    }

    public static int twoCitySchedCost(int[][] costs) {
        /*
            Input: [[10,20],[30,200],[400,50],[30,20]]
            Output: 110
            Explanation:
            The first person goes to city A for a cost of 10.
            The second person goes to city A for a cost of 30.
            The third person goes to city B for a cost of 50.
            The fourth person goes to city B for a cost of 20.

            The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
         */

        /*
            Give biggest difference priority
         */

        // Sort by a gain which company has
        // by sending a person to city A and not to city B
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o1[1] - (o2[0] - o2[1]);
            }
        });

        int total = 0;
        int n = costs.length / 2;
        // To optimize the company expenses,
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < n; ++i) total += costs[i][0] + costs[i + n][1];
        return total;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.equals(str1, str2);
    }

    public static boolean canAttendMeetings(int[][] intervals) {
        /*
            Steps:
            1. Sort by Start Time,
            2. Compare end time with next's start time
         */

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][1] > intervals[j][0]) {
                    return false;
                }
            }
        }

        return true;

    }

    public static int titleToNumber(String s) {
        /*
            Input: "A"
            Output: 1
         */

//        return 'Z' - 'A';
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            sum = sum * 26 + (s.charAt(i) - 'A' + 1);
        }

        return sum;
    }

//    public  boolean isCousins(TreeNode root, int x, int y) {
//        if (root == null)
//            return false;
//        Queue<TreeNode> queue = new LinkedList<>();
//        boolean xParent = false, yParent = false;
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//
//            for (int i = 0; i < size; i++) {
//                TreeNode node = queue.poll();
//                // if x and y share same parent.
//                if (node.left != null && node.right != null
//                        && (node.left.val == x && node.right.val == y || node.left.val == y && node.right.val == x)) {
//                    return false;
//                }
//                if (node.left != null) {
//                    queue.add(node.left);
//                    if (node.left.val == x)
//                        xParent = true;
//                    if (node.left.val == y)
//                        yParent = true;
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                    if (node.right.val == x)
//                        xParent = true;
//                    if (node.right.val == y)
//                        yParent = true;
//                }
//                if (xParent && yParent)
//                    break;
//            }
//            if (xParent && yParent)
//                return true;
//            if ((xParent && !yParent) || (!xParent && yParent))
//                return false;
//
//        }
//        return false;
//    }

    public int firstUniqChar(String s) {
        Set<Character> set = new HashSet();

        int index = -1;

        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (s.charAt(i) == s.charAt(index)) {
                    index = -1;
                }
            } else {
                set.add(s.charAt(i));
                index = i;
            }
        }

        return index;
    }

    public static String decodeString(String s) {
        Stack<Character> stackOfCharacters = new Stack();
        StringBuilder finalString = new StringBuilder();

        for (char c: s.toCharArray()) {
            if (c == ']') {
                StringBuilder sb = new StringBuilder();
                StringBuilder numberString = new StringBuilder();
                while(!Character.isDigit(stackOfCharacters.peek())) {
                    if (stackOfCharacters.peek() != '[') {
                        sb.append(stackOfCharacters.pop());
                    } else {
                        stackOfCharacters.pop();
                    }
                }


                while(!stackOfCharacters.empty()) {
                    if (Character.isDigit(stackOfCharacters.peek())) {
                        numberString.append(stackOfCharacters.pop());
                    } else {
                        break;
                    }
                }


                int timesRepeated = Integer.parseInt(numberString.reverse().toString());

                String stringRepeated = sb.reverse().toString();
                String parsedString = "";

                for (int i = 0; i < timesRepeated; i++) {
                    parsedString += stringRepeated;
                }


                for (char csb: parsedString.toCharArray()) {
                    stackOfCharacters.push(csb);
                }

            } else {
                stackOfCharacters.push(c);
            }
        }

        while(!stackOfCharacters.empty()) {
            finalString.append(stackOfCharacters.pop());
        }

        return finalString.reverse().toString();
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max_area = 0;
        /*
            Step 1: Find all the lands
            Step 2: Check starting with the biggest whether it is an island
         */

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    max_area = Math.max(max_area, areaOfIsland(grid, i, j));
                }
            }
        }
        return max_area;
    }

    public int areaOfIsland(int[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length && grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + areaOfIsland(grid, i + 1, j) + areaOfIsland(grid, i - 1, j) + areaOfIsland(grid, i, j + 1) + areaOfIsland(grid, i, j - 1);
        }

        return 0;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else {
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) {
                    // System.out.println("i: " + i);
                    continue; // element already exists, skip
                }
                tempList.add(nums[i]);
                // System.out.println("i: " + i);
                // System.out.println(tempList);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
                // System.out.println("remove: " + tempList);
            }
        }
    }

    /*
        Dynamic Programming
     */
    public static int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }

    private static int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
    }

    /*
        Divide and Conquer + Dynamic Programming + Array
     */
    public int maxSubArray(int[] nums) {
        /*
            Input: [-2,1,-3,4,-1,2,1,-5,4],
            Output: 6
            Explanation: [4,-1,2,1] has the largest sum = 6.
         */

        /*
            Steps:
            1. Find the Max contiguous sequence that starts with index 0, 1, 2, ... N
            2. Find the max between those results
         */
        return 0;
    }

    public static int findPairs(int[] nums, int k) {
        /*
            Input: [3, 1, 4, 1, 5], k = 2
            Output: 2
            Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
            Although we have two 1s in the input, we should only return the number of unique pairs.
         */
        int count = 0;
        int used = 100000000;
        Set<Integer> repeatCheck= new HashSet();

        Arrays.sort(nums);


        for (int i = 0; i < nums.length; i++) {
            if (!repeatCheck.contains(nums[i])) {
                repeatCheck.add(nums[i]);
            }

            for (int j = i+1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k && nums[j] != used) {
                    used = nums[j];
                    repeatCheck.add(nums[j]);
                    count++;
                }
            }
        }

        return count;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*
            Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
            Output: Reference of the node with value = 8
            Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
                From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5].
                There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
         */

        /* Brute Force */
        /* Loop through each and save each node in a Set */

        Set<ListNode> listNodeLookupTable = new HashSet();


        while (headA != null) {
            listNodeLookupTable.add(headA);
            if (headA.next == null) {
                break;
            } else {
                headA = headA.next;
            }
        }

        while (headB != null) {
            if (listNodeLookupTable.contains(headB)) {
                return headB;
            } else {
                if (headB.next == null) {
                    break;
                } else {
                    headB = headB.next;
                }
            }
        }

        return null;
    }

    public int minHeightShelves(int[][] books, int shelf_width) {
        /*
            Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
            Output: 6
            Explanation: The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
                Notice that book number 2 does not have to be on the first shelf.
         */

        return 1;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        /*
            Input: s = "leetcode", wordDict = ["leet", "code"]
            Output: true
            Explanation: Return true because "leetcode" can be segmented as "leet code".
         */
        return false;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int wordLength = beginWord.length();
        Map<String, ArrayList<String>> combinations = new HashMap();

        wordList.forEach(word -> {
            for (int i = 0; i < wordLength; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, wordLength);
                ArrayList<String> adjacents = combinations.getOrDefault(newWord, new ArrayList<String>());
                adjacents.add(word);
                combinations.put(newWord, adjacents);
            }
        });

        /* Queue for BFS */
        Queue<Pair<String, Integer>> Q = new LinkedList<Pair<String, Integer>>();
        Q.add(new Pair(beginWord, 1));

        Set<String> visited = new HashSet();
        visited.add(beginWord);

        while(!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < wordLength; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);

                for (String adjacentWord : combinations.getOrDefault(newWord, new ArrayList<String>())) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.contains(adjacentWord)) {
                        visited.add(adjacentWord);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }

    public static boolean isValidBST(TreeNode root) {
        /*
            Steps:
                1: Do an In-order Traversal.
                2: If the array is sorted from least to greatest in value. It is valid.
        */
        Stack<TreeNode> dfsStack = new Stack();
        ArrayList<TreeNode> resList = new ArrayList();
        boolean done = false;

        /*
            In-Order Traversal Using Stack
         */
        TreeNode currentNode = root;


        while(!done) {
            if (currentNode != null) {
                dfsStack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                if (!dfsStack.empty()) {
                    currentNode = dfsStack.pop();
                    resList.add(currentNode);
                    currentNode = currentNode.right;
                } else {
                    done = true;
                }
            }
        }
        /*
            Check if the Array is sorted
            Time Complexity: O(N)
         */
        for (int i = 1; i < resList.size(); i++) {
            if (resList.get(i).val <= resList.get(i - 1).val) {
                return false;
            }
        }

        return true;
    }

    public static String longestPalindrome(String s) {
        /*
            Dynamic Programming
                1. Recursion Method
                2. Memoization
         */
        int n = s.length(); // Length of s
        String res = null;  // Initialize res variable

        boolean[][] dp = new boolean[n][n];     // Create a table

        for (int i = n - 1; i >= 0; i--) {  // Pointer from the back of the array
            for (int j = i; j < n; j++) {   // Pointer from the i to the end of the array
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);   // Return true if s[i] == s[j] AND (j - i < 3 OR dp[i + 1][j - 1])

                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {    // if the string is a palindrome where i and j are AND res is null (so no res yet) OR the length of the substring is greater than current res's length
                    res = s.substring(i, j + 1);    // update res to the substring between i and j pointers
                }
            }
        }

        if (s.isEmpty()) {
            res = "";
        }

        return res;
    }

//    public static List<Integer> rightSideView(TreeNode root) {
//        /*
//            PostOrder Traversal
//            Get the last tree depth numbers from the traversal
//         */
//
//
//    }
}
