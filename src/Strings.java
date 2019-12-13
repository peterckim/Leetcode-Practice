public class Strings {
    public static void main(String[] args) {
        System.out.println(removeVowels("leetcodeisacommunityforcoders"));
        System.out.println(singleRowKeyboard("pqrstuvwxyzabcdefghijklmno", "leetcode"));
        System.out.println(balancedStringSplit("RLRRLLRLRL"));

    }

    public static String removeVowels(String s) {
        char[] ch = s.toCharArray();

        StringBuilder sentence = new StringBuilder();

        for (char c: ch) {
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                sentence.append(c);
            }
        }

        return sentence.toString();
    }

    public static int singleRowKeyboard(String keyboard, String word) {
        char[] ch = word.toCharArray();
        int sum = 0;
        int prevIndex = 0;

        for (char c: ch) {
            int currentIndex = keyboard.indexOf(c);

            sum += Math.abs(currentIndex - prevIndex);

            prevIndex = currentIndex;
        }

        return sum;
    }

    public static int balancedStringSplit(String s) {
        int balance = 0;
        int total = 0;

        char[] ch = s.toCharArray();

        for (char c: ch) {
            if (c == 'L') {
                balance -= 1;
            } else {
                balance += 1;
            }

            if (balance == 0) {
                total += 1;
            }
        }

        return total;
    }

    public String findContestMatch(int n) {
        return "test";
    }
}
