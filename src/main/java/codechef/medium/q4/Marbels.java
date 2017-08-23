package codechef.medium.q4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 Rohit dreams he is in a shop with an infinite amount of marbles. He is allowed to select n marbles. There are marbles of k different colors. From each color there are also infinitely many marbles. Rohit wants to have at least one marble of each color, but still there are a lot of possibilities for his selection. In his effort to make a decision he wakes up. Now he asks you how many possibilities for his selection he would have had. Assume that marbles of equal color can't be distinguished, and the order of the marbles is irrelevant.

 Input
 The first line of input contains a number T <= 100 that indicates the number of test cases to follow. Each test case consists of one line containing n and k, where n is the number of marbles Rohit selects and k is the number of different colors of the marbles. You can assume that 1<=k<=n<=1000000.

 Output
 For each test case print the number of possibilities that Rohit would have had. You can assume that this number fits into a signed 64 bit integer.

 Example
 Input:
 2
 10 10
 30 7

 Output:
 1
 475020
 */
public class Marbels {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        while(testCases-->0)
        {
            st = new StringTokenizer(br.readLine());
            System.out.println(getCombinations(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

    }

    public static long getCombinations(int n,int k)
    {
        n--;k--;
        double result = 1;
        if(n==k||k==0) return 1;
        if((n-k)<k)
        {
            k = n-k;
        }
        while(k>0)
        {
            result = result*(n--)/(k--);
        }
        return Math.round(result);
    }
}
