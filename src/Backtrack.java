import java.util.ArrayList;
import java.util.List;

public class Backtrack {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        System.out.println(getMaximumGold(grid));
    }

//    public String[] expand(String S) {
//
//    }
//
//    public String[] expandBacktrack(String S, String[] list, String tempString) {
//        boolean open = false;
//        for (int i = 0; i < S.length(); i++) {
//            if (S.charAt(i) == '{') {
//                open = true;
//            }
//            if (S.charAt(i) == '}') {
//                open = false;
//            }
//
//            if (open) {
//
//            }
//
//        }
//    }

    public static ArrayList<String> printAllPermutations(String s) {
        ArrayList<String> res = new ArrayList<>();
        int length = s.length();
        printAllPermutationsBacktrack(s, res, "", length);
        return res;
    }

    public static void printAllPermutationsBacktrack(String s, ArrayList<String> res, String tempString, int length) {
        if (tempString.length() == length && !res.contains(tempString)) {
            res.add(tempString);
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            tempString += s.charAt(i);
            printAllPermutationsBacktrack(s.substring(0, i) + s.substring(i + 1, s.length()), res, tempString, length);
            tempString = tempString.substring(0, tempString.length() - 1);
        }

    }

    public static int numTilePossibilities(String tiles) {
        ArrayList<String> res = new ArrayList<>();
        numTilePossibilitiesBacktrack(tiles, res, "");
        return res.size();
    }

    public static void numTilePossibilitiesBacktrack(String tiles, ArrayList<String> res, String tempString) {
        if (!res.contains(tempString) && !tempString.isEmpty()) {
            res.add(tempString);
        }

        for (int i = 0; i < tiles.length(); i++) {
            tempString += tiles.charAt(i);
            numTilePossibilitiesBacktrack(tiles.substring(0, i) + tiles.substring(i + 1, tiles.length()), res, tempString);
            tempString = tempString.substring(0, tempString.length() - 1);
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        solveNQueens(0, n, new ArrayList<>(), results);
        return results;
    }

    public static int getMaximumGold(int[][] grid) {
        int sum = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }

                getMaximumGoldBacktrack(grid, sum, row, col);
            }
        }

        return sum;
    }

    public static int getMaximumGoldBacktrack(int[][] grid, int sum, int row, int col) {
        /* Need a Visited array */
        boolean visited[];

        /*
            Base Case:
                If the only unvisited options left are 0 or out of the grid, return
        */
        if (row == grid.length - 1) {
            if (col == grid[row].length - 1) {
                if (grid[row - 1][col] == 0 && grid[row][col - 1] == 0) {
                    return grid[row][col];
                }
            } else if (col == 0) {
                if (grid[row - 1][col] == 0 && grid[row][col + 1] == 0) {
                    return grid[row][col];
                }
            } else {
                if (grid[row - 1][col] == 0 && grid[row][col + 1] == 0 && grid[row][col - 1] == 0) {
                    return grid[row][col];
                }
            }
        } else if (row == 0) {
            if (col == grid[row].length - 1) {
                if (grid[row + 1][col] == 0 && grid[row][col - 1] == 0) {
                    return grid[row][col];
                }
            } else if (col == 0) {
                if (grid[row + 1][col] == 0 && grid[row][col + 1] == 0) {
                    return grid[row][col];
                }
            } else {
                if (grid[row + 1][col] == 0 && grid[row][col + 1] == 0 && grid[row][col - 1] == 0) {
                    return grid[row][col];
                }
            }
        } else if (col == grid[row].length - 1) {
            if (grid[row][col - 1] == 0 && grid[row - 1][col] == 0 && grid[row + 1][col] == 0) {
                return grid[row][col];
            }
        } else if (col == 0) {
            if (grid[row][col + 1] == 0 && grid[row - 1][col] == 0 && grid[row + 1][col] == 0) {
                return grid[row][col];
            }
        } else {
            if (grid[row][col + 1] == 0 && grid[row - 1][col] == 0 && grid[row + 1][col] == 0 && grid[row][col - 1] == 0) {
                return grid[row][col];
            }
        }




        /* Get the Max value of going up, left, right, or down */
        int up = getMaximumGoldBacktrack(grid, sum, row - 1, col);
        int left = getMaximumGoldBacktrack(grid, sum, row, col + 1);
        int down = getMaximumGoldBacktrack(grid, sum, row + 1, col);
        int right = getMaximumGoldBacktrack(grid, sum, row, col - 1);

        int upLeft = Math.max(up, left);
        int downRight = Math.max(down, right);

        sum = Math.max(grid[row][col] + upLeft, grid[row][col] + downRight);


        return sum;
    }

    public static void solveNQueens(int row, int n, List<Integer> colPlacements, List<List<String>> results) {
        if (row == n) {
            results.add(generateBoardFromPlacements(colPlacements, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            colPlacements.add(col);

            if (isValid(colPlacements)) {
                solveNQueens(row + 1, n, colPlacements, results);
            }

            colPlacements.remove(colPlacements.size() - 1);
        }
    }

    public static boolean isValid(List<Integer> colPlacements) {
        int rowWeAreValidatingOn = colPlacements.size() - 1;

        for (int ithQueenRow = 0; ithQueenRow < rowWeAreValidatingOn; ithQueenRow++) {
            int absoluteColumnDistance = Math.abs(colPlacements.get(ithQueenRow) - colPlacements.get(rowWeAreValidatingOn));

            int rowDistance = rowWeAreValidatingOn - ithQueenRow;
            if (absoluteColumnDistance == 0 || absoluteColumnDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }

    public static List<String> generateBoardFromPlacements(List<Integer> colPlacements, int n) {

        List<String> board = new ArrayList<>();
        int totalItemsPlaced = colPlacements.size();

  /*
    Materialize a row for each queen that we placed
  */
        for (int row = 0; row < totalItemsPlaced; row++) {

            StringBuilder sb = new StringBuilder();

    /*
      Go through all columns in the row and populate the string.
      If the column has a queen in it place a 'Q', otherwise place
      a '.'
    */
            for (int col = 0; col < n; col++) {
                if (col == colPlacements.get(row)) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }

    /*
      Add the row to the board
    */
            board.add(sb.toString());
        }

        return board;
    }
}
