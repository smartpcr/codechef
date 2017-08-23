package codechef.medium.q5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 A positive integer is called a palindrome if its representation in the decimal system is the same when read from left to right and from right to left. For a given positive integer K of not more than 1000000 digits, write the value of the smallest palindrome larger than K to output. Numbers are always displayed without leading zeros.

 Input
 The first line contains integer t, the number of test cases. Followed by t lines containing integers K.

 Output
 For each K, output the smallest palindrome larger than K.

 Example
 Input:
 2
 808
 2133
 Output:
 818
 2222
 */
public class TheNextPalindrome {
    static char a[]=new char[1000005];
    public static void main(String[] args) throws IOException {
        BufferedReader data=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int t=Integer.parseInt(data.readLine());
        while(t--!=0)
        { String s=data.readLine();
            a=s.toCharArray();
            int l=s.length();
            int i=0,j=1,c=0;
            while(i<l){
                if(a[i]=='9') i++;
                else
                    break;
            }
            if(i==l){
                out.print('1');
                for (int k = 1; k < l; k++) {
                    out.print('0');
                }
                out.println('1');
            }
            else {
                j=0;
                while(j<l/2){
                    if(a[j]<a[l-j-1]) c=1;
                    else if(a[j]>a[l-j-1]) c=2;
                    a[l-j-1]=a[j];
                    j++;
                }
                if(c==0 || c==1){
                    if(l%2==0) j=l/2-1;
                    else j=l/2;
                    while(a[j]=='9' && j>=0){
                        a[j]=a[l-j-1]='0';
                        j--;
                    }
                    a[j]++;
                    a[l-j-1]=a[j];
                }


                j=0;
                while(j<a.length){
                    out.print(a[j++]);
                } out.println();
            }
        }out.flush();
    }
}
