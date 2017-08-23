package codechef.hard.q6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 Given N integers A1, A2, …. AN, Dexter wants to know how many ways he can choose three numbers such that they are three consecutive terms of an arithmetic progression.

 Meaning that, how many triplets (i, j, k) are there such that 1 ≤ i < j < k ≤ N and Aj - Ai = Ak - Aj.

 So the triplets (2, 5, 8), (10, 8, 6), (3, 3, 3) are valid as they are three consecutive terms of an arithmetic
 progression. But the triplets (2, 5, 7), (10, 6, 8) are not.

 Input
 First line of the input contains an integer N (3 ≤ N ≤ 100000). Then the following line contains N space separated integers A1, A2, …, AN and they have values between 1 and 30000 (inclusive).

 Output
 Output the number of ways to choose a triplet such that they are three consecutive terms of an arithmetic progression.

 Example
 Input:
 10
 3 5 3 6 3 4 10 4 5 2

 Output:
 9

 Explanation
 The followings are all 9 ways to choose a triplet

 1 : (i, j, k) = (1, 3, 5), (Ai, Aj, Ak) = (3, 3, 3)
 2 : (i, j, k) = (1, 6, 9), (Ai, Aj, Ak) = (3, 4, 5)
 3 : (i, j, k) = (1, 8, 9), (Ai, Aj, Ak) = (3, 4, 5)
 4 : (i, j, k) = (3, 6, 9), (Ai, Aj, Ak) = (3, 4, 5)
 5 : (i, j, k) = (3, 8, 9), (Ai, Aj, Ak) = (3, 4, 5)
 6 : (i, j, k) = (4, 6, 10), (Ai, Aj, Ak) = (6, 4, 2)
 7 : (i, j, k) = (4, 8, 10), (Ai, Aj, Ak) = (6, 4, 2)
 8 : (i, j, k) = (5, 6, 9), (Ai, Aj, Ak) = (3, 4, 5)
 9 : (i, j, k) = (5, 8, 9), (Ai, Aj, Ak) = (3, 4, 5)
 */
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Vaibhav Mittal
 */
class ArithmeticProgressions {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        ArithmeticProgressions solver = new ArithmeticProgressions();
        solver.solve(1, in, out);
        out.close();
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int[] A = new int[n];
        for (int i = 0; i < n; ++i)
            A[i] = in.readInt();
        int m = 30002;
        int[] counter = new int[m + 1];
        int[] waiting = new int[m + 1];
        long res = 0;
        for (int x : A) {
            ++counter[x];
            res += waiting[x];
            int y = Math.min(x, m - x);
            for (int idx = x + y, z = x - y; z < x; ++z, --idx) waiting[z] += counter[idx];
            for (int idx = x - y, z = x + y; z > x; --z, ++idx) waiting[z] += counter[idx];
        }
        for (int count : counter) res += (count * (long) (count - 1) * (long) (count - 2)) / 6L;
        out.println(res);
    }
}

class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
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

    public int readInt() {
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

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
}
