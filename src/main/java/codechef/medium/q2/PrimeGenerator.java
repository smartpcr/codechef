package codechef.medium.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 Shridhar wants to generate some prime numbers for his cryptosystem. Help him! Your task is to generate all prime numbers between two given numbers.

 Input
 The first line contains t, the number of test cases (less then or equal to 10). Followed by t lines which contain two numbers m and n (1 <= m <= n <= 1000000000, n-m<=100000) separated by a space.

 Output
 For every test case print all prime numbers p such that m <= p <= n, one number per line. Separate the answers for each test case by an empty line.

 Example
 Input:
 2
 1 10
 3 5

 Output:
 2
 3
 5
 7

 3
 5
 */
public class PrimeGenerator {
    static int[] pnumber;

    public static void main(String[]args) throws IOException {
        BufferedReader input = new BufferedReader (new InputStreamReader(System.in));

        subset();

        int t = Integer.parseInt(input.readLine());
        while(t>0){
            t--;
            String[] line = input.readLine().split(" ");
            findpn(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            System.out.println();
        }
    }

    static void subset(){
        pnumber=new int[3401];
        int k=0;
        int greatest = 31609;
        boolean [] a = new boolean [greatest];

        for(int i=2; i<greatest; i++){
            if(a[i]){
                continue;
            }
            pnumber[k++]=i;

            for(int j=2*i; j<greatest; j=j+i){
                a[j] = true;
            }
        }
    }

    static void findpn(int lowerBound , int upperBound){
        int greatest=3401;
        boolean [] isComposite = new boolean [upperBound-lowerBound+1];

        for(int i = 0; i < greatest; i++){
            int p = pnumber[i];

            if(p > upperBound){
                break;
            }

            int j=(int)(Math.ceil((double)lowerBound / p));

            if(j==1){
                j++;
            }

            j *= p;

            for( ;j <= upperBound; j+=p){
                isComposite[j - lowerBound] = true;
            }
        }
        int i=0;

        if(lowerBound==1){
            i++;
        }

        for( ;i<isComposite.length;i++){
            if(!isComposite[i]){
                System.out.println(i+lowerBound);
            }
        }
    }
}
