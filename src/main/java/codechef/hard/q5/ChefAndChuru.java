package codechef.hard.q5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by xd on 8/23/17.
 *
 * Chef has recently learnt Function and Addition. He is too exited to teach this to his friend Churu. Chef and Churu are very fast friends, they share their knowledge whenever they meet. Chef use to give a lot of exercises after he teaches some concept to Churu.

 Chef has an array of N numbers. He also has N functions. Each function will return the sum of all numbers in the array from Li to Ri. So Chef asks churu a lot of queries which are of two types.
 Type 1: Change the xth element of the array to y.
 Type 2: Return the sum of all functions from m to n.

 Now Churu has started to solve, but Chef realize that it is tough for him to decide whether Churu is correct or not. So he needs your help , will you help him out ?
 Input Format
 First Line is the size of the array i.e. N
 Next Line contains N space separated numbers Ai denoting the array
 Next N line follows denoting Li and Ri for each functions.
 Next Line contains an integer Q , number of queries to follow.
 Next Q line follows , each line containing a query of Type 1 or Type 2.
 1 x y : denotes a type 1 query,where x and y are integers
 2 m n : denotes a type 2 query where m and n are integers
 Output Format
 For each query of type 2 , output as asked above.
 Constraints
 1 ≤ N ≤ 105
 1 ≤ Ai ≤ 109
 1 ≤ Li ≤ N
 Li ≤ Ri ≤ N
 1 ≤ Q ≤ 105
 1 ≤ x ≤ N
 1 ≤ y ≤ 109
 1 ≤ m ≤ N
 m ≤ n ≤ N
 Subtask
 Subtask 1: N ≤ 1000 , Q ≤ 1000 , 10 points
 Subtask 2: R-L ≤ 10 , all x will be distinct ,10 points
 Subtask 3: Refer to constraints above , 80 points
 Sample Input
 5
 1 2 3 4 5
 1 3
 2 5
 4 5
 3 5
 1 2
 4
 2 1 4
 1 3 7
 2 1 4
 2 3 5
 Sample Output
 41
 53
 28
 Explanation
 Functions values initially :
 F[1] = 1+ 2 + 3 = 6
 F[2] = 2 + 3 + 4 + 5 = 14
 F[3] = 4+5 = 9
 F[4] = 3+4+5 = 12
 F[5] = 1+2 = 3
 Query 1: F[1] + F[2] + F[3] + F[4] = 41
 After Update , the Functions are :
 F[1] = 10 , F[2] = 18 , F[3] = 9 , F[4] = 16 , F[5] = 3
 Query 3: F[1] + F[2] + F[3] + F[4] = 53
 Query 4: F[3]+F[4]+F[5] = 28
 */
public class ChefAndChuru {
    static long[] numbers;

    static int[][] functions;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        numbers = new long[n];
        int count = 0;
        while(tokenizer.hasMoreTokens()) {
            numbers[count++] = Long.parseLong(tokenizer.nextToken());
        }
        functions = new int[n][2];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(in.readLine());
            functions[i] = new int[] {Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
        }

        int q = Integer.parseInt(in.readLine());
        while(q > 0) {
            tokenizer = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
            int first = Integer.parseInt(tokenizer.nextToken());
            int second = Integer.parseInt(tokenizer.nextToken());
            if (type == 1) {
                numbers[first - 1] = (long) second;
            } else {
                printSumOfFunctions(first, second);
            }
            q--;
        }
    }

    private static void printSumOfFunctions(int from, int to) {
        ArrayList<Integer> starts = new ArrayList<>();
        ArrayList<Integer> ends = new ArrayList<>();
        for (int i = from - 1; i < to; i++) {
            starts.add(functions[i][0]);
            ends.add(functions[i][1]);
        }
        Collections.sort(starts);
        Collections.sort(ends);

        long sum = 0;
        int pos = starts.get(0), startIndex = 1, endIndex = 0;
        long count = 1;
        boolean done = false;
        while (!done) {
            while (startIndex < starts.size() && starts.get(startIndex) == pos) {
                count++;
                startIndex++;
            }
            while (endIndex < ends.size() && ends.get(endIndex) == pos - 1) {
                count--;
                endIndex++;
            }
            if (endIndex == ends.size()) {
                done = true;
            } else {
                sum += (numbers[pos - 1] * count);
                pos++;
            }
        }
        System.out.println(sum);
    }
}
