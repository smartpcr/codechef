package codechef.medium.q1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 In Byteland they have a very strange monetary system.

 Each Bytelandian gold coin has an integer number written on it. A coin n can be exchanged in a bank into three coins: n/2, n/3 and n/4. But these numbers are all rounded down (the banks have to make a profit).

 You can also sell Bytelandian coins for American dollars. The exchange rate is 1:1. But you can not buy Bytelandian coins.

 You have one gold coin. What is the maximum amount of American dollars you can get for it?

 Input
 The input will contain several test cases (not more than 10). Each testcase is a single line with a number n, 0 <= n <= 1 000 000 000. It is the number written on your coin.

 Output
 For each test case output a single line, containing the maximum amount of American dollars you can make.

 Example
 Input:
 12
 2

 Output:
 13
 2
 You can change 12 into 6, 4 and 3, and then change these into $6+$4+$3 = $13. If you try changing the coin 2 into 3 smaller coins, you will get 1, 0 and 0, and later you can get no more than $1 out of them. It is better just to change the 2 coin directly into $2.
 */
public class ByteLandian {
    public static long goldCoins(long input, Map<Long, Long> solutions) {
        long n1 = input/2;
        long n2 = input/3;
        long n3 = input/4;

        long result = input;

        if(n1 + n2 + n3 > result) {
            if(solutions.get(n1) == null) {
                solutions.put(n1, goldCoins(n1, solutions));
            }
            if(solutions.get(n2) == null) {
                solutions.put(n2, goldCoins(n2, solutions));
            }
            if(solutions.get(n3) == null) {
                solutions.put(n3, goldCoins(n3, solutions));
            }
            result = solutions.get(n1) + solutions.get(n2) + solutions.get(n3);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(goldCoins(Long.parseLong(line.trim()), new HashMap<Long, Long>()));
        }
    }
}
