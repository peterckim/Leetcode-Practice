import java.util.HashMap;
import java.util.Map;

public class Greedy {
    public static void main(String[] args) {

        int[][] trips = new int[][]{{2,1,5}, {3,3,7}};

        System.out.println(carPooling(trips, 4));
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        /* Create an array of every stop */
        int[] stops = new int[1001];

        /* At each stop add the number of passengers getting in or subtract the number of passengers getting out */
        for (int t[] : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }

        /* At each stop, subtract from capacity the number of passengers getting in, or add to capacity the number of passengers getting off */
        for (int i = 0; capacity >= 0 && i < 1001; ++i) {
            capacity -= stops[i];
        }

        /*
            If capacity ever becomes negative, return false because the car can't accept all trips.
                If capacity is not negative, it is possible so return true.
         */
        return capacity >= 0;
    }
}
