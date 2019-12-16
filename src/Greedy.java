import java.util.HashMap;
import java.util.Map;

public class Greedy {
    public static void main(String[] args) {

    }

    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> bank = new HashMap<>();
        bank.put(5, 0);
        bank.put(10, 0);
        bank.put(20, 0);

        for (int i = 0; i < bills.length; i++) {
            switch (bills[i]) {
                case 5:
                    bank.put(5, bank.get(5) + 1);
                    break;
                case 10:
                    if (bank.get(5) >= 1) {
                        bank.put(5, bank.get(5) - 1);
                        bank.put(10, bank.get(10) + 1);
                    } else {
                        return false;
                    }

                    break;
                case 20:
                    if (bank.get(5) >= 1 && bank.get(10) >= 1) {
                        bank.put(5, bank.get(5) - 1);
                        bank.put(10, bank.get(10) - 1);
                        bank.put(20, bank.get(20) + 1);
                    } else if (bank.get(10) < 1 && bank.get(5) >= 3) {
                        bank.put(5, bank.get(5) - 3);
                        bank.put(20, bank.get(20) + 1);
                    } else {
                        return false;
                    }

                    break;
            }
        }

        return true;
    }

    public int largestSumAfterKNegations(int[] A, int K) {
        /*
            Input: A = [2,-3,-1,5,-4], K = 2
            Output: 13
            Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
         */

        /*
            Sort the array,
            Change the first K numbers signs
         */
        return 0;
    }
}
