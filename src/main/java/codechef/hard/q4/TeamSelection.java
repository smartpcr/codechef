package codechef.hard.q4;

import java.util.ArrayList;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 One of the cherished customs of my childhood was choosing up sides for a cricket game. We did it this way: The two bullies of our gully would appoint themselves captains of the opposing teams, and then they would take turns picking other players. On each round, a captain would choose the most capable (or, towards the end, the least inept) player from the pool of remaining candidates, until everyone present had been assigned to one side or the other. The aim of this ritual was to produce two evenly matched teams and, along the way, to remind each of us of our precise ranking in the neighbourhood pecking order.

 We all believed this was the fairest process, but does it ensure the fairest selection of players with evenly matched teams? We believed so, but then there were times when, as the game progressed we realized that the other team was stronger than ours and may be an exchange of a couple of players between the teams would have made them balanced. That scope of improvement seemed to be there...

 Here, we need to find a way to create two evenly balanced teams for any game(or as evenly balanced as possible considering the strength of each player). A set of players must be divided into two teams. Each player must be on one team or the other; the number of player on the two teams must not differ by more than 1; each player will have a skill-point associated with him. The total skill-points of the players on each team should be as nearly equal as possible.(The absolute difference of the sum of skill-points of players in each team should be the least).

 Input
 The first line of input will contain the number of test cases 'T'(1<=T<=100). This is followed by 'T' test cases. Each test case starts with a blank line, followed by N, the total number of players. N lines will follow with the first line giving the skill-point of person 1; the second line, the skill-point of person 2; and so on. Each skill-point shall be an integer between 1 and 450. There shall be at most 100 players in all(1<=N<=100).

 Output
 Your output should be exactly '2T-1' lines. The output for each test case should be followed by a blank line, except the output for the last test case. Each odd numbered line should contain 2 numbers: the total skill-points of the players on one team, and the total skill-points of the players on the other team. Print the smaller sum first.

 Example
 Input:
 4

 3
 90
 200
 100

 10
 2
 3
 10
 5
 8
 9
 7
 3
 5
 2

 10
 1
 1
 1
 1
 1
 1
 1
 1
 1
 9

 8
 87
 100
 28
 67
 68
 41
 67
 1

 Output:
 190 200

 27 27

 5 13

 229 230
 */
public class TeamSelection {
    static long tempo = Long.MAX_VALUE >> 12;

    private static int[] solve(int[] values) {
        int length = values.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += values[i];
        }

        long[] calc = new long[sum + 1];
        calc[values[0]] = 1;
        for (int i = 1; i < length; i++) {
            int val = values[i];
            for (int j = (sum / 2 + 1); j >= val; j--) {
                calc[j] = calc[j] | (calc[j - val] << 1);
                calc[j] = tempo & calc[j];
            }
            calc[val] = calc[val] | 1;
        }
        int average = sum / 2;
        int desiredSize = values.length / 2;
        long numberWithDesiredBitSet = 1;
        numberWithDesiredBitSet = numberWithDesiredBitSet << (desiredSize - 1);
        int out = 0;
        for (int i = average; i >= 0; i--) {
            boolean temp = (calc[i] & numberWithDesiredBitSet) > 0;
            if (temp) {
                out = i;
                break;
            }
        }
        int[] ret = new int[2];
        ret[0] = out;
        ret[1] = (sum - out);
        return ret;
    }

    public static void main(String[] args) throws Exception {
        java.io.BufferedReader bufferedReader = new java.io.BufferedReader
                (new java.io.InputStreamReader(System.in));
        int numTestCases = Integer.parseInt(bufferedReader.readLine());

        ArrayList<int[]> inputs = new ArrayList<int[]>(numTestCases);
        for (int i = 0; i < numTestCases; i++) {
            bufferedReader.readLine();
            int arraySize = Integer.parseInt(bufferedReader.readLine());
            int[] values = null;
            if (arraySize % 2 == 1) {
                values = new int[arraySize + 1];
                values[arraySize] = 0;
            } else {
                values = new int[arraySize];
            }
            for (int j = 0; j < arraySize; j++) {
                values[j] = Integer.parseInt(bufferedReader.readLine());
            }
            inputs.add(values);
        }
        for (int i = 0; i < numTestCases; i++) {
            if (i > 0) {
                System.out.println("");
            }

            int[] out = solve(inputs.get(i));
            System.out.println(out[0] + " " + out[1]);
        }
    }
}
