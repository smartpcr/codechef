package codechef.medium.q6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 Chef has a binary tree. The binary tree consists of 1 or more nodes. Each node has a unique integer id. Each node has up to 2 children, which are identified by their ids, and each node is the child of at most 1 other node. A node X is considered to be an ancestor of node Y if node Y is a child of node X or if there is some node Z for which X is an ancestor of Z and Y is a child of Z. No node is an ancestor of itself. A special node called the root node is an ancestor of all other nodes.

 Chef has forgotten which node of his tree is the root, and wants you to help him to figure it out. Unfortunately, Chef's knowledge of the tree is incomplete. He does not remember the ids of the children of each node, but only remembers the sum of the ids of the children of each node.

 Input
 Input begins with an integer T, the number of test cases. Each test case begins with an integer N, the number of nodes in the tree. N lines follow with 2 integers each: the id of a node, and the sum of the ids of its children. The second number will be 0 if the node has no children.

 Output
 For each test case, output on a line a space separated list of all possible values for the id of the root node in increasing order. It is guaranteed that at least one such id exists for each test case.

 Constraints
 1 ≤ T ≤ 50
 1 ≤ N ≤ 30
 All node ids are between 1 and 1000, inclusive
 Sample Input
 2
 1
 4 0
 6
 1 5
 2 0
 3 0
 4 0
 5 5
 6 5
 Sample Output
 4
 6
 Explanation
 In the first sample test case, there is only one node, which is clearly the root. In the second test case, there are two non-isomorphic trees that satisfy the constraints, as seen in the following picture:

 6           6
 \         / \
 5       1   4
 / \       \
 1   4       5
 / \         / \
 2   3       2   3
 */
public class RootOfTheProblem {
    public static void main(String args[])throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        int cases=in.readInt();
        for(int i=0;i<cases;i++){
            int n=in.readInt();
            int res=0;
            for(int j=0;j<n;j++){
                res=res+in.readInt()-in.readInt();
            }
            System.out.println(res);
        }
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

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuffer res = new StringBuffer();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public Long readLong() {
        return Long.parseLong(readString());
    }

    public Double readDouble() {
        return Double.parseDouble(readString());
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
}
