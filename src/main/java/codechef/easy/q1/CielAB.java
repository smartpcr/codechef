package codechef.easy.q1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
In Ciel's restaurant, a waiter is training. Since the waiter isn't good at arithmetic, sometimes he gives guests wrong
change. Ciel gives him a simple problem. What is A-B (A minus B) ?

Surprisingly, his answer is wrong. To be more precise, his answer has exactly one wrong digit. Can you imagine this?
Can you make the same mistake in this problem?

Input
An input contains 2 integers A and B.

Output
Print a wrong answer of A-B. Your answer must be a positive integer containing the same number of digits as the correct
answer, and exactly one digit must differ from the correct answer. Leading zeros are not allowed. If there are multiple
answers satisfying the above conditions, anyone will do.

Constraints
1 ≤ B < A ≤ 10000

Sample Input
5858 1234
Sample Output
1624

Output details
The correct answer of 5858-1234 is 4624. So, for instance, 2624, 4324, 4623, 4604 and 4629 will be accepted, but 0624,
624, 5858, 4624 and 04624 will be rejected.
 */
public class CielAB {
    public static void main(String fsd[]) {
        try {

            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String testcases = (new String(buff.readLine()));
            String num[] = null;
            num = testcases.split(" ");
            int ans = 0;
            ans = Integer.parseInt(num[0]) - Integer.parseInt(num[1]);
            ans = ans + 1;
            if (ans % 10 == 0) {
                ans = ans - 2;
            }
            System.out.println(ans);


        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
