import java.util.*;

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

        int[] inputArray = new int[]{1, 2, 3};
        System.out.println(permute(inputArray));


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

    public static int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }

    private static int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
    }
}
