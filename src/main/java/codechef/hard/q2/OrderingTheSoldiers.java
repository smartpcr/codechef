package codechef.hard.q2;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 In Byteland it is always the military officer's main worry to order his soldiers on parade correctly. Luckily, ordering soldiers is not really such a problem. If a platoon consists of n men, all of them have different rank (from 1 - lowest to n - highest) and on parade they should be lined up from left to right in increasing order of rank.

 Sounds simple, doesn't it? Well, Sgt Johnny thought the same, until one day he was faced with a new command. He soon discovered that his elite commandos preferred to do the fighting, and leave the thinking to their superiors. So, when at the first rollcall the soldiers lined up in fairly random order it was not because of their lack of discipline, but simply because they couldn't work out how to form a line in correct order of ranks. Sgt Johnny was not at all amused, particularly as he soon found that none of the soldiers even remembered his own rank. Over the years of service every soldier had only learned which of the other soldiers were his superiors. But Sgt Johnny was not a man to give up easily when faced with a true military challenge. After a moment's thought a solution of brilliant simplicity struck him and he issued the following order: "men, starting from the left, one by one, do: (step forward; go left until there is no superior to the left of you; get back in line).". This did indeed get the men sorted in a few minutes. The problem was solved... for the time being.

 The next day, the soldiers came in exactly the same order as the day before, and had to be rearranged using the same method. History repeated. After some weeks, Sgt Johnny managed to force each of his soldiers to remember how many men he passed when going left, and thus make the sorting process even faster.

 If you know how many positions each man has to walk to the left, can you try to find out what order of ranks the soldiers initially line up in?

 Input
 The first line of input contains an integer t<=50, the number of test cases. It is followed by t test cases, each consisting of 2 lines. The first line contains a single integer n (1<=n<=200000). The second line contains n space separated integers wi, denoting how far the i-th soldier in line must walk to the left when applying Sgt Johnny's algorithm.

 Output
 For each test case, output a single line consisting of n space separated integers - the ranks of the soldiers, given from left to right in their initial arrangement.

 Example
 Input:
 2
 3
 0 1 0
 5
 0 1 2 0 1

 Output:
 2 1 3
 3 2 1 5 4
 */
public class OrderingTheSoldiers {
    public FasterScanner mFScanner;
    public PrintWriter mOut;
    public int mBIT[];
    public int mSegLeaves[];
    public int mAns[];
    public int mArray[];
    public int mMax;
    public int mN;
    public static final int sMAX = 200009;
    public static final int sMAXSeg = sMAX * 20;

    public OrderingTheSoldiers() {
        mFScanner = new FasterScanner();
        mOut = new PrintWriter(System.out);
        mBIT = new int[sMAX];
        mArray = new int[sMAX];
        mSegLeaves = new int[sMAXSeg];
        mAns = new int[sMAX];
    }

    public void buildSegTree(int node, int begin, int end) {
        int mid;

        if (begin > end)
            return;

        if (begin == end) {
            mSegLeaves[node] = 1;
            return;
        }

        mid = begin + ((end - begin) >> 1);

        buildSegTree(2 * node, begin, mid);
        buildSegTree(2 * node + 1, mid + 1, end);

        mSegLeaves[node] = mSegLeaves[2 * node] + mSegLeaves[2 * node + 1];

    }

    public int query(int node, int begin, int end, int rank) {
        int mid;
        int left, right;

        left = right = -1;

        if (begin == end)
            return begin;

        mid = begin + ((end - begin) >> 1);

        if (rank <= mSegLeaves[2 * node]) {
            left = query(2 * node, begin, mid, rank);
        } else {
            rank -= mSegLeaves[2 * node];
            right = query(2 * node + 1, mid + 1, end, rank);
        }

        if (right == -1)
            return left;

        return right;

    }

    public void update(int node, int begin, int end, int index) {
        int mid;

        if (index < begin || index > end)
            return;

        if (begin == end) {
            mSegLeaves[node] = 0;
            return;
        }

        mid = begin + ((end - begin) >> 1);

        update(2 * node, begin, mid, index);
        update(2 * node + 1, mid + 1, end, index);

        mSegLeaves[node] = mSegLeaves[2 * node] + mSegLeaves[2 * node + 1];
    }

    public void solveTestCaseSeg() {
        int i;
        int id;
        int rank;

        buildSegTree(1, 0, mN - 1);

        for (i = mN - 1; i >= 0; i--) {
            rank = i + 1 - mArray[i];
            id = query(1, 0, mN - 1, rank);
            mAns[i] = id + 1;
//			mOut.println("rank = " + rank + ", id = " + id);
            update(1, 0, mN - 1, id);
        }

        for (i = 0; i < mN; i++) {
            mOut.print(mAns[i]);
            mOut.print(" ");
        }
        mOut.println();
    }

    public void solve() {
        int T;
        int N;

        T = mFScanner.nextInt();

        while(T-- > 0) {
            N = mFScanner.nextInt();

            for (int i = 0; i < N; i++) {
                mArray[i] = mFScanner.nextInt();
            }

            mN = N;
            mMax = N + 2;
            solveTestCaseSeg();
        }

        close();
    }

    public void close() {
        mOut.flush();
        mOut.close();
    }

    public static void main(String [] args) {
        OrderingTheSoldiers mSol = new OrderingTheSoldiers();
        mSol.solve();
    }
}

class FasterScanner {
    private InputStream mIs;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public FasterScanner() {
        this(System.in);
    }

    public FasterScanner(InputStream is) {
        mIs = is;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = mIs.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public String nextLine() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }

    public String nextString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public int nextInt() {
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

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    }

}