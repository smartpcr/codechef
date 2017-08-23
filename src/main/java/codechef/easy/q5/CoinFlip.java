package codechef.easy.q5;

import java.io.*;
import java.util.InputMismatchException;

/*
 * Created by xd on 8/23/17 10:08 AM
 *
Little Elephant was fond of inventing new games. After a lot of research, Little Elephant came to know that most of the
animals in the forest were showing less interest to play the multi-player games.Little Elephant had started to invent
single player games, and succeeded in inventing the new single player game named COIN FLIP.

In this game the player will use N coins numbered from 1 to N, and all the coins will be facing in "Same direction"
(Either Head or Tail),which will be decided by the player before starting of the game.

The player needs to play N rounds.In the k-th round the player will flip the face of the all coins whose number is less
than or equal to k. That is, the face of coin i will be reversed, from Head to Tail, or, from Tail to Head, for i ≤ k.

Elephant needs to guess the total number of coins showing a particular face after playing N rounds. Elephant really
becomes quite fond of this game COIN FLIP, so Elephant plays G times. Please help the Elephant to find out the answer.

Input
The first line of input contains an integer T, denoting the number of test cases. Then T test cases follow.

The first line of each test contains an integer G, denoting the number of games played by Elephant. Each of the
following G lines denotes a single game, and contains 3 space separeted integers I, N, Q, where I denotes the initial
state of the coins, N denotes the number of coins and rounds, and Q, which is either 1, or 2 as explained below.

Here I=1 means all coins are showing Head in the start of the game, and I=2 means all coins are showing Tail in the
start of the game. Q=1 means Elephant needs to guess the total number of coins showing Head in the end of the game,
and Q=2 means Elephant needs to guess the total number of coins showing Tail in the end of the game.

Output
For each game, output one integer denoting the total number of coins showing the particular face in the end of the game.

Constraints
1 ≤ T ≤ 10
1 ≤ G ≤ 20000
1 ≤ N ≤ 109
1 ≤ I ≤ 2
1 ≤ Q ≤ 2
Example

Input:
1
2
1 5 1
1 5 2

Output:
2
3
Explanation:
In the 1st game in Example: I=1, so initial arrangement of coins are H H H H H, and now Elephant will play 5 rounds and
coin faces will be changed as follows
After the 1st Round: T H H H H
After the 2nd Round: H T H H H
After the 3rd Round: T H T H H
After the 4th Round: H T H T H
After the 5th Round: T H T H T

Finally Q=1, so we need to find the total number of coins showing Head, which is 2.

In the 2nd game in Example: This is similar to the 1st game, except Elephant needs to find the total number of coins
showing Tail. So the Answer is 3. (Please see the final state of the coins in the 1st game)
 */
public class CoinFlip {
    private static class InputReader {

        BufferedInputStream bin;
        int numChars,curChar;
        byte[] buffer;
        static final int MAXBUF = 15000000;

        public InputReader(InputStream in) {
            bin = new BufferedInputStream(in);
            buffer = new byte[MAXBUF];
        }

        private int read() {
            if(numChars == -1)
                throw new InputMismatchException();

            if(curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = bin.read(buffer);
                } catch(IOException e) {
                    throw new InputMismatchException();
                }
                if(numChars <= 0)
                    return -1;
            }
            return buffer[curChar++];
        }

        public int nextInt() {
            int ch = read();
            while(isSpaceChar(ch))
                ch = read();

            int sgn = 1,res = 0;
            if(ch == '-') {
                sgn = -1;
                ch = read();
            }

            do {
                if(ch < '0' || ch > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += ch - '0';
                ch = read();
            } while(!isSpaceChar(ch));

            return res*sgn;
        }

        public boolean isSpaceChar(int ch) {
            return ch == ' ' || ch == '\n' || ch == '\r' || ch == -1 || ch == '\t';
        }

    }

    public static void main(String[] args) throws IOException{
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder soln = new StringBuilder();
        int numTc,numGames, total;
        int start,end;

        // input number of test cases
        numTc = in.nextInt();

        // solve for each test case
        for ( int i = 0; i < numTc; i++ ) {

            numGames = in.nextInt();
            for (int j = 0; j < numGames; j++) {
                start = in.nextInt();
                total = in.nextInt();
                end = in.nextInt();
                if (start != end) {
                    soln.append((total/2) + (total % 2));
                }
                else {
                    soln.append((total/2));
                }
                soln.append("\n");
            }
        }
        out.print(soln.toString());
        //in.close();
        out.close();
    }
}
