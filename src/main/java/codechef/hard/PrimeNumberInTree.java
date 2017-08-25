package codechef.hard;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/*
 * Created by xd on 8/24/17 8:21 PM
 */
public class PrimeNumberInTree {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    int n;
    int N = (int)(5e4 + 10);
    boolean P[] = new boolean[N];
    long dist[][] = new long[20][N];
    long sub[] = new long[N];
    int nn = 0;
    long ans = 0;
    HashSet<Integer> a[] = new HashSet[N];
    ArrayList<Integer> prime = new ArrayList<>();

    void solve() {
        prime = populatePrimeNumbers(N);
    }

    public static ArrayList<Integer> populatePrimeNumbers(int size) {
        boolean checks[] = new boolean[size];
        Arrays.fill(checks, true);
        for (int i = 2; i * i < size; i++) {
            if (checks[i]) {
                for (int j = 2; j * i < size; j++) {
                    checks[i * j] = false;
                }
            }
        }

        ArrayList<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i < size; i++) {
            if (checks[i]) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }
}
