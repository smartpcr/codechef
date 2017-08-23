package codechef.hard.q7;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 In Domino Solitaire, you have a grid with two rows and N columns. Each square in the grid contains an integer A. You are given a supply of rectangular 2 × 1 tiles, each of which exactly covers two adjacent squares of the grid. You have to place tiles to cover all the squares in the grid such that each tile covers two squares and no pair of tiles overlap.

 The score for a tile is the diﬀerence between the bigger and the smaller number that are covered by the tile. The aim of the game is to maximize the sum of the scores of all the tiles.

 Here is an example of a grid, along with two different tilings and their scores.The score for Tiling 1 is 12 = (9 − 8) + (6 − 2) + (7 − 1) + (3 − 2) while the score for Tiling 2 is 6 = (8 − 6) + (9 − 7) + (3 − 2) + (2 − 1). There are other tilings possible for this grid, but you can check that Tiling 1 has the maximum score among all tilings. Your task is to read the grid of numbers and compute the maximum score that can be achieved by any tiling of the grid.



 Your task is to read the grid of numbers and compute the maximum score that can be achieved by any tiling of the grid.

 Input
 The ﬁrst line contains one integer N, the number of columns in the grid. This is followed by 2 lines describing the grid. Each of these lines consists of N integers, separated by blanks.



 Output
 A single integer indicating the maximum score that can be achieved by any tiling of the given grid.

 Constraints
 1 ≤ N ≤ 105
 1 ≤ A ≤ 104


 Example
 Input:
 4
 8 6 2 3
 9 7 1 2
 Output:
 12

 */
public class DominoSolitaire {
    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        int N = in.nextInt();
        int[][] grid = new int[2][N];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = in.nextInt();

        int[] dp = new int[N + 1];
        dp[1] = Math.abs(grid[0][0] - grid[1][0]);

        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(
                    dp[i - 1] + Math.abs(grid[0][i - 1] - grid[1][i - 1]),
                    Math.abs(grid[0][i - 1] - grid[0][i - 2]) + Math.abs(grid[1][i - 1] - grid[1][i - 2]) + dp[i - 2]
            );
        }

        System.out.println(dp[N]);
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }

        private int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
    }
}
